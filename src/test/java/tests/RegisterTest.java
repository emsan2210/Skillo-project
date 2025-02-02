package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

import java.time.Duration;

public class RegisterTest extends BaseTest {

    @Test
    public void testSuccessfulRegistration() {
        // Navigate to the registration page
        driver.get("http://training.skillo-bg.com:4300/users/register");

        // Initialize the RegisterPage
        RegisterPage registerPage = new RegisterPage(driver);

        // Generate shorter unique test data
        String randomUsername = "Us" + System.currentTimeMillis() % 1000; // Shorter username
        String randomEmail = "e" + System.currentTimeMillis() % 1000 + "@t.com"; // Shorter email
        String birthDate = "01/01/2000";
        String password = "StrongPass123";
        String publicInfo = "Test account";

        // Perform the registration
        registerPage.register(randomUsername, randomEmail, birthDate, password, password, publicInfo);

        // Wait for the URL to update, with a timeout of 10 seconds
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/posts/all"));
        } catch (Exception e) {
            Assert.fail("Registration did not redirect to '/posts/all' within the timeout. Current URL: " + driver.getCurrentUrl());
        }

        // Final assertion to confirm successful registration
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/posts/all"), "Registration was unsuccessful!");
    }
}