package com.tesco.pom.util;

import java.util.Hashtable;

public class Constants {
	
	//LOCAL_SINGLE_BROWSER_RUN =true means local single browsers parallel on local 
	//GRID_RUN = false means local grid
	
	//GRID_RUN = true means remote grid
	
	
	public static final boolean LOCAL_SINGLE_BROWSER_RUN = true;
	public static final boolean GRID_RUN = false;
	
	//Environment
	public static final String ENV="UAT"; //PROD, UAT,SAT 
	
	// URLs-prod
	public static final String PROD_HOMEPAGE_URL = "PROD URL";
	public static final String PROD_USERNAME = "admin";
	public static final String PROD_PASSWORD = "admin";

	public static final String UAT_HOMEPAGE_URL = "http://tesco.com";
	public static final String UAT_USERNAME = "admin";
	public static final String UAT_PASSWORD = "admin";
	
	public static Hashtable<String,String> table;
	public static Hashtable<String,String> getEnvDetails(){
		if(table==null){
			table = new Hashtable<String,String>();
			if(ENV.equals("PROD")){
				table.put("url", PROD_HOMEPAGE_URL);
				table.put("username", PROD_USERNAME);
				table.put("password", PROD_PASSWORD);
			}else if(ENV.equals("UAT")){
				table.put("url", UAT_HOMEPAGE_URL);
				table.put("username", UAT_USERNAME);
				table.put("password", UAT_PASSWORD);
			}
		}
		return table;
	}

	//Paths
	public static final String IE_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/IEDriverServer.exe";
	
	public static final String CHROME_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/chromedriver.exe";
	public static final String CHROME_DRIVER_EXE_Linux = System.getProperty("user.dir")+"/drivers/chromedriver";
	
	public static final String FIREFOX_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/geckodriver.exe";
	public static final String FIREFOX_DRIVER_EXE_Linux = System.getProperty("user.dir")+"/drivers/geckodriver";
	
	public static final String REPORTS_PATH = System.getProperty("user.dir")+"/Report/";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"/data/Data.xlsx";
	public static final String TESTDATA_SHEET = "TestData";
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String RUNMODE_COL = "Runmode";

	
	//================================Locators=====================================================
//Commit Test1
	//commit test2
	//commit test3
	//commit test4
	
}
