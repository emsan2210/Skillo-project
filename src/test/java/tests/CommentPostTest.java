package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

public class CommentPostTest extends BaseTest {

    @Test
    public void testAddCommentToPost() {
        // Step 1: Navigate to Login Page
        driver.get("http://training.skillo-bg.com:4300/users/login");

        // Step 2: Log in using the existing LoginPage class
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testusername", "Qwer1234");

        // Step 3: Wait for successful login (URL should contain '/posts/all')
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/posts/all"));

        // Step 4: Navigate to Profile (href contains '/users/')
        WebElement profileLink = driver.findElement(By.cssSelector("a[href*='/users/']"));
        profileLink.click();

        // Step 5: Wait for posts to load on the profile page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".app-post")));

        // Step 6: Check if posts are available
        List<WebElement> posts = driver.findElements(By.cssSelector(".app-post"));
        Assert.assertTrue(posts.size() > 0, "No posts available to comment on!");

        // Step 7: Click on the first post to open it
        posts.get(0).click();

        // Step 8: Wait for the comment input field to be visible
        WebElement commentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='content']")));

        // Step 9: Add a comment
        String commentText = "This is an automated test comment!";
        commentField.sendKeys(commentText );
        commentField.sendKeys( Keys.ENTER);

        // Step 11: Verify the comment was added (wait for the comment to appear)
        WebElement addedComment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'This is an automated test comment!')]")));
        Assert.assertTrue(addedComment.isDisplayed(), "The comment was not added successfully!");
    }
}
