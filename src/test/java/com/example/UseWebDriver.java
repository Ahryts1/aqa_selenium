package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class UseWebDriver {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TitleTest() {
        driver.get("https://www.google.com/?hl=en-US");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Accept all']"))).click();

        Assert.assertEquals(driver.getTitle(), "Google", "Strings aren`t matching");
    }

    @Test
    public void SearchForLinkedinTest() {
        driver.get("https://www.google.com/?hl=en-US");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Accept all']"))).click();

        driver.findElement(By.name("q")).sendKeys("Linkedin");
        new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.findElement(By.name("btnK"));
        new WebDriverWait(driver,  Duration.ofSeconds(7)).until(ExpectedConditions.elementToBeClickable(By.name("btnK"))).click();

        WebDriverWait resultsWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        resultsWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a/h3")));

        WebDriverWait ListWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = ListWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a/h3")));
        WebElement firstElement = elements.get(0);

        String actualText = firstElement.getText();
        String expectedText = "LinkedIn: Log In or Sign Up";
        Assert.assertEquals(actualText, expectedText, "Up");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}