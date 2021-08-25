package com.qa.practice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	/* Initilize all my properties and launch browser */

	public static WebDriver driver;
	
	public static void initiliztion()
	{
	
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	
	}

}