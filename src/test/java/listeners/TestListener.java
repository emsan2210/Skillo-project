package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    private static final String SCREENSHOT_FOLDER = "screenshots/";

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((tests.BaseTest) currentClass).driver;

        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filePath = SCREENSHOT_FOLDER + result.getName() + "_" + timestamp + ".png";

        // Ensure the screenshots directory exists
        File screenshotDir = new File(SCREENSHOT_FOLDER);
        if (!screenshotDir.exists()) {
            boolean dirCreated = screenshotDir.mkdirs();
            if (dirCreated) {
                System.out.println("Screenshot directory created: " + SCREENSHOT_FOLDER);
            } else {
                System.err.println("Failed to create screenshot directory: " + SCREENSHOT_FOLDER);
            }
        }

        // Save the screenshot
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
