package encora_tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Test_run {
	
	public static WebDriver driver;
	static String Control_Sheet_Location = "C:\\Users\\91950\\eclipse-workspace\\Encora_DBS_Test\\";
	static String[][] TestArr = null;
	static String menam = "";
	static String Comments = "";
	static int scn_iterator = 1;
	static	String screenshotpath;
	
	
	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Test_Case tst = new Test_Case();
		  
		  File file = new File(Control_Sheet_Location + "Control_Book.xlsx");
	      FileInputStream FIP = new FileInputStream(file);
	      
	      //Get the workbook instance for XLSX file 
	      XSSFWorkbook workbook = new XSSFWorkbook(FIP);
	      //Reading the ControSheet
	      XSSFSheet worksheet = workbook.getSheet("Control_Sheet");
	      //Getting the Rowcount
	      int rowcnt = worksheet.getLastRowNum() -worksheet.getFirstRowNum();
	      //Checking for the Execute status
	      TestArr = Assign_Array();
	      	      
	      for(int strcnt=1;strcnt<=rowcnt;strcnt++)
	      {
	    	  Row row = worksheet.getRow(strcnt);
	    	  Cell cell = row.getCell(1);
	    	  String Exec = cell.getStringCellValue();
	    	  //run the test case if the execute status is Yes
	    	  if(Exec.equalsIgnoreCase("YES"))
	    	  {
	    		 Comments = "";
	    		 scn_iterator = 1;
	    		 menam = row.getCell(0).getStringCellValue();
	    		 //create result folder
	    		 Create_Folder();
	    		 //Call the Test case
	    		 tst.getClass().getMethod(menam).invoke(tst);
	    		 row.getCell(2).setCellValue(Comments);
	    		 
	    		 if(Comments.toLowerCase().contains("fail"))
	    		 {
	    			 row.getCell(3).setCellValue("Fail");
	    		 }
	    		 else
	    		 {
	    			 row.getCell(3).setCellValue("Pass");
	    		 }
	    		
	    	  }
	    	  else
	    	  {
	    		  row.getCell(3).setCellValue("Excluded");
	    		  row.getCell(2).setCellValue("");
	    	  }
	      }
	      
	      FileOutputStream fileOut = new FileOutputStream(Control_Sheet_Location+ "Control_Book.xlsx");
	      workbook.write(fileOut);
	      fileOut.close();
	      System.out.println("Complete");
	    	    			  
	     
	}
	
	//Read the Data Table and store in array
	public static String[][] Assign_Array() throws IOException
	{
		 File file = new File(Control_Sheet_Location+ "Control_Book.xlsx");
	     FileInputStream FIP = new FileInputStream(file);
	      
	      //Get the workbook instance for XLSX file 
	      XSSFWorkbook workbook = new XSSFWorkbook(FIP);
	      //Reading the ControSheet
	      XSSFSheet worksheet = workbook.getSheet("Test_Data");
	      //Getting the Rowcount
	      int rowcnt = worksheet.getLastRowNum()+1;
	      int Colno = worksheet.getRow(0).getLastCellNum();
	      
	      String[][] TestArr = new String[rowcnt][Colno];
	      
	      for(int stra=0;stra<rowcnt;stra++)
	      {
	    	  Row r1 = worksheet.getRow(stra);
	    	  for(int strb=0;strb<Colno;strb++)
	    	  {
	    		 String c1 = r1.getCell(strb).getStringCellValue();
	    		  TestArr[stra][strb] = c1;
	    	  }
	      }  
	      
	      return TestArr;
	}
	
	//Read the dataTable column
	public static String Read_Data_Table (String Colmn) throws IOException
	{
		int rocnt = TestArr.length;
		int colcnt = TestArr[0].length;
		String dtval = "";
		
		for(int stra =0;stra<rocnt;stra++)
		{
			if(TestArr[stra][0].contentEquals(menam))
			{
				for(int strb=0;strb<colcnt;strb++)
				{
					if(TestArr[0][strb].contentEquals(Colmn))
					{
						dtval = TestArr[stra][strb];
						break;
					}
				}
			}
		}
		return dtval;
	}

	//To add Comments
	public void Reporting(String Subcomments,String Status) throws IOException
	{
		Comments = Comments + Subcomments + " :-" + Status + "\n"  ;
		screenshot();
	}
	
	//Create new result folder with date and timeResult
	public static void Create_Folder()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyy_HHmmss");
		Date date = new Date(System.currentTimeMillis());
		
		screenshotpath = Control_Sheet_Location + "Result\\" + menam + "_" +formatter.format(date);
		System.out.println(screenshotpath);
		File files = new File(screenshotpath);
		files.mkdir();
		
	}
	//Take Screenshots
	public void screenshot() throws IOException
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(SrcFile, new File(screenshotpath + "\\" + scn_iterator + ".png"));
		
		                      	
		
		scn_iterator = scn_iterator + 1;
	}

}
