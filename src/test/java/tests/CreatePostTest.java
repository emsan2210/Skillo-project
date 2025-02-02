package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreatePostPage;
import pages.LoginPage;

import java.io.File;
import java.time.Duration;

public class CreatePostTest extends BaseTest {

    @Test
    public void testCreatePost() {
        // Step 1: Log in to the application
        driver.get("http://training.skillo-bg.com:4300/users/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testusername", "Qwer1234"); // Use valid credentials

        // Wait for successful login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/posts/all"));

        // Step 2: Navigate to the "Create Post" page
        driver.get("http://training.skillo-bg.com:4300/posts/create");

        // Initialize the CreatePostPage
        CreatePostPage createPostPage = new CreatePostPage(driver);

        // Step 3: Provide test data
        String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.jpg"; // Adjust path to your test image
        String caption = "This is an automated test post.";
        boolean isPublic = true; // Set to false if you want the post to be private

        // Step 4: Validate image file and create the post
        File imageFile = new File(imagePath);
        Assert.assertTrue(imageFile.exists(), "File does not exist at: " + imagePath);

        // Step 5: Create the post
        createPostPage.createPost(imagePath, caption, isPublic);

        // Step 6: Wait for the page to redirect to the /users/{id} URL
        try {
            wait.until(ExpectedConditions.urlContains("/users/"));
        } catch (Exception e) {
            Assert.fail("Post creation did not redirect to a '/users/{id}' URL within the timeout. Current URL: " + driver.getCurrentUrl());
        }

        // Step 7: Assert that the URL contains "/users/"
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/"), "Post creation did not navigate to a '/users/{id}' URL!");

        // Optional: Add further assertions to verify the post creation, such as checking the caption or image on the redirected page
    }
}
