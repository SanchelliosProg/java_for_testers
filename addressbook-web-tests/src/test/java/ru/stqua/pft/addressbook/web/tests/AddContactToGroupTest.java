package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

/**
 * Created by Александр on 05.05.2017.
 */
public class AddContactToGroupTest extends TestBase{
    /*
    * Проверить, есть ли такой контакт
    * Проверить, есть ли группа этому контакту соответствующая
    * */
    AddedDataStatus<ContactData> addedContact;
    AddedDataStatus<GroupData> addedGroup;
    @BeforeMethod
    public void setData(){
        createGroupIfNotExist(GroupLabels.BROTHERHOOD_OF_RING);
        addedGroup = createGroupIfNotExist(GroupLabels.FAIRY_WORLDS);
        addedContact = createContactIfNotExist(ContactProvider.getContact(ContactsLabels.FRODO_BAGGINS));

    }
    @Test
    public void addToGroup(){
        goTo.homePage();
        WebElement contactRow = contactListHelper.getRowContainingName(addedContact.data().getLastName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
