package Pages;

import Utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutPageStepTwo extends BasePage {

    public CheckoutPageStepTwo(WebDriver driver) {
        super(driver);
    }

    public String CheckoutPageStepTwoUrl = "https://www.saucedemo.com/checkout-step-two.html";

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;
    @FindBy(css = "div.summary_tax_label[data-test='tax-label']")
    private WebElement taxLabel;
    @FindBy(css = "div.summary_subtotal_label[data-test='subtotal-label']")
    private WebElement subtotalLabel;
    @FindBy(css = "div.summary_total_label[data-test='total-label']")
    private WebElement totalLabel;

    private String backpackPriceTag = "";
    private String fleeceJacketPriceTag = "";

    public double extractNumericValue(String text) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }
        return 0.0;
    }

    public void verifyProductPrices() {

        // Find the cart items
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        // Iterate through the cart items
        for (WebElement cartItem : cartItems) {
            // Find elements within the cart item
            WebElement itemNameElement = cartItem.findElement(By.className("inventory_item_name"));
            WebElement itemPriceElement = cartItem.findElement(By.className("inventory_item_price"));

            // Get the text of item name and price
            String itemName = itemNameElement.getText();
            String itemPrice = itemPriceElement.getText();

            // Print out the item name and price for verification
            Log.info("Item Name: " + itemName);
            Log.info("Item Price: " + itemPrice);

            // Store the price based on the item name
            if (itemName.equals("Sauce Labs Backpack")) {
                backpackPriceTag = itemPrice;
            } else if (itemName.equals("Sauce Labs Fleece Jacket")) {
                fleeceJacketPriceTag = itemPrice;
            }

            // Perform any necessary verification here
            if (itemName.equals("Sauce Labs Backpack") && itemPrice.equals("$29.99")) {
                Log.info("Verification for Sauce Labs Backpack passed.");
            } else if (itemName.equals("Sauce Labs Fleece Jacket") && itemPrice.equals("$49.99")) {
                Log.info("Verification for Sauce Labs Fleece Jacket passed.");
            } else {
                Log.info("Verification failed.");
            }
        }
    }

    public void verifyTotalPrice() {
        double tax= extractNumericValue(taxLabel.getText());
        double totalDisplayed = extractNumericValue(totalLabel.getText());
        double fleeceJacketPrice= extractNumericValue(fleeceJacketPriceTag);
        double backpackPrice= extractNumericValue(backpackPriceTag);
        Log.info("tax is :"+ tax);
        if (tax==6.40) {
            Log.info("Verification tax passed.");
        }else Log.info("Tax Price is Wrong");

        //total value
        double total = tax + fleeceJacketPrice + backpackPrice;
        Log.info("Calculated total is :"+total+" $");
        if (total==totalDisplayed){
            Log.info("Total Price Verified!");
        }else Log.info("Total Price is Wrong");

    }

        public void ErrorMessageDisplayed () {
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed on the page.");
            VerifyCurrentUrl(CheckoutPageStepTwoUrl);
        }

        public void VerifyErrorMessageText (String expectedErrorMessage){
            String actualErrorMessage = errorMessage.getText();
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message text does not match.");
        }
    }