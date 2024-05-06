package Pages;

import Utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String CheckoutPageUrl = "https://www.saucedemo.com/checkout-step-one.html";

    @FindBy(id ="checkout")
    private WebElement checkoutButton;
    @FindBy(id = "first-name")
    private WebElement NameInput;
    @FindBy(id = "last-name")
    private WebElement LastNameInput;
    @FindBy(id ="postal-code")
    private WebElement PostalCode;
    @FindBy(id = "continue")
    private WebElement ContinueButton;
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public void checkoutOne(){
        checkoutButton.click();
        NameInput.sendKeys("Dimitrios");
        LastNameInput.sendKeys("Toumpas");
        ContinueButton.click();
    }

    public void checkoutTwo(){
        checkoutButton.click();
        NameInput.sendKeys("Dimitios");
        LastNameInput.sendKeys("Toumpas");
        PostalCode.sendKeys("55337");
        ContinueButton.click();
    }

    public void ErrorMessageDisplayed() {
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed on the page.");
        VerifyCurrentUrl(CheckoutPageUrl);
    }

    public void VerifyErrorMessageText(String expectedErrorMessage) {
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message text does not match.");
    }
}
