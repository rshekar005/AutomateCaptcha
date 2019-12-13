package com.captcha;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadCaptcha 
{
	WebDriver driver;
	@Test
	public void test() throws IOException, TesseractException, InterruptedException
	{
		System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");	
		 driver= new ChromeDriver();
		driver.get("https://www.incometaxindiaefiling.gov.in/home");
		driver.manage().window().maximize();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
	    driver.findElement(By.xpath(".//input[@value='  Login Here  ']")).click();
	    //Capturing captcha image in a file
	    File src=driver.findElement(By.id("captchaImg")).getScreenshotAs(OutputType.FILE);
	    //Creating a folder for image
	    String path= System.getProperty("user.dir")+"/screenshots/captcha.png";
	  //Storing in a destination folder
	    FileHandler.copy(src, new File(path));
	    //ITessaract is an interface and Tesseract is a class. With the help of these, we can read captcha from the image.
	    ITesseract test= new Tesseract();
	    //Captured image then send to tessdata folder which convert into user understandable language
	    test.setDatapath("D:/API Automation Rest Assured/Jars/Tess4j jars/New/Tess4J/tessdata");
	    //Captured image then coverted to String with the help of doOCR().
	    String imageText=test.doOCR(new File(path));
	    //Printing the imageText
	    System.out.println(imageText);
	}

}
