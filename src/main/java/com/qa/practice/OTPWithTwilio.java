package com.qa.practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import org.openqa.selenium.By;

import com.twilio.Twilio;

public class OTPWithTwilio extends TestBase{
	public static final String ACCOUNT_SID="ACb03360de883bf1ba4176c5ee1a31e542";
	public static final String AUTH_TOKEN="765cf2c205d0174de780d195554148fe";
	
	public static void main(String[] args) {
	initiliztion();	
	driver.get("https://www.amazon.in");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.cssSelector("a#nav-link-accountList>span>span")).click();
	//driver.findElement(By.linkText("Start here.")).click();
	//driver.findElement(By.id("continue")).click();
	
	driver.findElement(By.id("ap_customer_name")).click();
	driver.findElement(By.id("auth-country-picker-container")).click();
	driver.findElement(By.xpath("//ul[@role='application']/li/a[contains(text(), 'United States +1')]")).click();
	driver.findElement(By.xpath("//input[@id='ap_phone_number']")).sendKeys("8066033880");
	driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("sagar@123");
	driver.findElement(By.id("continue")).click();
	
	// get the OTP using Twilio APIs:
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			String smsBody = getMessage();
			System.out.println(smsBody);
			String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
			System.out.println(OTPNumber);
			
			driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);

		}

		public static String getMessage() {
			return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
					.filter(m -> m.getTo().equals("+18066033880")).map(Message::getBody).findFirst()
					.orElseThrow(IllegalStateException::new);
		}

		private static Stream<Message> getMessages() {
			ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
			return StreamSupport.stream(messages.spliterator(), false);
		}

}
