package ru.stqua.pft.addressbook.web.tests.utils.address;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqua.pft.addressbook.web.tests.models.AddressData;
import ru.stqua.pft.addressbook.web.tests.utils.PageInteractor;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressHelper implements PageInteractor {
    public static final String ADD_ADDRESS_URL = "http://localhost/addressbook/edit.php";

    private WebDriver driver;

    public AddressHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void addAddress(AddressData data){
        String dataEntry = data.getFirstName() + Keys.TAB +
                data.getLastName() + Keys.TAB + Keys.TAB + data.getLastName();
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(dataEntry);
        driver.findElement(By.cssSelector("textarea[name='address']")).sendKeys(data.getAddress());
        driver.findElement(By.cssSelector("input[name='home']")).sendKeys(data.getPhone());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(data.getEmail());
        Select select = new Select(driver.findElement(By.cssSelector("select[name='new_group']")));
        select.selectByVisibleText(data.getGroupName());
        driver.findElement(By.cssSelector("input[name='submit']")).click();
    }

    public boolean isElementWithTextExists(String text){
        try{
            return driver.findElement(By.xpath("//td[contains(., \""+ text +"\")]")).isDisplayed();
        }catch (ElementNotFoundException ex){
            return false;
        }
    }

    public void cleanup(){
        driver.findElement(By.cssSelector("input#MassCB")).click();
        driver.findElement(By.cssSelector("input[onclick='DeleteSel()']")).click();
        driver.switchTo().alert().accept();
    }

    @Override
    public void open() {
        driver.get(ADD_ADDRESS_URL);
    }
}
