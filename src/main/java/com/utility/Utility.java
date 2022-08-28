package com.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utility {
	public static WebDriver driver;
	private static Properties properties = null;
	public static Date dt;
	public static SimpleDateFormat dateFormat;

	public static int random(int lowerBound, int upperBound) {
		return (lowerBound + (int) Math.round(Math.random() * (upperBound - lowerBound)));
	}


	public static String getConfigProperty(String propertyName) throws IOException {

		if (properties == null) {
			loadProperties();
		}
		return properties.getProperty(propertyName);
	}

	public static void loadProperties() throws IOException {
		InputStream instream = Utility.class.getClassLoader().getResourceAsStream("config.properties");
		if (instream != null) {
			properties = new Properties();
			properties.load(instream);
		}
	}

	public static String getCurrentTime() {
		dt = new Date();
		dateFormat = new SimpleDateFormat("yyyyddMMhhmmss");
		return String.valueOf(dateFormat.format(dt));
	}

	public static String formatDateAndTime(Date dateValue, String format) {
		try {
			SimpleDateFormat ft = new SimpleDateFormat(format);
			String formatedDateValue = ft.format(dateValue);
			return formatedDateValue;
		} catch (Exception e) {
			return null;
		}
	}

	public static void takeSnapShotOfPage(String destPath, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(destPath);
		FileUtils.copyFile(source, destination);
	}
}
