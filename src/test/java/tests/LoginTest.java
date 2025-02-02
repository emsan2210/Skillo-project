package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test
    public void testSuccessfulLogin() {
        driver.get("http://training.skillo-bg.com:4300/users/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testusername", "Qwer1234");

        // Wait for the URL to update, with a timeout of 10 seconds
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/posts/all"));
        } catch (Exception e) {
            Assert.fail("Login did not redirect to '/posts/all' within the timeout. Current URL: " + driver.getCurrentUrl());
        }

        // Final assertion
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/posts/all"), "Login was unsuccessful!");
    }
}