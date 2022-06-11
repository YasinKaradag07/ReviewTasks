package com.cydeo.test.task2_MoneyGaming;

import com.cydeo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MoneyGaming_Test {

    @Test
    public void money_gaming_test(){
        //Task 2:
        //1. Navigate to: https://moneygaming.qa.gameaccount.com/
        Driver.getDriver().get("https://moneygaming.qa.gameaccount.com/");

        //2. Click the JOIN NOW button to open the registration page
        WebElement joinButton = Driver.getDriver().findElement(By.xpath("//a[@class='newUser green']"));
        joinButton.click();

        //3. Select a title value from the dropdown
        WebElement selectTitle = Driver.getDriver().findElement(By.xpath("//select[@name='map(title)']"));
        Select select = new Select(selectTitle);
        select.selectByValue("Mr");

        //4. Enter your first name and surname in the form
        WebElement firstName = Driver.getDriver().findElement(By.xpath("//input[@id='forename']"));
        firstName.sendKeys("Yasin"+ Keys.ENTER);
        WebElement lastName = Driver.getDriver().findElement(By.xpath("//input[@name='map(lastName)']"));
        lastName.sendKeys("Karadag"+Keys.ENTER);

        //5. Check the tick box with text 'I accept the Terms and Conditions and certify that I am over
        //the age of 18.'
        WebElement checkBox = Driver.getDriver().findElement(By.xpath("//input[@id='checkbox']"));
        checkBox.click();

        //6. Submit the form by clicking the JOIN NOW button
        WebElement submitButton = Driver.getDriver().findElement(By.xpath("//input[@id='form']"));
        submitButton.click();

        //7. Validate that a validation message with text ‘ This field is required’ appears under the date of
        //birth box
        WebElement errorMessageOfBirthDate = Driver.getDriver().findElement(By.xpath("//label[@for='dob']"));
        String actualErrorMessage = errorMessageOfBirthDate.getText();
        String expectedErrorMessage = "This field is required";
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);


    }

}
