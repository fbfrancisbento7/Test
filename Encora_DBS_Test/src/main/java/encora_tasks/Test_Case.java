package encora_tasks;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Test_Case extends Test_run {
	
	Object_Repository or = new Object_Repository();
	
	public void Credit_Card_1() throws IOException, InterruptedException
	{
		//Launch the Browser
		Driver_Call(Read_Data_Table("url"));
		//Check if the default page is displayed, if so navigate to personal banking
		try {
			if(or.btn_Personal_Banking().isDisplayed())
			{
				Reporting("The default page is displayed. hence navigate to personal banking","Done");
				or.btn_Personal_Banking().click();
				try {
					if(or.fr_dbs().isDisplayed())
					{
						Reporting("Personal banking screen is loaded Successfully","Pass");
					}}
				catch(Exception e) {
					Reporting("Personal banking screen is not loaded Successfully","Fail");
				}
				//Click on DBS
				or.fr_dbs().click();
			}}
		catch(Exception e) {
			Reporting("The default page is not displayed","Done");
		}
				
		
		//Verify the Open browser
		try {
			if(or.btn_login().isDisplayed())
			{
				Reporting("The personal banking dbs page is displayed successfully","Pass");
			}}
		catch(Exception e) {
			Reporting("The personal banking dbs page is not displayed","Fail");
		}
		//Click on Cards
		or.btn_Cards().click();
		
		//Verify if the cards page is displayed
		try {
			if(or.btn_Credit_Cards().isDisplayed())
			{
				Reporting("The cards page is loaded Successfully","Pass");
			}}
		catch(Exception e) {
			Reporting("The cards page is not loaded Successfully","Fail");
		}
		
		//Click on Credit Cards
		or.btn_Credit_Cards().click();
		
		//Verify if table containing all the credit cards is displayed
		try {
			if(or.tab_cards().isDisplayed())
			{
				// Scroll Down
				 Actions actions = new Actions(driver);
			      actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			      
				Reporting("The table containing all the credit cards is displayed Successfully","Pass");
			}}
		catch(Exception e) {
			Reporting("The table containing all the credit cards is not displayed Successfully","Fail");
		}
		
		List <WebElement> cc_names = or.tab_cards().findElements(By.xpath("//div[@class='cardcontainer-header']"));
		//Selecting the first credit card type
		Thread.sleep(5000);
		for(int cci=0;cci<cc_names.size();cci++)
		{
		    if(cc_names.get(cci).getText().contentEquals(Read_Data_Table("CC_1")))
			{
//		    	Actions builder = new Actions(driver);
//                Action scrol = builder.moveToElement(cc_names.get(cci)).build();
//                scrol.perform();
//				cc_names.get(cci).click();
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()", cc_names.get(cci));
				break;
			}
		}
		
		//Selecting the second credit card type
		for(int ccj=0;ccj<cc_names.size();ccj++)
		{
			if(cc_names.get(ccj).getText().contentEquals(Read_Data_Table("CC_2")))
			{
//				Actions action =new Actions(driver);
//				action.moveToElement(cc_names.get(ccj)).click().build().perform();
//				cc_names.get(ccj).click();
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()", cc_names.get(ccj));
				break;
			}
		}
		
		//Click on the compare button
		try {
			if(or.btn_cardcomapare().isDisplayed())
			{
				Reporting("The cards compare button is loaded Successfully","Pass");
				or.btn_cardcomapare().click();
			}}
		catch(Exception e) {
			Reporting("The cards compare button is not loaded Successfully","Fail");
		}
		
	
		try {
			if(or.btn_recompare().isDisplayed())
			{
				or.tab_cards().click();
				Full_Screenshot();
				Reporting("The cards comparing page is loaded Successfully and full page screenshot is taken","Pass");
				
			}}
		catch(Exception e) {
			Reporting("The cards comparing page is not loaded Successfully","Fail");
		}
		
		
		//Close the browser
		Driver_Close();
		
	}
	

	public void Credit_Card_2() throws IOException, InterruptedException
	{
		Credit_Card_1();
	}
	public void Credit_Card_3() throws IOException, InterruptedException
	{
		Credit_Card_1();
	}
	public void Driver_Call(String url)
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\91950\\Desktop\\Bento Documents\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver =  new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	public void Driver_Close()
	{
		driver.quit();
	}
	public void Full_Screenshot()
	{
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);             
		  try {                 
			  ImageIO.write(screenshot.getImage(),"png",new File(screenshotpath + "\\" + "Full Screenshot.png"));             
		  } catch (IOException e){              
			  e.printStackTrace();  
		  }    
	}

}
