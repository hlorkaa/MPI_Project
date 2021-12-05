package com.example.MPI_Project.testHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class AuthTestHelper {

    public static void auth(WebDriver driver, String login, String password) {
        driver.get("http://localhost:8080/main");
        driver.manage().window().setSize(new Dimension(1530,830));
        driver.findElement(By.name("task_loginButton")).click();
        driver.findElement(By.cssSelector("td:nth-child(1) > form")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("exitButton")).click();
    }
}
