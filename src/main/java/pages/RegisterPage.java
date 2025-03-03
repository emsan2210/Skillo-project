package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(css = "[formcontrolname='email']")
    private WebElement emailField;

    @FindBy(css = "[formcontrolname='birthDate']")
    private WebElement birthDateField;  // For mm/dd/yyyy

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordField;

    @FindBy(css = "[formcontrolname='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(css = "[formcontrolname='publicInfo']")
    private WebElement publicInfoField;  // Public information about the user

    @FindBy(css = "[type='submit']")  // Submit button for registration
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void register(String username, String email, String birthDate, String password, String confirmPassword, String publicInfo) {
        typeText(usernameField, username);
        typeText(emailField, email);
        typeText(birthDateField, birthDate);
        typeText(passwordField, password);
        typeText(confirmPasswordField, confirmPassword);
        typeText(publicInfoField, publicInfo);

        click(registerButton);
    }
}