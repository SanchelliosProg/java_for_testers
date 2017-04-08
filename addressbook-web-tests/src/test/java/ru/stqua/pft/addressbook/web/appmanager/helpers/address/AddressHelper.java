package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressHelper extends BaseHelper {
    public static final String ADD_ADDRESS_URL = "http://localhost/addressbook/edit.php";

    private final String INPUT_FIRST_NAME_CSS = "input[name='firstname']";
    private final String INPUT_LAST_NAME_CSS = "input[name='lastname']";

    private final String INPUT_ADDRESS_CSS = "textarea[name='address']";
    private final String INPUT_HOME_CSS = "input[name='home']";
    private final String INPUT_EMAIL_CSS = "input[name='email']";

    private final String SELECT_GROUP_CSS = "select[name='new_group']";

    private final String BUTTON_UPDATE_CSS = "input[name='update']";
    private final String BUTTON_SUBMIT_CSS = "input[name='submit']";


    public AddressHelper(WebDriver driver) {
        super(driver);
    }

    public void addAddress(AddressData data){
        NavigationHelper helper = new NavigationHelper(driver);
        helper.goAddNewAddress();
        find(By.cssSelector(INPUT_FIRST_NAME_CSS)).sendKeys(data.getFirstName());
        find(By.cssSelector(INPUT_LAST_NAME_CSS)).sendKeys(data.getLastName());
        find(By.cssSelector(INPUT_ADDRESS_CSS)).sendKeys(data.getAddress());
        find(By.cssSelector(INPUT_HOME_CSS)).sendKeys(data.getPhone());
        find(By.cssSelector(INPUT_EMAIL_CSS)).sendKeys(data.getEmail());
        selectGroup(data);
        click(By.cssSelector(BUTTON_SUBMIT_CSS));
    }

    public void editAddressWithName(String addressName, AddressData newData){
        NavigationHelper nh = new NavigationHelper(driver);
        nh.openApp();
        WebElement row = find(By.xpath("//tr[contains(., \""+ addressName +"\")]"));
        row.findElement(By.cssSelector("img[title='Edit']")).click();
        editFirstName(newData.getFirstName());
        editLastName(newData.getLastName());
        editAddress(newData.getAddress());
        editHomePhone(newData.getPhone());
        editEmail(newData.getEmail());
        click(By.cssSelector(BUTTON_UPDATE_CSS));
    }

    public void editFirstName(String newName){
        type(By.cssSelector(INPUT_FIRST_NAME_CSS), newName);
    }

    public void editLastName(String lastName){
        type(By.cssSelector(INPUT_LAST_NAME_CSS), lastName);
    }

    public void editAddress(String address){
        type(By.cssSelector(INPUT_ADDRESS_CSS), address);
    }

    public void editHomePhone(String homePhone){
        type(By.cssSelector(INPUT_HOME_CSS), homePhone);
    }

    public void editEmail(String email){
        type(By.cssSelector(INPUT_EMAIL_CSS), email);
    }

    public void selectGroup(AddressData data) {
        Select select = new Select(find(By.cssSelector(SELECT_GROUP_CSS)));
        select.selectByVisibleText(data.getGroupName());
    }

    public boolean isAddressWithNamePresented(String text){
        WebElement we = find(By.xpath("//td[contains(., \""+ text +"\")]"));
        return we != null;
    }

    public void openEditAddressWithName(String name){
        WebElement row = getRowContainingName(name);
        row.findElement(By.cssSelector("img[title='Edit']")).click();
    }

    public WebElement getRowContainingName(String name){
        return find(By.xpath("//tr[contains(., '"+name+"')]"));
    }
}
