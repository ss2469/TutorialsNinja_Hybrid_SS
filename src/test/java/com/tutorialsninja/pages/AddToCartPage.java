package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {
	
	public WebDriver driver;
	
	@FindBy(xpath = "//h2[text() = '$122.00']")
	private WebElement priceOfLaptop;
	
	@FindBy(id = "button-cart")
	private WebElement addToCartButton;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement productSuccessfullyAddedMessage;
	
	@FindBy(id = "cart")
	private WebElement cartButton;
	
	@FindBy(xpath = "//span[@id = 'cart-total'][text() = ' 1 item(s) - $122.00']")
	private WebElement cartButtonDisplayText;
	
	
	
	
	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean displayStatusOfLaptopPrice() {
		boolean display = priceOfLaptop.isDisplayed();
		return display;
	}
	
	public void clickOnAddToCartButton() {
		addToCartButton.click();
	}

	public String actualProductAddedSuccessMessage() {
		String display = productSuccessfullyAddedMessage.getText();
		return display;
	}
	
	public boolean verifyDisplayOfCartButtonWithItemsAndTotalPriceShowing() {
		boolean display = cartButtonDisplayText.isDisplayed();
		return display;
	}
	
	public void clickOnCartButton() {
		cartButton.click();
	}
	
}
