package Tests;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.CheckoutPageStepTwo;
import Pages.InventoryPage.InventoryPage;
import Pages.Login.LoginPage;
import Utils.Log;
import org.testng.annotations.Test;

public class CheckoutTests extends TestBase{
    private String validUserName = "standard_user";
    private String validPassword = "secret_sauce";
    private String expectedErrorMessage = "Error: Postal Code is required";

    @Test(priority = 1)
    public void testCheckoutStepOneErrorValidation() {

        Log.info("Start Test Case: TestCheckoutStepOneErrorValidation");

        Log.info("login...");

        LoginPage loginPage =  new LoginPage(driver);
        loginPage.Login(validUserName, validPassword);
        InventoryPage inventoryPage = new InventoryPage(driver);
        loginPage.VerifyCurrentUrl(inventoryPage.inventoryPageUrl);


        Log.info("Proceed with checkout after successful login");


        // after Login
        inventoryPage.addToCart("bikeLight");


        //go to cart page
        CartPage cartPage = new CartPage(driver);
        cartPage.goToCartPage();
        loginPage.VerifyCurrentUrl(cartPage.CartPageUrl);

        // Proceed with checkout after successful login
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutOne();
        loginPage.VerifyCurrentUrl(checkoutPage.CheckoutPageUrl);
        // Enter a valid name & Last name
        checkoutPage.ErrorMessageDisplayed();
        checkoutPage.VerifyErrorMessageText(expectedErrorMessage);
        Log.info("Expexted Error 'Postal Code is Required' Verified!");

    }

    @Test(priority = 2)
    public void CheckoutStepTwoValidation() {

        Log.info("Start Test Case: TestCheckoutStepTwoValidation");

        Log.info("login...");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(validUserName, validPassword);
        InventoryPage inventoryPage = new InventoryPage(driver);
        loginPage.VerifyCurrentUrl(inventoryPage.inventoryPageUrl);

        Log.info("Proceed with checkout after successful login");

        // after Login
        inventoryPage.addToCart("backPack");
        inventoryPage.addToCart("FleeceJacket");

        //go to cart page
        CartPage cartPage = new CartPage(driver);
        cartPage.goToCartPage();
        loginPage.VerifyCurrentUrl(cartPage.CartPageUrl);

        // Proceed with checkout after successful login

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkoutTwo();

        //checkout step two
        CheckoutPageStepTwo checkoutPageStepTwo = new CheckoutPageStepTwo(driver);
        checkoutPageStepTwo.VerifyCurrentUrl(checkoutPageStepTwo.CheckoutPageStepTwoUrl);

        //verify The prices of both products
        Log.info("Verify the prices of products");
        checkoutPageStepTwo.verifyProductPrices();


        //verify The value of the total price
        checkoutPageStepTwo.verifyTotalPrice();

   }

}

