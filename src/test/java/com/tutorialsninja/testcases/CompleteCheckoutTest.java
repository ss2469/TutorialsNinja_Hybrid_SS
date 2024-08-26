package com.tutorialsninja.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.tutorialsninja.pages.CheckoutPage;
import com.tutorialsninja.testbase.TestBase;
import com.tutorialsninja.utilities.LoginUtil;

public class CompleteCheckoutTest extends TestBase {

	public CompleteCheckoutTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public CheckoutPage checkoutpage;
	public LoginUtil loginutil;
	public WebDriverWait wait;

	@BeforeMethod
	public void completeCheckoutSetup() {
		driver = initializeBrowserAndOpenApplication(configprop.getProperty("browser"));
	}

	@Test(priority = 1)
	public void validateCompleteCheckoutWithRegisteredAccount() throws Exception {
		checkoutpage = new CheckoutPage(driver);
		checkoutpage.searchValidProductAndNavigateToCheckout();

		loginutil = new LoginUtil(driver);
		loginutil.loginWithValidCredentials(configprop.getProperty("validEmail"),
				configprop.getProperty("validPassword"));

		Assert.assertTrue(checkoutpage.verifySelectionOfRadioButtonBilling());
		checkoutpage.clickOnContinueButtonOfBillingAddress();

		Assert.assertTrue(checkoutpage.verifySelectionOfRadioButtonDelivery());
		checkoutpage.clickOnContinueButtonOfDeliveryAddress();

		Assert.assertTrue(checkoutpage.verifySelectionOfRadioButtonDeliveryMethod());
		checkoutpage.clickOnContinueButtonOfDeliveryMethod();

		Assert.assertTrue(checkoutpage.verifySelectionOfCashOnDeliveryRadioButton());
		checkoutpage.clickOnTermsAndConditionsCheckbox();
		checkoutpage.clickOnContinueButtonOfPaymentMethod();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div>#button-confirm"))));
		checkoutpage.clickOnConfirmOrderButton();

		Assert.assertTrue(checkoutpage.verifyDisplayOfSuccessMessage());

	}

	@Test(priority = 2)
	public void validateCompleteCheckoutWithGuestAccount() throws Exception {
		checkoutpage = new CheckoutPage(driver);
		checkoutpage.searchValidProductAndNavigateToCheckout();

		checkoutpage.clickOnGuestAccountRadioButton();
		checkoutpage.clickOnContinueButtonOfNewCustomer();

		checkoutpage.fillingMandatoryDetailsOfGuestAccount(dataprop.getProperty("guestFirstName"),
				dataprop.getProperty("guestLastName"), dataprop.getProperty("guestEmail"),
				dataprop.getProperty("guestTelephone"), dataprop.getProperty("guestAddress1"),
				dataprop.getProperty("guestCity"), dataprop.getProperty("guestPostCode"));

		Select select = new Select(driver.findElement(By.id("input-payment-country")));
		select.selectByVisibleText(dataprop.getProperty("guestCountry"));
		select = new Select(driver.findElement(By.id("input-payment-zone")));
		select.selectByVisibleText(dataprop.getProperty("guestState"));

		checkoutpage.clickOnContinueButtonGuest();
		checkoutpage.clickOnContinueButtonOfDeliveryMethod();
		checkoutpage.clickOnTermsAndConditionsCheckbox();
		checkoutpage.clickOnContinueButtonOfPaymentMethod();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div>#button-confirm"))));
		checkoutpage.clickOnConfirmOrderButton();
		Assert.assertTrue(checkoutpage.verifyDisplayOfSuccessMessage());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
