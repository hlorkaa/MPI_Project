package com.example.MPI_Project.testHelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestUtils {
    static final String ChromePath = "C:\\Users\\moks\\IdeaProjects\\MPI_Project\\src\\test\\resources\\chromedriver.exe";
    static final String FirefoxPath = "C:\\Users\\moks\\IdeaProjects\\MPI_Project\\src\\test\\resources\\geckodriver.exe";

//    static WebDriver GetDriver() {
//        System.setProperty("webdriver.chrome.driver", ChromePath);
//        return new ChromeDriver();
//    }

    public static WebDriver GetDriver() {
        System.setProperty("webdriver.gecko.driver", FirefoxPath);
        return new FirefoxDriver();
    }
}
