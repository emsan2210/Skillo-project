package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

public class DeletePostTest extends BaseTest {

    @Test
    public void testDeletePost() {
        // Step 1: Navigate to Login Page
        driver.get("http://training.skillo-bg.com:4300/users/login");

        // Step 2: Log in using the existing LoginPage class
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testusername", "Qwer1234");

        // Step 3: Wait for successful login (URL should contain '/posts/all')
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/posts/all"));

        // Step 4: Navigate to Profile (href contains '/users/')
        WebElement profileLink = driver.findElement(By.cssSelector("a.nav-link#nav-link-profile"));
        profileLink.click();

        // Step 5: Wait for posts to load on the profile page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".app-post")));

        // Step 6: Check if posts are available to delete
        List<WebElement> posts = driver.findElements(By.cssSelector(".app-post"));
        Assert.assertTrue(posts.size() > 0, "No posts available to delete!");

        // Step 7: Click on the first post to open it
        posts.get(0).click();

        // Step 8: Wait for the delete button to be visible and click it
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.delete-ask"))); // Adjust selector if needed
        deleteButton.click();

        // Step 9: Handle confirmation prompt and click "Yes"
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
        yesButton.click();

        // Step 10: Confirm deletion by checking that the post count has decreased
        driver.navigate().refresh(); // Refresh the profile page to update post list
        List<WebElement> updatedPosts = driver.findElements(By.cssSelector(".post"));
        Assert.assertTrue(updatedPosts.size() < posts.size(), "Post was not deleted successfully!");
    }
}