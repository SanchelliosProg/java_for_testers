package ru.stqua.pft.addressbook.web.appmanager.helpers;

import org.openqa.selenium.*;

import java.io.File;
import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class BaseHelper {
    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator){
        try{
            return driver.findElement(locator);
        }catch (NoSuchElementException ex){
            return null;
        }
    }

    public WebElement find(String locator){
        try{
            return driver.findElement(By.cssSelector(locator));
        }catch (NoSuchElementException ex){
            return null;
        }
    }

    public List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    @Deprecated
    public WebElement findByXpath(String xpath){
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException ex){
            return null;
        }
    }

    public void type(By locator, String text){
        if(text != null){
            click(locator);
            String existingText = getAttributeValue(locator);
            if (!existingText.equals(text)){
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file){
        if(file != null){
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public String getAttributeValue(By locator){
        return find(locator).getAttribute("value");
    }

    public void click(By locator){
        find(locator).click();
    }

    public void click(String css) {
        find(By.cssSelector(css)).click();
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
