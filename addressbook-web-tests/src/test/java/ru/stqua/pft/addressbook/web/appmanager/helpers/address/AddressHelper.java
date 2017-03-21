package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;

import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressHelper extends BaseHelper implements PageInteractor {
    public static final String ADD_ADDRESS_URL = "http://localhost/addressbook/edit.php";

    public AddressHelper(WebDriver driver) {
        super(driver);
    }

    public void addAddress(AddressData data){
        String dataEntry = data.getFirstName() + Keys.TAB +
                data.getLastName() + Keys.TAB + Keys.TAB + data.getLastName();
        find("input[name='firstname']").sendKeys(dataEntry);
        find("textarea[name='address']").sendKeys(data.getAddress());
        find("input[name='home']").sendKeys(data.getPhone());
        find("input[name='email']").sendKeys(data.getEmail());
        Select select = new Select(find("select[name='new_group']"));
        select.selectByVisibleText(data.getGroupName());
        click("input[name='submit']");
    }

    public boolean isElementWithTextExists(String text){
        try{
            return findByXpath("//td[contains(., \""+ text +"\")]").isDisplayed();
        }catch (ElementNotFoundException ex){
            return false;
        }
    }

    public void openEditAddressWithName(String name){
        WebElement row = getRowContainingName(name);
        row.findElement(By.cssSelector("img[title='Edit']")).click();
    }

    public WebElement getRowContainingName(String name){
        return findByXpath("//tr[contains(., '"+name+"')]");
    }

    public boolean isAddressesPresented(){
        List<WebElement> list = findAll("tr[name='entry']");
        if(list.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    public void editFirstName(String newName){
        WebElement firstNameInput = find("input[name='firstname']");
        int lengthOfName = firstNameInput.getAttribute("value").length();
        for (int i = 0; i < lengthOfName; i++){
            firstNameInput.sendKeys(Keys.BACK_SPACE);
        }
        firstNameInput.sendKeys(newName);
        click("input[name='update']");
    }

    public void cleanup(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        click("input#MassCB");
        click("input[onclick='DeleteSel()']");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }



    @Override
    public void open() {
        driver.get(ADD_ADDRESS_URL);
    }
}
