package com.example.MPI_Project.interface_tests;// Generated by Selenium IDE

import com.example.MPI_Project.testHelpers.AuthTestHelper;
import com.example.MPI_Project.testHelpers.PgClient;
import com.example.MPI_Project.testHelpers.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminInterfaceTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = TestUtils.getDriver();
        AuthTestHelper.auth(driver,"admin", "admin");
    }

    @After
    public void tearDown() {
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.quit();
    }

    @Test
    public void basicInterfaceTest() {
        driver.findElement(By.name("task_adminButton")).click();
        driver.findElement(By.cssSelector("tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")).click();
        driver.findElement(By.cssSelector("tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")).click();
        assertThat(driver.findElement(By.cssSelector("tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")).getText(), is("Пользователи"));
        driver.findElement(By.cssSelector("th:nth-child(1)")).click();
        assertThat(driver.findElement(By.cssSelector("th:nth-child(1)")).getText(), is("ИД"));
        driver.findElement(By.cssSelector("th:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("th:nth-child(2)")).getText(), is("Имя пользователя"));
        driver.findElement(By.cssSelector("th:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("th:nth-child(3)")).getText(), is("Должность"));
        {
            String value = driver.findElement(By.name("exitButton")).getAttribute("value");
            assertThat(value, is("Выход"));
        }
        addUsers();
        driver.findElement(By.cssSelector("tr:nth-child(1) > .colUser:nth-child(1)")).click();
        driver.findElement(By.cssSelector("span")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("workman"));
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("WORKMAN"));
        driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser > span")).getText(), is("manager"));
        driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser > i")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > .colUser > i")).getText(), is("MANAGER"));
        driver.findElement(By.cssSelector("tr:nth-child(3) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) span")).getText(), is("consultant"));
        driver.findElement(By.cssSelector("tr:nth-child(3) i")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) i")).getText(), is("CONSULTANT"));
        driver.findElement(By.cssSelector("tr:nth-child(4) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) span")).getText(), is("security"));
        driver.findElement(By.cssSelector("tr:nth-child(4) i")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) i")).getText(), is("SECURITY"));
        driver.findElement(By.cssSelector("tr:nth-child(5) > .colUser:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(5) span")).getText(), is("account"));
        driver.findElement(By.cssSelector("tr:nth-child(5) > .colUser:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(5) i")).getText(), is("ACCOUNT"));
        driver.findElement(By.cssSelector("tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)")).click();
        assertThat(driver.findElement(By.name("user_deleteButton")).getText(), is("удалить"));
        assertThat(driver.findElement(By.name("task_signUpButton")).getText(), is("Зарегистрировать\nпользователя"));
    }

    @Test
    public void addUserTest() {
        driver.findElement(By.name("task_adminButton")).click();
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).getText(), is("Регистрация"));
        driver.findElement(By.cssSelector("p:nth-child(1) > label")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(1) > label")).getText(), is("Имя пользователя"));
        driver.findElement(By.cssSelector("p:nth-child(1)")).click();
        {
            WebElement element = driver.findElement(By.id("username"));
            Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
            assertTrue(isEditable);
        }
        driver.findElement(By.cssSelector("p:nth-child(3) > label")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(3) > label")).getText(), is("Должность"));
        assertThat(driver.findElement(By.cssSelector("p:nth-child(4) > label")).getText(), is("Сотрудник"));
        assertTrue(driver.findElement(By.id("role_WORKMAN")).isSelected());
        assertThat(driver.findElement(By.cssSelector("p:nth-child(5) > label")).getText(), is("Бухгалтер"));
        assertFalse(driver.findElement(By.id("role_ACCOUNT")).isSelected());
        assertThat(driver.findElement(By.cssSelector("p:nth-child(6) > label")).getText(), is("Охранник"));
        assertFalse(driver.findElement(By.id("role_SECURITY")).isSelected());
        assertThat(driver.findElement(By.cssSelector("p:nth-child(7) > label")).getText(), is("Консультант"));
        assertFalse(driver.findElement(By.id("role_CONSULTANT")).isSelected());
        assertThat(driver.findElement(By.cssSelector("p:nth-child(8) > label")).getText(), is("Менеджер"));
        assertFalse(driver.findElement(By.id("role_MANAGER")).isSelected());
        assertThat(driver.findElement(By.cssSelector("p:nth-child(9) > label")).getText(), is("Пароль"));
        {
            WebElement element = driver.findElement(By.id("password"));
            Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
            assertTrue(isEditable);
        }
        {
            String value = driver.findElement(By.name("exitButton")).getAttribute("value");
            assertThat(value, is("Выход"));
        }
        assertThat(driver.findElement(By.cssSelector("button")).getText(), is("Зарегистрировать"));
        driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).click();
    }

    public void addUsers() {
        while(!driver.findElements(By.cssSelector("span")).isEmpty()){
            driver.findElement(By.name("user_deleteButton")).click();
        }
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("workman");
        driver.findElement(By.id("role_WORKMAN")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("workman");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("manager");
        driver.findElement(By.id("role_MANAGER")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("manager");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("consultant");
        driver.findElement(By.id("role_CONSULTANT")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("consultant");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("security");
        driver.findElement(By.id("role_SECURITY")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("security");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("task_signUpButton")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("account");
        driver.findElement(By.id("role_ACCOUNT")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("account");
        driver.findElement(By.cssSelector("button")).click();
    }
}
