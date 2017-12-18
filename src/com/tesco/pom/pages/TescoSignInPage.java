package com.tesco.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tesco.pom.base.BasePage;
import com.tesco.pom.util.Constants;

public class TescoSignInPage extends BasePage {	
	
	@FindBy(xpath="//*[contains(@title, 'Groceries')]")
	public WebElement groceriesLbl;
	
	
	@FindBy(xpath="//*[contains(text(),'Sign in')]")
	public WebElement signInLnk;
	
	@FindBy(xpath="//*[contains(text(),'Email address')]")
	public WebElement emailAddressLbl;

	
	public TescoSignInPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}
//Sign in
	public Object goToHomePage()
	{
		test.log(LogStatus.INFO, "Go to URL "+Constants.getEnvDetails().get("url"));
		driver.get(Constants.getEnvDetails().get("url"));		
		boolean homePageNav=isElementPresent("//*[contains(@title, 'Groceries')]");
		if(homePageNav)	
		{	
			test.log(LogStatus.INFO, "URL Launch SUCCESSFUL and User present with Home page");
			TescoSignInPage homePage=new TescoSignInPage(driver, test);
			PageFactory.initElements(driver, homePage);
			return homePage;		
		}
		else
		{
			TescoSignInPage lPage=new TescoSignInPage(driver, test);
			PageFactory.initElements(driver, lPage);
			reportFailure("URL Launch UNSUCCESSFUL");
			return lPage;
			
		}
		
	}
	
	public Object goToSignInPage()
	{
		test.log(LogStatus.INFO, "Go to sign in page");
		signInLnk.click();
		boolean homePageNav=isElementPresent("//*[contains(text(),'Email address')]");
		if(homePageNav)	
		{	
			test.log(LogStatus.INFO, "URL Launch SUCCESSFUL and User present with Home page");
			TescoSignInPage homePage=new TescoSignInPage(driver, test);
			PageFactory.initElements(driver, homePage);
			return homePage;		
		}
		else
		{
			TescoSignInPage lPage=new TescoSignInPage(driver, test);
			PageFactory.initElements(driver, lPage);
			reportFailure("URL Launch UNSUCCESSFUL");
			return lPage;
			
		}
		
	}

}
