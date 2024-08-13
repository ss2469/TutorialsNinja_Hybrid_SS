package com.tutorialsninja.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public static ExtentReports generateExtentReport() throws Exception {
		
	
	ExtentReports extentReport = new ExtentReports();
	
	File extentReportFile = new File("test-output/ExtentReports/extentreport.html");
	
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("Tutorialsninja Automation Results");
	sparkReporter.config().setDocumentTitle("TutorialsNinjaReport|HybridFramework");
	sparkReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
	Properties prop = new Properties();
	prop.load(ip);
	
	extentReport.setSystemInfo("application url", prop.getProperty("url"));
	extentReport.setSystemInfo("browswer name", prop.getProperty("browser"));
	extentReport.setSystemInfo("email", prop.getProperty("validEmail"));
	extentReport.setSystemInfo("password", prop.getProperty("validPassword"));
	extentReport.setSystemInfo("operating system", System.getProperty("os.name"));
	extentReport.setSystemInfo("ops version detail", System.getProperty("os.version"));
	extentReport.setSystemInfo("SDET Name", System.getProperty("user.name"));
	extentReport.setSystemInfo("java version", System.getProperty("java.version"));
	extentReport.setSystemInfo("java vendor", System.getProperty("java.vendor"));
	
	return extentReport;
	}
}
