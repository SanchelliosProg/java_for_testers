package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;

import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressHelper extends BaseHelper implements PageInteractor {
    public static final String ADD_ADDRESS_URL = "http://localhost/addressbook/edit.php";

    private final String FIRST_NAME_INPUT_CSS = "input[name='firstname']";
    private final String LAST_NAME_INPUT_CSS = "input[name='lastname']";

    private final String ADDRESS_INPUT_CSS = "textarea[name='address']";
    private final String HOME_INPUT_CSS = "input[name='home']";
    private final String EMAIL_INPUT_CSS = "input[name='email']";

    private final String GROUP_SELECT_CSS = "select[name='new_group']";

    private final String UPDATE_BUTTON_CSS = "input[name='update']";
    private final String SUBMIT_BUTTON = "input[name='submit']";
    private final String DELETE_BUTTON_CSS = "input[onclick='DeleteSel()']";

    private final String LIST_OF_ADDRESSES_CSS = "tr[name='entry']";

    public AddressHelper(WebDriver driver) {
        super(driver);
    }

    public void addAddress(AddressData data){
        find(FIRST_NAME_INPUT_CSS).sendKeys(data.getFirstName());
        find(LAST_NAME_INPUT_CSS).sendKeys(data.getLastName());
        find(ADDRESS_INPUT_CSS).sendKeys(data.getAddress());
        find(HOME_INPUT_CSS).sendKeys(data.getPhone());
        find(EMAIL_INPUT_CSS).sendKeys(data.getEmail());
        selectGroup(data);
        click(SUBMIT_BUTTON);
    }

    public void editAddressWithName(String addressName, AddressData newData){
        NavigationHelper nh = new NavigationHelper(driver);
        nh.openMainPage();
        WebElement row = findByXpath("//tr[contains(., \""+ addressName +"\")]");
        row.findElement(By.cssSelector("img[title='Edit']")).click();
        editFirstName(newData.getFirstName());
        editLastName(newData.getLastName());
        editAddress(newData.getAddress());
        editHomePhone(newData.getPhone());
        editEmail(newData.getEmail());
        click(UPDATE_BUTTON_CSS);
    }

    public void editFirstName(String newName){
        editInputField(FIRST_NAME_INPUT_CSS, newName);
    }

    public void editLastName(String lastName){
        editInputField(LAST_NAME_INPUT_CSS, lastName);
    }

    public void editAddress(String address){
        editInputField(ADDRESS_INPUT_CSS, address);
    }

    public void editHomePhone(String homePhone){
        editInputField(HOME_INPUT_CSS, homePhone);
    }

    public void editEmail(String email){
        editInputField(EMAIL_INPUT_CSS, email);
    }

    public void selectGroup(AddressData data) {
        Select select = new Select(find(GROUP_SELECT_CSS));
        select.selectByVisibleText(data.getGroupName());
    }

    public boolean isElementWithTextExists(String text){
        WebElement we = findByXpath("//td[contains(., \""+ text +"\")]");
        return we != null;
    }

    public void openEditAddressWithName(String name){
        WebElement row = getRowContainingName(name);
        row.findElement(By.cssSelector("img[title='Edit']")).click();
    }

    public WebElement getRowContainingName(String name){
        return findByXpath("//tr[contains(., '"+name+"')]");
    }

    public boolean isAddressesPresented(){
        List<WebElement> list = findAll(LIST_OF_ADDRESSES_CSS);
        if(list.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    public void deleteFirstAddress(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement firstElement = find(LIST_OF_ADDRESSES_CSS);
        firstElement.findElement(By.cssSelector("input[type='checkbox']")).click();
        click(DELETE_BUTTON_CSS);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void cleanup(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        click("input#MassCB");
        click(DELETE_BUTTON_CSS);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Override
    public void open() {
        driver.get(ADD_ADDRESS_URL);
    }
}
