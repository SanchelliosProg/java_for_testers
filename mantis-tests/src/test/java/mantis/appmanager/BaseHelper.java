package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class BaseHelper {
    protected WebDriver wd;
    protected ApplicationManager app;

    public BaseHelper(ApplicationManager app) {
        this.wd = app.getDriver();
        this.app = app;
    }

    public WebElement find(By locator){
        try{
            return wd.findElement(locator);
        }catch (NoSuchElementException ex){
            return null;
        }
    }

    public WebElement find(String locator){
        try{
            return wd.findElement(By.cssSelector(locator));
        }catch (NoSuchElementException ex){
            return null;
        }
    }

    public List<WebElement> findAll(By locator){
        return wd.findElements(locator);
    }

    @Deprecated
    public WebElement findByXpath(String xpath){
        try {
            return wd.findElement(By.xpath(xpath));
        } catch (NoSuchElementException ex){
            return null;
        }
    }

    public void type(By locator, String text){
        click(locator);
        if(text != null){
            String existingText = getAttributeValue(locator);
            if (!existingText.equals(text)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file){
        if(file != null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
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
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
