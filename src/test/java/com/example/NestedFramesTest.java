package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NestedFramesTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testNestedFrames() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        WebElement topFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(topFrame);

        WebElement leftFrame = driver.findElement(By.name("frame-left"));
        driver.switchTo().frame(leftFrame);

        String frameText = driver.findElement(By.tagName("body")).getText();
        Assert.assertEquals(frameText, "LEFT", "Incorrect text in the left frame");

        driver.switchTo().defaultContent();

        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        driver.switchTo().frame(middleFrame);

        frameText = driver.findElement(By.tagName("body")).getText();
        Assert.assertEquals(frameText, "MIDDLE", "Incorrect text in the centre frame");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
