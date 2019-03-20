package com.aditya.docker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GoogleSearchFirefox2 {
	static RemoteWebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException
	{
		System.out.println("Running Test in Docker Container <<Firefox>>");
		
		DesiredCapabilities cap = DesiredCapabilities.firefox();
							cap.setPlatform(Platform.LINUX);
							cap.setVersion("");
		driver = new RemoteWebDriver(new URL("http://192.168.0.104:4444/wd/hub"),cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test(description= "Searching in youtube")
	public void googleSearch() throws InterruptedException
	{
		driver.navigate().to("https://www.youtube.com/");
		driver.findElement(By.xpath("//input[@type='text' and @id='search']")).sendKeys("Specialize Automation");
		driver.findElement(By.xpath("//*[@id='search-icon-legacy']")).click();
		System.out.println("Search in Firefox Completed");
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public static void tearDown() throws Exception
	{
		if(driver!=null)
		{
			System.out.println("Completed Test in Docker Container <<Firefox>>");
			driver.quit();
		}
	}
}
