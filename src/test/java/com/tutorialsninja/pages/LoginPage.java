package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	@FindBy(css = "#input-email")
	private WebElement emailTextBox;

	@FindBy(css = "#input-password")
	private WebElement passwordTextBox;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement loginButton;
	
	@FindBy(css = "div.alert.alert-danger.alert-dismissible")
	private WebElement loginWarningMessage;
	
	@FindBy(xpath = "//h2[contains(text(), 'Returning Customer')]")
	private WebElement returningCustomerHeading;
	
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public void enterEmail(String emailText) {
		emailTextBox.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		passwordTextBox.sendKeys(passwordText);
	}

	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	public AccountPage navigateToLoginPageByCombining3Actions(String emailText, String passwordText) {
		emailTextBox.sendKeys(emailText);
		passwordTextBox.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retreiveLoginWarningMessageText() {
		String warningText = loginWarningMessage.getText();
		return warningText;
	}
	
	public boolean returningCustomerHeadingDisplayStatus() {
		boolean display = returningCustomerHeading.isDisplayed();
		return display;
	}
	
}
