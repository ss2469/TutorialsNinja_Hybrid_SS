package com.tutorialsninja.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.ProductPage;
import com.tutorialsninja.testbase.TestBase;

public class SearchProductTest extends TestBase {
	
	public SearchProductTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public HomePage homepage;
	public ProductPage productpage;
	

	@BeforeMethod
	public void registerSetup() {
		driver = initializeBrowserAndOpenApplication(configprop.getProperty("browser"));
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		homepage = new HomePage(driver);
		productpage = homepage.navigateToProductPage(dataprop.getProperty("validProduct"));
		AssertJUnit.assertTrue(productpage.verifyValidProductPresence());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		homepage = new HomePage(driver);
		productpage = new ProductPage(driver);
		homepage.enterProductName(dataprop.getProperty("invalidProduct"));
		homepage.clickOnSearchButton();
		AssertJUnit.assertTrue(productpage.verifyInvalidProductWarningMessageDisplay());
	}

	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		homepage = new HomePage(driver);
		productpage = new ProductPage(driver);
		
		homepage.clickOnSearchButton();
		AssertJUnit.assertTrue(productpage.verifyInvalidProductWarningMessageDisplay());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
