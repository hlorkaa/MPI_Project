package com.example.MPI_Project.business_cycle_tests;

import com.example.MPI_Project.testHelpers.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TaskBusinessCycleTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = TestUtils.getDriver();
    }

    @After
    public void tearDown() {
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.quit();
    }

    @Test
    public void taskBusinessCycleTest() {
        driver.get("http://localhost:8080/main");
        driver.manage().window().setSize(new Dimension(1530,830));
        driver.findElement(By.name("task_loginButton")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("manager");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("manager");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_managButton")).click();
        while(!driver.findElements(By.cssSelector("span")).isEmpty()){
            driver.findElement(By.name("task_deleteButton")).click();
        }

        //create task
        driver.findElement(By.name("newTask_name")).click();
        driver.findElement(By.name("newTask_name")).sendKeys("Тестовая задача");
        driver.findElement(By.name("newTask_deadline")).click();
        driver.findElement(By.name("newTask_deadline")).sendKeys("2021-12-31");
        driver.findElement(By.name("newTask_status")).click();
        driver.findElement(By.name("newTask_description")).click();
        driver.findElement(By.name("newTask_description")).sendKeys("Описание тестовой задачи");
        driver.findElement(By.cssSelector("select:nth-child(5)")).click();
        driver.findElement(By.cssSelector("select:nth-child(5) > option")).click();
        driver.findElement(By.cssSelector("p:nth-child(6) > input:nth-child(1)")).click();

        //check created task
        driver.findElement(By.cssSelector("tr > .colTask:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Тестовая задача"));
        driver.findElement(By.cssSelector(".colTask:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Утверждено"));
        driver.findElement(By.name("task_changeButton")).click();
        driver.findElement(By.name("task_name")).click();
        {
            String value = driver.findElement(By.name("task_name")).getAttribute("value");
            assertThat(value, is("Тестовая задача"));
        }
        driver.findElement(By.name("task_deadline")).click();
        {
            String value = driver.findElement(By.name("task_deadline")).getAttribute("value");
            assertThat(value, is("2021-12-31"));
        }
        driver.findElement(By.name("task_description")).click();
        {
            String value = driver.findElement(By.name("task_description")).getAttribute("value");
            assertThat(value, is("Описание тестовой задачи"));
        }
        driver.findElement(By.cssSelector("form:nth-child(3) > p > input")).click();

        //edit task
        driver.findElement(By.cssSelector(".colTask:nth-child(5)")).click();
        driver.findElement(By.name("task_changeButton")).click();
        driver.findElement(By.name("task_name")).click();
        driver.findElement(By.name("task_name")).clear();
        driver.findElement(By.name("task_name")).sendKeys("Отредактированная задача");
        driver.findElement(By.name("task_deadline")).click();
        driver.findElement(By.name("task_deadline")).clear();
        driver.findElement(By.name("task_deadline")).sendKeys("2021-12-30");
        driver.findElement(By.id("taskStatus_wip")).click();
        driver.findElement(By.name("task_description")).click();
        driver.findElement(By.name("task_description")).clear();
        driver.findElement(By.name("task_description")).sendKeys("Отредактированное описание тестовой задачи");
        driver.findElement(By.cssSelector("p:nth-child(7) > input")).click();

        //check edited task
        driver.findElement(By.cssSelector("span")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Отредактированная задача"));
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Выполняется"));
        driver.findElement(By.name("task_changeButton")).click();
        driver.findElement(By.name("task_name")).click();
        {
            String value = driver.findElement(By.name("task_name")).getAttribute("value");
            assertThat(value, is("Отредактированная задача"));
        }
        {
            String value = driver.findElement(By.name("task_deadline")).getAttribute("value");
            assertThat(value, is("2021-12-30"));
        }
        driver.findElement(By.name("task_description")).click();
        {
            String value = driver.findElement(By.name("task_description")).getAttribute("value");
            assertThat(value, is("Отредактированное описание тестовой задачи"));
        }
        driver.findElement(By.cssSelector("form:nth-child(3) > p > input")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();

        //login workman
        driver.findElement(By.name("task_loginButton")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("workman");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("workman");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_workButton")).click();

        //check task
        driver.findElement(By.cssSelector("span")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Отредактированная задача"));
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Выполняется"));
        driver.findElement(By.name("task_changeButton")).click();
        driver.findElement(By.name("task_name")).click();
        {
            String value = driver.findElement(By.name("task_name")).getAttribute("value");
            assertThat(value, is("Отредактированная задача"));
        }
        driver.findElement(By.name("task_deadline")).click();
        {
            String value = driver.findElement(By.name("task_deadline")).getAttribute("value");
            assertThat(value, is("2021-12-30"));
        }
        driver.findElement(By.name("task_description")).click();
        {
            String value = driver.findElement(By.name("task_description")).getAttribute("value");
            assertThat(value, is("Отредактированное описание тестовой задачи"));
        }
        driver.findElement(By.cssSelector("form:nth-child(3) > p > input")).click();

        //edit status
        driver.findElement(By.name("task_changeButton")).click();
        driver.findElement(By.id("taskStatus_ready")).click();
        driver.findElement(By.cssSelector("p:nth-child(5) > input")).click();

        //check task after edit status
        driver.findElement(By.cssSelector("tr > .colTask:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Отредактированная задача"));
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Готово"));
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();

        //login manager
        driver.findElement(By.name("task_loginButton")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("manager");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("manager");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_managButton")).click();

        //check task with edited status
        driver.findElement(By.cssSelector("span")).click();
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Отредактированная задача"));
        driver.findElement(By.cssSelector(".colTask:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Готово"));

        //delete task
        driver.findElement(By.name("task_deleteButton")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("span"));
            assert(elements.size() == 0);
        }
    }

}
