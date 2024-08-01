package com.tutorialsninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.pages.AccountSuccessPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.RegisterPage;
import com.tutorialsninja.testbase.TestBase;
import com.tutorialsninja.utilities.ExcelUtil;
import com.tutorialsninja.utilities.Utils;

public class RegisterTest extends TestBase {

	public RegisterTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public HomePage homepage;
	public RegisterPage registerpage;
	public AccountSuccessPage accountsuccesspage;

	@BeforeMethod
	public void registerSetup() {
		driver = initializeBrowserAndOpenApplication(configprop.getProperty("browser"));
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountDropdown();
		homepage.clickOnRegisterOption();

		registerpage = new RegisterPage(driver);
		Assert.assertTrue(registerpage.registerFormDisplayStatus());
	}

	@Test(priority = 1, dataProvider = "TNRegister", dataProviderClass = ExcelUtil.class, enabled = false)
	public void verifyRegisterWithMandatoryDetails(String firstname, String lastname, String telephone, String password,
			String confirmpassword) {

		accountsuccesspage = registerpage.fillingMandatoryDetailsToNavigateToAccountSuccessPage(firstname, lastname,
				Utils.emailWithDateTimeStamp(), telephone, password, confirmpassword);
		
		Assert.assertTrue(accountsuccesspage.validateAccountSuccessCreatedMessage());

	}

	@Test(priority = 2)
	public void verifyRegisterWithAllDetails() {

		accountsuccesspage = registerpage.fillingAllDetailsToNavigateToAccountSuccessPage(dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), Utils.emailWithDateTimeStamp(), dataprop.getProperty("telephone"), configprop.getProperty("validPassword"), 
				configprop.getProperty("validPassword"));
		
		Assert.assertTrue(accountsuccesspage.validateAccountSuccessCreatedMessage());
	}

	@Test(priority = 3)
	public void verifyRegisterWithExistingEmail() {

		registerpage.fillingMandatoryDetailsToNavigateToAccountSuccessPage(dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), configprop.getProperty("validEmail"),
				dataprop.getProperty("telephone"), configprop.getProperty("validPassword"),
				configprop.getProperty("validPassword"));

		Assert.assertTrue(dataprop.getProperty("existingEmailWarningMessage")
				.contains(registerpage.retrieveDuplicateEmailWarningMessage()));
	}

	@Test(priority = 4)
	public void verifyRegisterWithInvalidConfirmPassword() {

		registerpage.fillingMandatoryDetailsToNavigateToAccountSuccessPage(dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), Utils.emailWithDateTimeStamp(), dataprop.getProperty("telephone"),
				configprop.getProperty("validPassword"), dataprop.getProperty("invalidPassword"));

		Assert.assertTrue(registerpage.retrieveWrongConfirmPasswordWarningMessage()
				.contains(dataprop.getProperty("wrongconfirmPasswordWarning")));

	}

	@Test(priority = 5)
	public void verifyRegisterWithNoDetails() {

		registerpage.clickOnContinueButton();
		Assert.assertTrue(registerpage.retrieveAllWarningMessages(dataprop.getProperty("privacyPolicyWarningMessage"),
				dataprop.getProperty("firstnameWarningMessage"), dataprop.getProperty("lastnameWarningMessage"),
				dataprop.getProperty("emailWarningMessage"), dataprop.getProperty("telephoneWarningMessage"),
				dataprop.getProperty("passwordWarningMessage")));
	}

	@Test(priority = 6)
	public void verifyRegisterWithoutClickingAgreePrivacyPolicy() {
		registerpage = new RegisterPage(driver);
		registerpage.fillingMandatoryDetailsWithoutClickingPrivacyPolicyCheckbox(dataprop.getProperty("firstname"),
				dataprop.getProperty("lastname"), configprop.getProperty("validEmail"),
				dataprop.getProperty("telephone"), configprop.getProperty("validPassword"),
				configprop.getProperty("validPassword"));

		Assert.assertTrue(dataprop.getProperty("privacyPolicyWarningMessage").contains(registerpage.retrievePrivacyPolicyWarningMessage()));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
