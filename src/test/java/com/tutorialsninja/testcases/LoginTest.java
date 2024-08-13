package com.tutorialsninja.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pages.AccountPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.testbase.TestBase;
import com.tutorialsninja.utilities.Utils;

public class LoginTest extends TestBase {

	public LoginTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public HomePage homepage;
	public LoginPage loginpage;
	public AccountPage accountpage;
	

	@BeforeMethod
	public void loginSetup() {
		driver = initializeBrowserAndOpenApplication(configprop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.navigateToHomePage();
		
		loginpage = new LoginPage(driver);
		AssertJUnit.assertTrue(loginpage.returningCustomerHeadingDisplayStatus());
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
		accountpage = loginpage.navigateToLoginPageByCombining3Actions(configprop.getProperty("validEmail"), configprop.getProperty("validPassword"));
		AssertJUnit.assertTrue(accountpage.editAccountInfoLinkDisplayStatus());
	}

	@Test(priority = 2)
	public void verifyLoginWithInalidCredentials() {
		loginpage.navigateToLoginPageByCombining3Actions(Utils.emailWithDateTimeStamp(), dataprop.getProperty("invalidPassword"));
		AssertJUnit.assertEquals(loginpage.retreiveLoginWarningMessageText(), dataprop.getProperty("loginWarningMessage"));
	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginpage.navigateToLoginPageByCombining3Actions(configprop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
		AssertJUnit.assertEquals(loginpage.retreiveLoginWarningMessageText(), dataprop.getProperty("loginWarningMessage"));
	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginpage.navigateToLoginPageByCombining3Actions(Utils.emailWithDateTimeStamp(), configprop.getProperty("validPassword"));
		AssertJUnit.assertEquals(loginpage.retreiveLoginWarningMessageText(), dataprop.getProperty("loginWarningMessage"));
	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		AssertJUnit.assertEquals(loginpage.retreiveLoginWarningMessageText(), dataprop.getProperty("loginWarningMessage"));
	}

	@Test(priority = 6)
	public void verifyLoginWithEmptyEmail() {
		loginpage = new LoginPage(driver);
		loginpage.enterPassword(configprop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		AssertJUnit.assertEquals(loginpage.retreiveLoginWarningMessageText(), dataprop.getProperty("loginWarningMessage"));
	}

	@Test(priority = 7)
	public void verifyLoginWithEmptyPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmail(configprop.getProperty("validEmail"));
		loginpage.clickOnLoginButton();
		AssertJUnit.assertEquals(loginpage.retreiveLoginWarningMessageText(), dataprop.getProperty("loginWarningMessage"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
