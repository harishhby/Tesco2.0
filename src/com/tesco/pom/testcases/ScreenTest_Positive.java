package com.tesco.pom.testcases;

import java.io.File;
import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.tesco.pom.pages.TescoHomePage;
import com.tesco.pom.testcases.base.BaseTest;
import com.tesco.pom.util.Constants;
import com.tesco.pom.util.DataUtil;

public class ScreenTest_Positive extends BaseTest {
	
	String testCaseName="ScreenTest_Positive";
	public String actualResult="";	

	@Test(dataProvider="getData")	
	public void doLogin(Hashtable<String, String> data) throws InterruptedException
	{
		test=extent.startTest(testCaseName+" - "+data.get("Browser"),"Screen Test Positive Flow");
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}

		test.log(LogStatus.INFO,"Starting  "+ testCaseName +" Test Script Execution");
		init(data.get("Browser"));		
		test.log(LogStatus.INFO, data.get("Browser")+" Browser opened Successfully");

		TescoHomePage homePagePf=new TescoHomePage(driver,test);
		PageFactory.initElements(driver, homePagePf);
		
		Object homePage=homePagePf.goToHomePage();	
		homePagePf.clickOnGroceries();
		Thread.sleep(5000);
		
		if(homePage instanceof TescoHomePage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("Tesco Home Page NOT found");
		}

		
		try {
			ScreenRegion srn=new DesktopScreenRegion();
			ScreenRegion r = new DesktopScreenRegion();

			Target tg =null;
			tg = new ImageTarget(new File(System.getProperty("user.dir")+"\\Images\\Tesco_Grocery_Items.PNG"));
			
			r = srn.find(tg);
			
			Thread.sleep(5000);
			if( r!=null)
			{
				test.log(LogStatus.INFO,"Screenshot " +test.addScreenCapture(System.getProperty("user.dir")+"\\Images\\Tesco_Grocery_Items.PNG"));
				reportPass("Tesco Groceries Items Resolution appropriate");
				
			}
			else
			{
				test.log(LogStatus.INFO,"Screenshot " +test.addScreenCapture(System.getProperty("user.dir")+"\\Images\\Tesco_Grocery_Items.PNG"));
				reportFailure("Tesco Groceries Items Resolution NOT appropriate");
				
			}

		} catch (Exception e) {
			System.out.println("Image NOT found");
			
			test.log(LogStatus.INFO, "Failed");
			reportFailure("Tesco_HomePage_Header NOT Found");
		}	

	}


	@AfterMethod
	public void quit()
	{
		if(extent!=null)
		{
			extent.endTest(test);
			extent.flush();		
		}
		if(driver!=null)
		{
			try
			{
				driver.quit();
			}
			catch(Exception e)
			{
				driver.quit();
			}
		}
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(xls, testCaseName);
	}
}
