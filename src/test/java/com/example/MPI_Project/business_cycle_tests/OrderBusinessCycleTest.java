package com.example.MPI_Project.business_cycle_tests;

import com.example.MPI_Project.testHelpers.AuthTestHelper;
import com.example.MPI_Project.testHelpers.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrderBusinessCycleTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = TestUtils.getDriver();
        AuthTestHelper.auth(driver,"consultant", "consultant");
    }

    @After
    public void tearDown() {
        driver.findElement(By.name("exitButton")).click();
        driver.findElement(By.name("task_logoutButton")).click();
        driver.findElement(By.name("exitButton")).click();
        driver.quit();
    }

    @Test
    public void orderBusinessCycleTest() {
        driver.findElement(By.name("task_conButton")).click();
        while(!driver.findElements(By.cssSelector("span")).isEmpty()){
            driver.findElement(By.name("order_deleteButton")).click();
        }

        //create order
        driver.findElement(By.name("newOrder_name")).click();
        driver.findElement(By.name("newOrder_name")).sendKeys("Тестовый заказ");
        driver.findElement(By.name("newOrder_customer")).click();
        driver.findElement(By.name("newOrder_customer")).sendKeys("Иванов И.И.");
        driver.findElement(By.name("newOrder_deadline")).click();
        driver.findElement(By.name("newOrder_deadline")).sendKeys("2021-12-27");
        driver.findElement(By.name("newOrder_quality")).click();
        driver.findElement(By.name("newOrder_quantity")).click();
        driver.findElement(By.name("newOrder_quantity")).sendKeys("123");
        driver.findElement(By.name("newOrder_notes")).click();
        driver.findElement(By.name("newOrder_notes")).sendKeys("Описание тестового заказа.");
        driver.findElement(By.cssSelector("td:nth-child(2) p:nth-child(6) > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("span")).click();

        //check created order
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Тестовый заказ"));
        driver.findElement(By.cssSelector("i")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Иванов И.И."));
        driver.findElement(By.name("order_changeButton")).click();
        driver.findElement(By.name("order_name")).click();
        {
            String value = driver.findElement(By.name("order_name")).getAttribute("value");
            assertThat(value, is("Тестовый заказ"));
        }
        driver.findElement(By.name("order_customer")).click();
        {
            String value = driver.findElement(By.name("order_customer")).getAttribute("value");
            assertThat(value, is("Иванов И.И."));
        }
        driver.findElement(By.name("order_deadline")).click();
        {
            String value = driver.findElement(By.name("order_deadline")).getAttribute("value");
            assertThat(value, is("2021-12-27"));
        }
        driver.findElement(By.name("order_quantity")).click();
        {
            String value = driver.findElement(By.name("order_quantity")).getAttribute("value");
            assertThat(value, is("123"));
        }
        driver.findElement(By.name("order_notes")).click();
        {
            String value = driver.findElement(By.name("order_notes")).getAttribute("value");
            assertThat(value, is("Описание тестового заказа."));
        }
        driver.findElement(By.cssSelector("form:nth-child(3) > p > input")).click();

        //edit order
        driver.findElement(By.name("order_changeButton")).click();
        driver.findElement(By.name("order_name")).click();
        driver.findElement(By.name("order_name")).clear();
        driver.findElement(By.name("order_name")).sendKeys("Отредактированный заказ");
        driver.findElement(By.name("order_customer")).click();
        driver.findElement(By.name("order_customer")).clear();
        driver.findElement(By.name("order_customer")).sendKeys("Петров П.П.");
        driver.findElement(By.name("order_deadline")).click();
        driver.findElement(By.name("order_deadline")).clear();
        driver.findElement(By.name("order_deadline")).sendKeys("2021-12-31");
        driver.findElement(By.id("orderQuality_top")).click();
        driver.findElement(By.name("order_quantity")).click();
        driver.findElement(By.name("order_quantity")).clear();
        driver.findElement(By.name("order_quantity")).sendKeys("100");
        driver.findElement(By.name("order_notes")).click();
        driver.findElement(By.name("order_notes")).clear();
        driver.findElement(By.name("order_notes")).sendKeys("Отредактированное описание тестового заказа.");
        driver.findElement(By.cssSelector("td:nth-child(1) p:nth-child(6) > input")).click();
        driver.findElement(By.cssSelector("tr > .colOrder:nth-child(2)")).click();

        //check edited order
        assertThat(driver.findElement(By.cssSelector("span")).getText(), is("Отредактированный заказ"));
        driver.findElement(By.cssSelector(".colOrder:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Петров П.П."));
        driver.findElement(By.name("order_changeButton")).click();
        driver.findElement(By.name("order_name")).click();
        {
            String value = driver.findElement(By.name("order_name")).getAttribute("value");
            assertThat(value, is("Отредактированный заказ"));
        }
        driver.findElement(By.name("order_customer")).click();
        {
            String value = driver.findElement(By.name("order_customer")).getAttribute("value");
            assertThat(value, is("Петров П.П."));
        }
        driver.findElement(By.name("order_deadline")).click();
        {
            String value = driver.findElement(By.name("order_deadline")).getAttribute("value");
            assertThat(value, is("2021-12-31"));
        }
        driver.findElement(By.name("order_quantity")).click();
        {
            String value = driver.findElement(By.name("order_quantity")).getAttribute("value");
            assertThat(value, is("100"));
        }
        driver.findElement(By.name("order_notes")).click();
        {
            String value = driver.findElement(By.name("order_notes")).getAttribute("value");
            assertThat(value, is("Отредактированное описание тестового заказа."));
        }
        driver.findElement(By.cssSelector("form:nth-child(3) > p > input")).click();

        //delete order
        driver.findElement(By.name("order_deleteButton")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("span"));
            assert(elements.size() == 0);
        }
    }

}
