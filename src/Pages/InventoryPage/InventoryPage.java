package Pages.InventoryPage;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import Utils.Log;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public String inventoryPageUrl = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartBackPack;
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addToCartBikeLight;
    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addToCartFleeceJacket;


    public void addToCart(String item){
        driver.get(inventoryPageUrl);
        if (item.equalsIgnoreCase("bikeLight")) {
            addToCartBikeLight.click();
            Log.info("bike light added to cart!");
        }
        else if (item.equalsIgnoreCase("backPack")){
            addToCartBackPack.click();
            Log.info("back Pack added to cart!");
        }
        else if (item.equalsIgnoreCase("FleeceJacket")){
            addToCartFleeceJacket.click();
            Log.info("Fleece Jacket added to cart!");
        }

    }
}
