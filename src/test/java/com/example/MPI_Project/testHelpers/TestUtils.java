package com.example.MPI_Project.testHelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestUtils {
    private static final String CHROME_PATH = "C:\\Users\\moks\\IdeaProjects\\MPI_Project\\src\\test\\resources\\chromedriver.exe";
    private static final String FIREFOX_PATH = "C:\\Users\\moks\\IdeaProjects\\MPI_Project\\src\\test\\resources\\geckodriver.exe";

//   public static WebDriver GetDriver() {
//        System.setProperty("webdriver.chrome.driver", CHROME_PATH);
//        return new ChromeDriver();
//    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);
        return new FirefoxDriver();
    }
}
