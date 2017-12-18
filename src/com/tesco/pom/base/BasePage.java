package com.tesco.pom.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import com.tesco.pom.pages.UFSSTopMenu;
import com.tesco.pom.util.Constants;

public class BasePage {

	public 	WebDriver driver;
	//public UFSSTopMenu menu;
	public ExtentTest test;

	public BasePage(){}

	public BasePage(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
		//menu=new UFSSTopMenu(driver, test);
		//PageFactory.initElements(driver, menu);
	}

	
	public String verifyTitle(String expTitle)
	{
		return "";
	}

	public String verifyText(String locator, String expText)
	{
		return "";
	}


	/*public UFSSTopMenu getMenu()
	{
		return menu;
	}*/

	public boolean isElementPresent(String locator){
		try {
			test.log(LogStatus.INFO, "Trying to find an element -> "+locator);
			int s = driver.findElements(By.xpath(locator)).size();
			if(s==0){
				reportFailure("Element not found");
				return false;
			}
			else{
				test.log(LogStatus.INFO, "Element found");
				return true;
			}
		} 
		catch (ElementNotFoundException e) {
			
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean isElementPresentWeb(WebElement ele){
		test.log(LogStatus.INFO, "Trying to find an element -> "+ele);
		boolean s = ele.isDisplayed();		
		if(s){
			
			test.log(LogStatus.INFO, "Element found");
			return true;
		}
		else{
			reportFailure("Element NOT found");
			return false;
		}

	}

	public String getCurrentSystemDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}



public void takeScreenShot(){
	Date d=new Date();
	String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
	String filePath=Constants.REPORTS_PATH+"screenshots/"+screenshotFile;
	// store screenshot in that file
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	try {
		FileUtils.copyFile(scrFile, new File(filePath));
	} catch (IOException e) {
		e.printStackTrace();
	}
	test.log(LogStatus.INFO,"Screenshot "+test.addScreenCapture(filePath));
}
	
	/*public void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath="./screenshots/"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(Constants.REPORTS_PATH+"/screenshots", screenshotFile);

		try {
			//FileUtils.copyFile(scrFile, new File(filePath));
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	} */
public void reportFailure(String failureMessage){
	test.log(LogStatus.FAIL, failureMessage);
	takeScreenShot();
	Assert.fail(failureMessage);
}

public void reportPass(String successMessage){
	test.log(LogStatus.PASS, successMessage);
	takeScreenShot();

}

public void WaitForElementPresent(String objname)
		throws Exception {
	 int time = 50;
	 int second = 0;

	 try{
		do  {
			Thread.sleep(1000);
			if (isElementPresent(objname)){
                
				break;
			}else{
				second = second+1;
				Thread.sleep(1000);	
				}
		
		}while(second < time);
		}catch(Exception e){
		}				
} 


public void selectElement(WebElement selectCategory,String value)
{
	WebElement mySelectElement = selectCategory;
	Select dropdown= new Select(mySelectElement);
	dropdown.selectByVisibleText(value);
	test.log(LogStatus.INFO,"Selected "+value+" from DropDown" );
}
}
