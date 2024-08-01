package com.tutorialsninja.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.tutorialsninja.testbase.TestBase;

public class LoginUtil extends TestBase{

	

	public WebDriver driver;
	
	@FindBy(css = "#input-email")
	private WebElement emailTextBox;

	@FindBy(css = "#input-password")
	private WebElement passwordTextBox;
	
	@FindBy(css = "#button-login")
	private WebElement loginButton;
	
	
	
	public LoginUtil(WebDriver driver) throws Exception {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public void loginWithValidCredentials(String emailText, String passwordText) {

		emailTextBox.sendKeys(emailText);
		passwordTextBox.sendKeys(passwordText);
		loginButton.click();

	}
}
