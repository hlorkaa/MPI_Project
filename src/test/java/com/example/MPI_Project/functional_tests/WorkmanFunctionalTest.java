package com.example.MPI_Project.functional_tests;// Generated by Selenium IDE

import com.example.MPI_Project.testHelpers.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WorkmanFunctionalTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = TestUtils.GetDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void editStatusTask() {
        driver.get("http://localhost:8080/main");
        driver.manage().window().setSize(new Dimension(1530,829));
        driver.findElement(By.name("task_loginButton")).click();
        driver.findElement(By.cssSelector("td:nth-child(1) > form")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("workman");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("workman");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_workButton")).click();
        driver.findElement(By.name("task_changeButton")).click();
        driver.findElement(By.cssSelector("i")).click();
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Приостановлено"));
        driver.findElement(By.cssSelector("form > p:nth-child(2)")).click();
        driver.findElement(By.id("taskStatus_problems")).click();
        driver.findElement(By.cssSelector("p:nth-child(5) > input")).click();
        driver.findElement(By.cssSelector(".colTask:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Возникли проблемы"));
        driver.findElement(By.cssSelector("td > .colTask")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();
    }

}