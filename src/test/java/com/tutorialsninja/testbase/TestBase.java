package com.tutorialsninja.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.utilities.Utils;

public class TestBase {
	
	public WebDriver driver;
	public FileInputStream ipconfig, ipdata;
	public Properties configprop, dataprop;
	public ChromeOptions options;
	
	
	public TestBase() throws Exception {
		ipconfig = new FileInputStream("src/test/resources/config.properties");
		configprop = new Properties();
		configprop.load(ipconfig);
		
		ipdata = new FileInputStream("src/test/resources/testData.properties");
		dataprop = new Properties();
		dataprop.load(ipdata);
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		if (browserName.equals("chrome")) {
			options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
			driver = new ChromeDriver(options);

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browserName.equals("Edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		} else if (browserName.equals("Safari")) {
			driver = new SafariDriver();
			driver.manage().window().maximize();
		} else {
			System.out.println("Browser Not matching");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGELOAD_TIMEOUT));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIMEOUT));
		driver.get(configprop.getProperty("url"));
		return driver;
	}
}
