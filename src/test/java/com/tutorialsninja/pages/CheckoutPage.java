package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tutorialsninja.testbase.TestBase;

public class CheckoutPage extends TestBase {

	public CheckoutPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver driver;
	public HomePage homepage;
	public ProductPage productpage;
	public AddToCartPage addtocartpage;
	public CheckoutPage checkoutPage;

	@FindBy(xpath = "//div/a[ text() = 'Checkout']")
	private WebElement checkoutButton;

	@FindBy(xpath = "//a[@title = 'Shopping Cart']")
	private WebElement shoppingcartLink;

	@FindBy(xpath = "//input[@name = 'payment_address' and @value = 'existing']")
	private WebElement radioButtonBilling;

	@FindBy(css = "#button-payment-address")
	private WebElement continueButtonBillingAddress;

	@FindBy(xpath = "//input[@name = 'shipping_address' and @value = 'existing']")
	private WebElement radioButtonDelivery;

	@FindBy(css = "#button-shipping-address")
	private WebElement continueButtonDeliveryAddress;

	@FindBy(xpath = "//input[@name = 'shipping_method']")
	private WebElement radioButtonFlatRate;

	@FindBy(css = "#button-shipping-method")
	private WebElement continueButtonDeliveryMethod;

	@FindBy(xpath = "//input[@name = 'payment_method' and @value = 'cod']")
	private WebElement cashOnDeliveryRadioButton;

	@FindBy(css = "input[name = 'agree']")
	private WebElement termsAndConditionsCheckbox;

	@FindBy(css = "#button-payment-method")
	private WebElement continueButtonPaymentMethod;

	@FindBy(css = "div>#button-confirm")
	private WebElement confirmOrderButton;

	@FindBy(xpath = "//p[text() = 'Your order has been successfully processed!']")
	private WebElement successMessageOfOrderPlacement;

	@FindBy(xpath = "//input[@value = 'guest']")
	private WebElement guestAccountRadioButton;

	@FindBy(css = "#button-account")
	private WebElement continueButtonOfNewCustomer;

	@FindBy(css = "#input-payment-firstname")
	private WebElement firstnameTextBox;

	@FindBy(css = "#input-payment-lastname")
	private WebElement lastnameTextBox;

	@FindBy(css = ".form-group.required>input[name = 'email']")
	private WebElement emailTextBox;

	@FindBy(css = ".form-group.required>input[name = 'telephone']")
	private WebElement telephoneTextBox;

	@FindBy(css = ".form-group.required>input[name = 'address_1']")
	private WebElement address1TextBox;

	@FindBy(css = ".form-group.required>input[name = 'city']")
	private WebElement cityTextBox;

	@FindBy(css = ".form-group.required>input[name = 'postcode']")
	private WebElement postCodeTextBox;

	@FindBy(css = "#button-guest")
	private WebElement continueButtonGuest;

	public void searchValidProductAndNavigateToCheckout() throws Exception {
		homepage = new HomePage(driver);
		checkoutPage = new CheckoutPage(driver);
		productpage = homepage.navigateToProductPage(dataprop.getProperty("validProduct"));
		productpage.verifyValidProductPresence();

		addtocartpage = productpage.clickOnAddToCartButtonOnProductPage();
		Assert.assertTrue(addtocartpage.displayStatusOfLaptopPrice());
		addtocartpage.clickOnAddToCartButton();
		Assert.assertTrue(addtocartpage.actualProductAddedSuccessMessage()
				.contains(dataprop.getProperty("expectedProductAddedSuccessMessage")));
		Assert.assertTrue(addtocartpage.verifyDisplayOfCartButtonWithItemsAndTotalPriceShowing());
		addtocartpage.clickOnAddToCartButton();

		checkoutPage.clickOnShoppingCartLink();
		Assert.assertTrue(checkoutPage.verifyDisplayOfCheckoutButton());
		checkoutPage.clickOnCheckoutButton();
	}

	public void clickOnCheckoutButton() {
		checkoutButton.click();
	}

	public void clickOnShoppingCartLink() {
		shoppingcartLink.click();
	}

	public boolean verifyDisplayOfCheckoutButton() {
		boolean display = checkoutButton.isDisplayed();
		return display;
	}

	public boolean verifySelectionOfRadioButtonBilling() {
		boolean selected = radioButtonBilling.isSelected();
		return selected;
	}

	public void clickOnContinueButtonOfBillingAddress() {
		continueButtonBillingAddress.click();
	}

	public boolean verifySelectionOfRadioButtonDelivery() {
		boolean selected = radioButtonDelivery.isSelected();
		return selected;
	}

	public void clickOnContinueButtonOfDeliveryAddress() {
		continueButtonDeliveryAddress.click();
	}

	public boolean verifySelectionOfRadioButtonDeliveryMethod() {
		boolean selected = radioButtonFlatRate.isSelected();
		return selected;
	}

	public void clickOnContinueButtonOfDeliveryMethod() {
		continueButtonDeliveryMethod.click();
	}

	public boolean verifySelectionOfCashOnDeliveryRadioButton() {
		boolean selected = cashOnDeliveryRadioButton.isSelected();
		return selected;
	}

	public void clickOnTermsAndConditionsCheckbox() {
		termsAndConditionsCheckbox.click();
	}

	public void clickOnContinueButtonOfPaymentMethod() {
		continueButtonPaymentMethod.click();
	}

	public void clickOnConfirmOrderButton() {
		confirmOrderButton.click();
	}

	public boolean verifyDisplayOfSuccessMessage() {
		boolean display = successMessageOfOrderPlacement.isDisplayed();
		return display;
	}

	public void clickOnGuestAccountRadioButton() {
		guestAccountRadioButton.click();
	}

	public void clickOnContinueButtonOfNewCustomer() {
		continueButtonOfNewCustomer.click();
	}

	public void fillingMandatoryDetailsOfGuestAccount(String firstnameText, String lastnameText, String emailText,
			String telephoneText, String address1Text, String cityText, String postcodeText) {

		firstnameTextBox.sendKeys(firstnameText);
		lastnameTextBox.sendKeys(lastnameText);
		emailTextBox.sendKeys(emailText);
		telephoneTextBox.sendKeys(telephoneText);
		address1TextBox.sendKeys(address1Text);
		cityTextBox.sendKeys(cityText);
		postCodeTextBox.sendKeys(postcodeText);

	}

	public void clickOnContinueButtonGuest() {
		continueButtonGuest.click();
	}

}
