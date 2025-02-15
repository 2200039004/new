package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Lmslogintest {
    private WebDriver driver;
    private WebDriverWait wait;
        @BeforeTest
        public void setUp() {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        }
    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        driver.get("https://lms.kluniversity.in/login/");
        Thread.sleep(500); // Wait for 2 seconds
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("2200039004");
        Thread.sleep(500); // Wait for 2 seconds
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys("9693311421@Om");
        Thread.sleep(500); // Wait for 2 seconds
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginbtn")));
        loginButton.click();
    }

    @Test(priority = 2)
    public void courseTest() throws InterruptedException{
        driver.get("https://lms.kluniversity.in/my/courses.php");
        Thread.sleep(500);
        WebElement course = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'ADVANCED OBJECT ORIENTED PROGRAMMING')]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", course);
    }


    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void logoutTest() throws InterruptedException {
        Thread.sleep(500); // Wait for 3 seconds before starting logout
        driver.get("https://lms.kluniversity.in/login/logout.php?sesskey=6lGnWwfLeP");
        Thread.sleep(500); // Wait for 2 seconds
        WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'btn-primary')]")));
        logOutButton.click();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File("screenshot.png"));
        System.out.println("Screenshot taken");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null){
            driver.quit();
        }}



}

