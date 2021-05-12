package encora_tasks;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Object_Repository extends Test_run{
	
	public WebElement btn_login() {
		
		WebElement btn_login = driver.findElement(By.xpath("//li[@class='submenulist last-child']//a[contains(text(),'Login')]"));
		return btn_login;
	}
	public WebElement btn_Cards() {
		
		WebElement btn_Cards = driver.findElement(By.xpath("//div[@class='navbar-links-left hidden-xs ']//a[contains(text(),'Cards')]"));
		return btn_Cards;
	}
	public WebElement btn_Credit_Cards() {
		
		WebElement btn_Credit_Cards = driver.findElement(By.xpath("//li[2]/a[contains(text(),'Credit Cards')]"));
		return btn_Credit_Cards;
	}
	public WebElement tab_cards() {
		
		WebElement tab_cards = driver.findElement(By.xpath("//div[@class='tab-content']"));
		return tab_cards;
	}
	public WebElement btn_Personal_Banking() {
		
		WebElement btn_Personal_Banking = driver.findElement(By.xpath("//a[contains(text(),'Personal')]"));
		return btn_Personal_Banking;
	}
	public WebElement fr_dbs() {
		
		WebElement fr_dbs = driver.findElement(By.xpath("//*[@id=\"pb\"]//span[contains(text(),'DBS')]"));
		return fr_dbs;
	}
	public WebElement btn_cardcomapare() {
		
		WebElement btn_cardcomapare = driver.findElement(By.id("cardCompareBtn"));
		return btn_cardcomapare;
	}
	public WebElement btn_recompare() {
		
		WebElement btn_recompare = driver.findElement(By.id("cardReCompareBtn"));
		return btn_recompare;
	}
	
}
