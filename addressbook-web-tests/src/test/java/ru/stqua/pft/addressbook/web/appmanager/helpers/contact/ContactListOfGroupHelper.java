package ru.stqua.pft.addressbook.web.appmanager.helpers.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.model.ContactData;

import static ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactListSelectors.CHECKBOX_ROW_CSS;

/**
 * Created by Александр on 07.05.2017.
 */
public class ContactListOfGroupHelper extends BaseHelper {
    private final String BUTTON_REMOVE = "input[name='remove']";
    public ContactListOfGroupHelper(WebDriver driver) {
        super(driver);
    }

    public void removeFromGroup(ContactData contact){
        new ContactListHelper(driver).checkContact(getRowContainingName(contact.getLastName()));
        click(BUTTON_REMOVE);
    }

    public WebElement getRowContainingName(String name){
        return find(By.xpath("//tr[contains(., '"+name+"')]"));
    }
    public void checkContact(WebElement row) {
        row.findElement(By.cssSelector(CHECKBOX_ROW_CSS)).click();
    }
}
