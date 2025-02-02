package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class CreatePostPage extends BasePage {

    @FindBy(css = "input[formcontrolname='coverUrl'][type='file']") // File input field
    private WebElement uploadImageField;

    @FindBy(css = "[formcontrolname='caption']") // Caption input field
    private WebElement captionField;

    @FindBy(css = "input[formcontrolname='postStatus'][type='checkbox']") // Checkbox for public/private
    private WebElement publicPrivateCheckbox;

    @FindBy(css = "button[type='submit']") // Submit button to create the post
    private WebElement createPostButton;

    @FindBy(css = ".image-preview") // Optional: Preview image displayed after upload
    private WebElement imagePreview;

    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Uploads an image and validates the file exists.
     *
     * @param imagePath The absolute path of the image file to upload.
     */
    private void uploadImage(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists()) {
            throw new RuntimeException("Image file does not exist at: " + imagePath);
        }

        try {
            // Attempt to upload the image
            uploadImageField.sendKeys(imagePath);
            System.out.println("File uploaded successfully: " + imagePath);
        } catch (Exception e) {
            // Fallback to JavaScript if standard upload fails
            System.out.println("Standard file upload failed. Attempting JavaScript upload.");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", uploadImageField);
            uploadImageField.sendKeys(imagePath);
        }
    }

    /**
     * Toggles the public/private status of the post.
     *
     * @param isPublic If true, sets the post to public (checked). If false, sets it to private (unchecked).
     */
    private void togglePublicPrivate(boolean isPublic) {
        boolean isCurrentlyChecked = publicPrivateCheckbox.isSelected();
        if (isPublic && !isCurrentlyChecked) {
            publicPrivateCheckbox.click(); // Check the box for public
        } else if (!isPublic && isCurrentlyChecked) {
            publicPrivateCheckbox.click(); // Uncheck the box for private
        }
    }

    /**
     * Verifies if the image is uploaded by checking the preview.
     */
    private void verifyImageUploaded() {
        if (imagePreview != null && !imagePreview.isDisplayed()) {
            throw new RuntimeException("Image upload failed: Preview is not visible.");
        }
    }

    /**
     * Creates a post by uploading an image, setting a caption, toggling public/private, and submitting the form.
     *
     * @param imagePath The absolute path of the image file to upload.
     * @param caption   The caption text for the post.
     * @param isPublic  True if the post should be public, false otherwise.
     */
    public void createPost(String imagePath, String caption, boolean isPublic) {
        // Upload image
        uploadImage(imagePath);

        // Optionally, verify the image upload (if preview exists in your application)
        verifyImageUploaded();

        // Enter caption
        captionField.sendKeys(caption);

        // Set privacy toggle
        togglePublicPrivate(isPublic);

        // Submit the form
        createPostButton.click();
    }
}
