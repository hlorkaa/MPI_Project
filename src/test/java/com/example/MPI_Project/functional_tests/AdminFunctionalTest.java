package com.example.MPI_Project.functional_tests;// Generated by Selenium IDE

import com.example.MPI_Project.testHelpers.AuthTestHelper;
import com.example.MPI_Project.testHelpers.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class AdminFunctionalTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = TestUtils.getDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulAuthorizationTest() {
        driver.get("http://localhost:8080/main");
        driver.manage().window().setSize(new Dimension(1550, 838));
        driver.findElement(By.name("task_loginButton")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("admin");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("p:nth-child(1)")).click();
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("admin"));
        driver.findElement(By.name("exitButton")).click();
    }

    @Test
    public void addNewUserTest() {
        AuthTestHelper.auth(driver, "admin", "admin");
        driver.findElement(By.name("task_adminButton")).click();
        while(!driver.findElements(By.cssSelector("span")).isEmpty()){
            driver.findElement(By.name("user_deleteButton")).click();
        }
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("workman");
        driver.findElement(By.cssSelector("p:nth-child(6)")).click();
        driver.findElement(By.id("role_WORKMAN")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("workman");
        driver.findElement(By.cssSelector("td:nth-child(1) > form")).click();
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("span")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("workman"));
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("WORKMAN"));
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("manager");
        driver.findElement(By.id("role_MANAGER")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("manager");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser > span")).getText(), is("manager"));
        driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser > i")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser > i")).getText(), is("MANAGER"));
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("consultant");
        driver.findElement(By.id("role_CONSULTANT")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("consultant");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("tr:nth-child(3) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) span")).getText(), is("consultant"));
        driver.findElement(By.cssSelector("tr:nth-child(3) i")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) i")).getText(), is("CONSULTANT"));
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("security");
        driver.findElement(By.id("role_SECURITY")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("security");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) span")).getText(), is("security"));
        driver.findElement(By.cssSelector("tr:nth-child(4) i")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) i")).getText(), is("SECURITY"));
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("account");
        driver.findElement(By.id("role_ACCOUNT")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("account");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("tr:nth-child(5) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(5) span")).getText(), is("account"));
        driver.findElement(By.cssSelector("tr:nth-child(5) > .colUser:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(5) i")).getText(), is("ACCOUNT"));
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();
    }

}
