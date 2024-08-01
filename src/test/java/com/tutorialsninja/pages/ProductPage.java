package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	public WebDriver driver;

	@FindBy(linkText = "HP LP3065")
	WebElement validProduct;
	
	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement invalidProductMessage;
	
	@FindBy(css = "div.button-group>button:nth-child(1)")
	private WebElement addToCartButtonOnProductPage;
	
	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	public boolean verifyValidProductPresence() {
		boolean displayStatus = validProduct.isDisplayed();
		return displayStatus;
	}
	
	public boolean verifyInvalidProductWarningMessageDisplay() {
		boolean displayStatus = invalidProductMessage.isDisplayed();
		return displayStatus;
	}
	
	public AddToCartPage clickOnAddToCartButtonOnProductPage() {
		addToCartButtonOnProductPage.click();
		return new AddToCartPage(driver);
	}
}
