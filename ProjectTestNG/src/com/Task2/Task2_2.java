package com.Task2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Task2_2 {
	
	WebDriver driver;

	
	@Parameters({"url"})
	@BeforeMethod
	void setUpPage(String url) {
		System.out.println("Before Method Task2_2");

		String chromeDriver = ".//selenium_drivers//";
		System.setProperty("webdriver.chrome.driver", chromeDriver+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://corridor.pramati.com/");
		driver.manage().window().maximize() ;
		
		WebElement login;
		login = driver.findElement(By.linkText("LOGIN"));
		login.click();
	}
	
	@Test(priority = 1)
	void login()  {
		System.out.println("In Login Method Task2_2");
				
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
		WebElement username;
		username = driver.findElement(By.id("username"));
		username.clear();
		username.sendKeys("vibha.lakshmi@imaginea.com");
		
		WebElement password;
		password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Vibha2108@");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// login
		WebElement loginbtn;
		loginbtn = driver.findElement(By.id("loginButton"));
		loginbtn.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


	}

	@Test(priority = 2)
	void logout() {
		login();
		System.out.println("In Logout Method Task2_2");
		//Logout
		WebElement logout;
		logout = driver.findElement(By.linkText("LOGOUT"));
		logout.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement logout_link;
		logout_link = driver.findElement(By.linkText("log out"));
		logout_link.click();

	}

	@AfterMethod
	void tearDown() {
		System.out.println("In TearDown Task2_2");
		driver.quit();
	}
}
