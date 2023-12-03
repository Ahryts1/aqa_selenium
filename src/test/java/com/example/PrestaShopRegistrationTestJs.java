package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class PrestaShopRegistrationTestJs {

    private WebDriver driver;
    private WebDriverWait wait;

    // changes in local
    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testPrestaShopRegistration() {
        driver.get("http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation");

        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.sendKeys("test+790@example.com");

        WebElement createAccountButton = driver.findElement(By.id("SubmitCreate"));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", createAccountButton);

        WebElement createAccountText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Create an account')]")));

        String actualText = createAccountText.getText();
        String expectedText = "CREATE AN ACCOUNT";
        Assert.assertEquals(actualText, expectedText);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
