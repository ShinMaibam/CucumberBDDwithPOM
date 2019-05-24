package com.pri.stepDef;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.pri.utilityHelper.Constants;
import com.pri.utilityHelper.DriverService;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks {

	DriverService driverservice;
	
	@Before
	public void initializeDriver() {
		driverservice = new DriverService();
		driverservice.InitializeDriver(Constants.getBrowser());
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driverservice.driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			}catch(Exception e) {
				e.printStackTrace();
			}
		} /*else {
			try {
				scenario.embed(((TakesScreenshot) driverservice.driver).getScreenshotAs(OutputType.BYTES), "image/png");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		
		driverservice.driver.close();
		driverservice.driver.quit();
		
	}

}
