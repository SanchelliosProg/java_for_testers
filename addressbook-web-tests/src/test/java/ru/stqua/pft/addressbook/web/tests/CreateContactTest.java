package ru.stqua.pft.addressbook.web.tests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.model.DataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateContactTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            json.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(
                json.toString(),
                new TypeToken<List<ContactData>>(){}.getType()
        );
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void createContactTest(ContactData contactData){
        DataSet<ContactData> before = contactListHelper.all();
        beforeCount = contactListHelper.count();

        goTo.homePage();

        AddedDataStatus<ContactData> newAddress = createContactIfNotExist(contactData);
        if(!newAddress.isCreated()){
            beforeCount -= 1;
        }

        goTo.homePage();
        assertThat(contactListHelper.count(), equalTo(beforeCount+1));
        DataSet<ContactData> after = contactListHelper.all();

        goTo.homePage();
        if(newAddress.isCreated()){
            assertThat(after, equalTo(before.withAdded(contactData)));
        }else {
            assertThat(after, equalTo(before));
        }
    }
}
