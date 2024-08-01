package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;

	@FindBy(linkText = "My Account")
	WebElement myAccountDropdown;

	@FindBy(linkText = "Login")
	WebElement loginOption;

	@FindBy(linkText = "Register")
	WebElement registerOption;
	
	@FindBy(name = "search")
	WebElement searchTextbox;
	
	@FindBy(css = "button.btn.btn-default.btn-lg")
	WebElement searchButton;
	
	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	public void clickOnMyAccountDropdown() {
		myAccountDropdown.click();
	}

	public void clickOnLoginOption() {
		loginOption.click();
	}

	public void clickOnRegisterOption() {
		registerOption.click();
	}
	
	
	
	public LoginPage navigateToHomePage() {
		myAccountDropdown.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
    public RegisterPage navigateToRegisterPage() {
    	myAccountDropdown.click();
    	registerOption.click();
    	return new RegisterPage(driver);
	}

	public void enterProductName(String validProductText) {
		searchTextbox.sendKeys(validProductText);
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
	}
	
	public ProductPage navigateToProductPage(String validProductText) {
		searchTextbox.sendKeys(validProductText);
		searchButton.click();
		return new ProductPage(driver);
	}
	
}
