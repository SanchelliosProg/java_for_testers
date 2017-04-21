package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.DataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 08.04.2017.
 */
public class AddressListHelper extends BaseHelper {
    private final String BUTTON_DELETE_CSS = "input[onclick='DeleteSel()']";
    private final String LIST_OF_ADDRESSES_CSS = "table tbody tr[name='entry']";
    private final String CHECKBOX_ROW_CSS = "input[type='checkbox']";
    private final String LABEL_LAST_NAME_CSS = "td:nth-child(2)";
    private final String LABEL_FIRST_NAME_CSS = "td:nth-child(3)";

    private DataSet<AddressData> addressCache = null;

    public AddressListHelper(WebDriver driver) {
        super(driver);
    }

    public DataSet<AddressData> all() {
        if (addressCache != null) {
            return new DataSet<>(addressCache);
        }
        DataSet<AddressData> addresses = new DataSet<>();
        NavigationHelper navigationHelper = new NavigationHelper(driver);
        navigationHelper.homePage();
        List<WebElement> addressRows = findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        for (WebElement row : addressRows) {
            AddressData addressData = convertElementToAddressData(row);
            addresses.add(addressData);
        }
        return addresses;
    }

    public boolean isAddressesPresented() {
        List<WebElement> list = findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public AddressData addAddress(AddressData data){
        addressCache = null;
        NavigationHelper goTo = new NavigationHelper(driver);
        goTo.addNewAddressPage();
        new AddressHelper(driver).fillNewAddressData(data);
        return data;
    }

    public AddressData editFirstAddress(AddressData newAddress) {
        addressCache = null;
        WebElement firstElement = find(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        AddressData oldAddress = convertElementToAddressData(firstElement);
        chooseAddress(firstElement);
        clickEdit(firstElement);
        AddressHelper helper = new AddressHelper(driver);
        helper.editAddress(newAddress);
        return oldAddress;
    }

    public AddressData removeFirstAddress() {
        WebElement firstElement = find(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        AddressData address = convertElementToAddressData(firstElement);
        removeElement(firstElement);
        return address;
    }

    public AddressData removeAddress(AddressData data) {
        addressCache = null;
        AddressData removedElement;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> rows = findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        for (WebElement row : rows) {
            String lastName = row.findElement(By.cssSelector(LABEL_LAST_NAME_CSS)).getText();
            if(lastName.equals(data.getLastName())){
                removedElement = convertElementToAddressData(row);
                removeElement(row);
                return removedElement;
            }
        }
        return null;
    }

    public void cleanup() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        click(By.cssSelector("input#MassCB"));
        click(By.cssSelector(BUTTON_DELETE_CSS));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    private AddressData convertElementToAddressData(WebElement row) {
        int id = Integer.parseInt(row.findElement(By.cssSelector(CHECKBOX_ROW_CSS)).getAttribute("value"));
        String lastName = row.findElement(By.cssSelector(LABEL_LAST_NAME_CSS)).getText();
        String firstName = row.findElement(By.cssSelector(LABEL_FIRST_NAME_CSS)).getText();
        return new AddressData(id, firstName, lastName);
    }

    private void clickEdit(WebElement firstElement) {
        firstElement.findElement(By.cssSelector("img[title='Edit']")).click();
    }

    private void removeElement(WebElement firstElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        chooseAddress(firstElement);
        click(By.cssSelector(BUTTON_DELETE_CSS));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    private void chooseAddress(WebElement firstElement) {
        firstElement.findElement(By.cssSelector(CHECKBOX_ROW_CSS)).click();
    }
}
