package Pages;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import Utils.Log;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String CartPageUrl = "https://www.saucedemo.com/cart.html";

    @FindBy(className = "shopping_cart_link")
    private WebElement Cart;

    public void goToCartPage(){
        Cart.click();
        //driver.get(CartPageUrl);
    }


}