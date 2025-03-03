package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class CreatePostPage extends BasePage {

    @FindBy(css = "input[formcontrolname='coverUrl'][type='file']")
    private WebElement uploadImageField;

    @FindBy(css = "[formcontrolname='caption']")
    private WebElement captionField;

    @FindBy(css = "input[formcontrolname='postStatus'][type='checkbox']")
    private WebElement publicPrivateCheckbox;

    @FindBy(css = "button[type='submit']")
    private WebElement createPostButton;

    @FindBy(css = ".image-preview")
    private WebElement imagePreview;

    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Uploads an image using the file input field.
     *
     * @param imagePath The absolute path of the image file to upload.
     */
    private void uploadImage(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists()) {
            throw new RuntimeException("Image file does not exist at: " + imagePath);
        }

        try {
            // Use BasePage's wait utilities to ensure field is ready
            setFilePath(uploadImageField, imagePath);
        } catch (Exception e) {
            // Use JavaScript as a fallback
            System.out.println("Standard file upload failed. Attempting JavaScript upload.");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", uploadImageField);
            setFilePath(uploadImageField, imagePath);
        }
    }

    /**
     * Toggles the public/private status of the post.
     *
     * @param isPublic If true, sets the post to public. If false, sets it to private.
     */
    private void togglePublicPrivate(boolean isPublic) {
        boolean isCurrentlyChecked = publicPrivateCheckbox.isSelected();

        if (isPublic != isCurrentlyChecked) {
            click(publicPrivateCheckbox);
        }
    }

    /**
     * Verifies if the image is uploaded by checking the preview.
     */
    private void verifyImageUploaded() {
        if (isElementVisible(imagePreview)) {
            System.out.println("Image uploaded successfully.");
        } else {
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
        uploadImage(imagePath);
        verifyImageUploaded();
        typeText(captionField, caption);
        togglePublicPrivate(isPublic);
        click(createPostButton);
    }
}
