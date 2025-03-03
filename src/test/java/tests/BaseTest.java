package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Check and create the reports folder if it doesn't exist
        createReportsFolder();

        // Automatically setup ChromeDriver for all OS
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open the website
        driver.get("http://training.skillo-bg.com:4300/posts/all");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to check and create the reports folder
    private void createReportsFolder() {
        // Define the path to the reports directory
        Path reportsDir = Paths.get("./reports");

        // Check if the reports folder exists
        if (Files.notExists(reportsDir)) {
            try {
                // Create the reports folder if it doesn't exist
                Files.createDirectories(reportsDir);
                System.out.println("Reports folder created at: " + reportsDir.toAbsolutePath());
            } catch (Exception e) {
                System.err.println("Failed to create reports folder: " + e.getMessage());
            }
        } else {
            System.out.println("Reports folder already exists at: " + reportsDir.toAbsolutePath());
        }
    }
}
