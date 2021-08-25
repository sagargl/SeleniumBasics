package com.qa.practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdown extends TestBase{
	public static void main(String[] args) {
		
	
	initiliztion();
	driver.get("https://demoqa.com/select-menu");
	
	/*By using Select Class we will select by index, value and visible text*/
	Select select=new Select(driver.findElement(By.xpath("//select[@id='oldSelectMenu']")));
	select.selectByIndex(2);
	select.selectByValue("6");
	select.selectByVisibleText("Yellow");
	
	
	/*By Using Select Class--> select the multiple values*/
	
		Select sel1 = new Select(driver.findElement(By.xpath("//*[@id='cars']")));

			if(sel1.isMultiple()){
				
				//Selecting multiple values by index
				sel1.selectByIndex(1);
				sel1.selectByIndex(2);

				//Or selecting by values
				sel1.selectByValue("volvo");
				sel1.selectByValue("audi");

				//Or selecting by visible text
				sel1.selectByVisibleText("Volvo");
				sel1.selectByVisibleText("Opel");
			}
	
			
			/*Print all the dropdown for multiple options in Selenium */
			
			List<WebElement>list= select.getOptions();
			//Looping through the options and printing dropdown options
			System.out.println("The dropdown options are:");
			WebElement ele;
			for(int i=0; i<list.size(); i++)
			{
				System.out.println(list.get(i).getText());
			}
			
	}
	
}