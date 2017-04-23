package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

import org.openqa.selenium.WebDriver;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.model.ContactData;

/**
 * Created by Александр on 23.04.2017.
 */
public class ContactDetailedScreenHelper extends BaseHelper{
    private final String CONTENT_CSS = "div#content";

    public ContactDetailedScreenHelper(WebDriver driver) {
        super(driver);
    }

    public ContactData parseContactData(){
        String text = find(CONTENT_CSS).getText();
        String[] sections = text.split("\n");
        String[] name = sections[0].split(" ");
        String firstName = name[0];
        String lastName = name[1];
        String address = sections[1];
        String homePhone = sections[3].replaceAll("H: ", "");
        String mobilePhone = sections[4].replaceAll("M: ", "");
        String workPhone = sections[5].replaceAll("W: ", "");
        String email = sections[7];
        return ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                .homePhone(homePhone).mobilePhone(mobilePhone).workPhone(workPhone).email(email).noGroup().build();
    }
}
