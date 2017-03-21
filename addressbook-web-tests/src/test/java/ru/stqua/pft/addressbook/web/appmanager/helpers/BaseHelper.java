package ru.stqua.pft.addressbook.web.appmanager.helpers;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class BaseHelper {
    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(String css){
        return driver.findElement(By.cssSelector(css));
    }

    public List<WebElement> findAll(String css){
        return driver.findElements(By.cssSelector(css));
    }

    public WebElement findByXpath(String xpath){
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException ex){
            return null;
        }

    }

    public void click(String css){
        find(css).click();
    }

    protected void clearInputText(WebElement input){
        String inputText = input.getAttribute("value");
        int length = inputText.length();
        for (int i = 0; i < length; i++){
            input.sendKeys(Keys.BACK_SPACE);
        }
    }
}
