package ru.stqua.pft.addressbook.web.appmanager.helpers.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.DataSet;
import ru.stqua.pft.addressbook.web.model.GroupData;

import java.util.List;

import static ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactListSelectors.*;

/**
 * Created by Александр on 08.04.2017.
 */
public class ContactListHelper extends BaseHelper {

    private DataSet<ContactData> contactCache = null;

    public ContactListHelper(WebDriver driver) {
        super(driver);
    }

    public void setGroupsToContact(ContactData data) {
        for (GroupData groupData : data.getGroups()) {
            WebElement row = getRowContainingName(data.getLastName());
            checkContact(row);
            Select select = new Select(find(By.cssSelector(SELECT_GROUP)));
            select.selectByVisibleText(groupData.getName());
            click(BUTTON_ADD_TO);
            new NavigationHelper(driver).homePage();
        }
    }

    public int count(){
        return findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS)).size();
    }

    public void openDetailedInfoOfContactWithLastName(String lastName) {
        openRowsPage(lastName, By.cssSelector(BUTTON_DETAILED_CSS));
    }

    public void openEditPageOfContactWithLastName(String lastName) {
        openRowsPage(lastName, By.cssSelector(BUTTON_EDIT_CSS));
    }

    private void openRowsPage(String name, By locator){
        WebElement row = getRowContainingName(name);
        row.findElement(locator).click();
    }

    public DataSet<ContactData> all() {
        if (contactCache != null) {
            return contactCache;
        }
        DataSet<ContactData> addresses = new DataSet<>();
        NavigationHelper navigationHelper = new NavigationHelper(driver);
        navigationHelper.homePage();
        List<WebElement> addressRows = findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        for (WebElement row : addressRows) {
            ContactData contactData = convertElementToContactData(row);
            addresses.add(contactData);
        }
        return addresses;
    }

    public boolean isContactsPresented() {
        List<WebElement> list = findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ContactData createContact(ContactData data){
        if(isContactWithNamePresented(data.getLastName()))
            removeContact(data);
        contactCache = null;
        NavigationHelper goTo = new NavigationHelper(driver);
        goTo.addNewAddressPage();
        new ContactHelper(driver).fillNewContactData(data);
        return data;
    }

    public ContactData editFirstContact(ContactData newAddress) {
        contactCache = null;
        WebElement firstElement = find(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        ContactData oldAddress = convertElementToContactData(firstElement);
        checkContact(firstElement);
        clickEdit(firstElement);
        ContactHelper helper = new ContactHelper(driver);
        helper.editContact(newAddress);
        return oldAddress;
    }

    public ContactData removeFirstContact() {
        WebElement firstElement = find(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        ContactData address = convertElementToContactData(firstElement);
        removeElement(firstElement);
        return address;
    }

    public ContactData removeContact(ContactData data) {
        contactCache = null;
        ContactData removedElement;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> rows = findAll(By.cssSelector(LIST_OF_ADDRESSES_CSS));
        for (WebElement row : rows) {
            String lastName = parseTextFrom(row, LABEL_LAST_NAME_CSS);
            if(lastName.equals(data.getLastName())){
                removedElement = convertElementToContactData(row);
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



    public WebElement getRowContainingName(String name){
        return find(By.xpath("//tr[contains(., '"+name+"')]"));
    }

    public ContactData parseContactWithName(String name){
        WebElement row = getRowContainingName(name);
        return convertElementToContactData(row);
    }

    public boolean isContactWithNamePresented(String text){
        WebElement we = find(By.xpath("//td[contains(., \""+ text +"\")]"));
        return we != null;
    }

    private ContactData convertElementToContactData(WebElement row) {
        int id = Integer.parseInt(row.findElement(By.cssSelector(CHECKBOX_ROW_CSS)).getAttribute("value"));
        String lastName = parseTextFrom(row, LABEL_LAST_NAME_CSS);
        String firstName = parseTextFrom(row, LABEL_FIRST_NAME_CSS);
        String address = parseTextFrom(row, LABEL_ADDRESS_CSS);
        String email = parseTextFrom(row, LABEL_EMAIL_CSS);
        String phones = parseTextFrom(row, LABEL_PHONES_CSS);
        String[] phonesArray = phones.split("\n");
        ContactData data;
        if(phonesArray.length == 3){
            data = ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                    .homePhone(phonesArray[0]).mobilePhone(phonesArray[1]).workPhone(phonesArray[2]).email(email).noGroup().noPhoto().build();
        }else if (phonesArray.length == 2){
            data = ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                    .homeAndMobile(phonesArray[0], phonesArray[1]).email(email).noGroup().noPhoto().build();
        }else if (phonesArray.length == 1){
            data = ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                    .homePhoneOnly(phonesArray[0]).email(email).noGroup().noPhoto().build();
        } else {
            data = ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                    .noPhone().email(email).noGroup().noPhoto().build();
        }

        data.setId(id);
        return data;
    }

    private String parseTextFrom(WebElement row, String locator) {
        return row.findElement(By.cssSelector(locator)).getText();
    }

    private void clickEdit(WebElement firstElement) {
        firstElement.findElement(By.cssSelector("img[title='Edit']")).click();
    }

    private void removeElement(WebElement firstElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        checkContact(firstElement);
        click(By.cssSelector(BUTTON_DELETE_CSS));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void checkContact(WebElement row) {
        row.findElement(By.cssSelector(CHECKBOX_ROW_CSS)).click();
    }
}
