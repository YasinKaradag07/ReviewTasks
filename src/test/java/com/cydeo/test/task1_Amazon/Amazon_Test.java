package com.cydeo.test.task1_Amazon;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Amazon_Test {

    @Test
            public  void amazonTest(){
        // The task consists on the next steps:
        //1.	Go to https://www.amazon.com
        Driver.getDriver().get("https://www.amazon.com");

        //2.	Search for "hats for men" (Call from Configuration.properties file)
        BrowserUtils.sleep(2);
        WebElement searchBox = Driver.getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys(ConfigurationReader.getProperty("searchValue")+ Keys.ENTER);

        //3.	Add the first hat appearing to Cart with quantity 2
        WebElement firstHat = Driver.getDriver().findElement(By.xpath("(//img[@class='s-image'])[1]"));
        firstHat.click();

        WebElement quantity = Driver.getDriver().findElement(By.xpath("//select[@id='quantity']"));
        Select select = new Select(quantity);
        select.selectByValue("2");

        WebElement addToCartButton = Driver.getDriver().findElement(By.xpath("//input[@id='add-to-cart-button']"));
        addToCartButton.click();

        //4.	Open cart and assert that the total price and quantity are correct
        WebElement goToCartButton = Driver.getDriver().findElement(By.xpath("//span[@id='sw-gtc']"));
        goToCartButton.click();

        WebElement priceWholePart = Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-whole'])[2]"));
        WebElement priceDecimalPart = Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-fraction'])[2]"));
        Double priceOfHat = Double.parseDouble(priceWholePart.getText()+"."+priceDecimalPart.getText());

        WebElement priceWholePart2 = Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-whole'])[3]"));
        WebElement priceDecimalPart2 = Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-fraction'])[3]"));
        Double totalPrice = Double.parseDouble(priceWholePart2.getText()+"."+priceDecimalPart2.getText());

        Assert.assertEquals(totalPrice,priceOfHat*2);

        WebElement quantityInCart = Driver.getDriver().findElement(By.xpath("//span[@id='sc-subtotal-label-activecart']"));
        String actualQuantity = quantityInCart.getText();
        String expectedQuantity = "2 items";
        Assert.assertTrue(actualQuantity.contains(expectedQuantity));

        //5.    Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3
        Select select2 = new Select(Driver.getDriver().findElement(By.xpath("//select[@name='quantity']")));
        select2.selectByVisibleText("1");

        BrowserUtils.sleep(3);

        //6.    Assert that the total price and quantity has been correctly changed
        WebElement wholePriceTotal2 = Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-whole'])[3]"));
        WebElement decimalPriceTotal2 = Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-fraction'])[3]"));
        totalPrice=Double.parseDouble(wholePriceTotal2.getText()+"."+decimalPriceTotal2.getText());

        Assert.assertEquals(totalPrice,priceOfHat);

        Driver.closeDriver();

    }


}
