package com.tutorialsninja.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pages.AddToCartPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.pages.ProductPage;
import com.tutorialsninja.testbase.TestBase;
import com.tutorialsninja.utilities.LoginUtil;

public class CompleteCheckoutTest extends TestBase {

	public CompleteCheckoutTest() throws Exception {
		super();
	}
	
	public WebDriver driver;
	public HomePage homepage;
	public ProductPage productpage;
	public AddToCartPage addtocartpage;
	public LoginUtil loginutil;
	public WebDriverWait wait; 
	
	
	@BeforeMethod
	public void completeCheckoutSetup() {
		driver = initializeBrowserAndOpenApplication(configprop.getProperty("browser"));
	}

	
	@Test(priority = 1)
	public void validateCompleteCheckoutWithRegisteredAccount() throws Exception {
		homepage = new HomePage(driver);
		//search product and navigate to product page
		productpage = homepage.navigateToProductPage(dataprop.getProperty("validProduct"));
		productpage.verifyValidProductPresence();
		//click on 'add to cart' button (on ProductPage)
		addtocartpage = productpage.clickOnAddToCartButtonOnProductPage();
		AssertJUnit.assertTrue(addtocartpage.displayStatusOfLaptopPrice());
		addtocartpage.clickOnAddToCartButton();
		AssertJUnit.assertTrue(addtocartpage.actualProductAddedSuccessMessage().contains(dataprop.getProperty("expectedProductAddedSuccessMessage")));
		AssertJUnit.assertTrue(addtocartpage.verifyDisplayOfCartButtonWithItemsAndTotalPriceShowing());
		addtocartpage.clickOnAddToCartButton();
		
		//Assert.assertTrue(driver.findElement(By.xpath("//p/a/strong/text()[contains(., 'View Cart')]")).isDisplayed());
		//click on 'View Cart' link on DropDown of Cart Button
		
		driver.findElement(By.xpath("//a[@title = 'Shopping Cart']")).click();
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//div/a[text() = 'Checkout']")).isDisplayed());
		driver.findElement(By.xpath("//div/a[text() = 'Checkout']")).click();
		
		loginutil = new LoginUtil(driver);
		loginutil.loginWithValidCredentials(configprop.getProperty("validEmail"), configprop.getProperty("validPassword"));
		
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//input[@name = 'payment_address' and @value = 'existing']")).isSelected());
		driver.findElement(By.cssSelector("#button-payment-address")).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//input[@name = 'shipping_address' and @value = 'existing']")).isSelected());
		driver.findElement(By.cssSelector("#button-shipping-address")).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//input[@name = 'shipping_method']")).isSelected());
		driver.findElement(By.cssSelector("#button-shipping-method")).click();

		AssertJUnit.assertTrue(driver.findElement(By.xpath("//input[@name = 'payment_method' and @value = 'cod']")).isSelected());
		driver.findElement(By.cssSelector("input[name = 'agree']")).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.cssSelector("div>#button-payment-method")).isDisplayed());
		driver.findElement(By.cssSelector("div>#button-payment-method")).click();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div>#button-confirm")))).click();
		
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//p[text() = 'Your order has been successfully processed!']")).isDisplayed());
		
	}
	
	
	@Test(priority = 2, enabled = false)
	public void validateCompleteCheckoutWithGuestAccount() {

		
								//Guest
		//Checkout Options { Returning Customer }
		/*enter email, password, click login button
		 * 
		 * OR //input[@value = 'guest']
		 *    css - #button-account
		 * 
		 * click continue - Billing details
		 * #button-payment-address
		 * 
		 * click continue - Delivery details
		 * #button-shipping-address
		 * 
		 * click continue - delivery method
		 * #button-shipping-method
		 * 
		 * click on terms & conditions checkbox
		 * css - input[name = 'agree']
		 * 
		 * click on continue - payment method
		 * #button-payment-method
		 * 
		 * click on button 'Confirm Order'
		 * #button-confirm
		 * 
		 * 
		 * Verify message - 'Your order has been successfully processed!'
		 * //p[text() = 'Your order has been successfully processed!']
		 */
		//click continue button - //div/a[text() = 'Continue']
		
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
