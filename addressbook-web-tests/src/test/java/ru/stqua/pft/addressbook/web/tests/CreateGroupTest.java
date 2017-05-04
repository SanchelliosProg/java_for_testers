package ru.stqua.pft.addressbook.web.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.GroupData;

import ru.stqua.pft.addressbook.web.model.DataSet;

import java.io.*;
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
public class CreateGroupTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        StringBuilder xml = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            xml.append(line);
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        System.out.println(xml.toString());
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml.toString());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            json.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(
                json.toString(),
                new TypeToken<List<GroupData>>(){}.getType()
        );
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void createGroup(GroupData groupData) {
        boolean doAdd = false;
        DataSet<GroupData> before = dbHelper.groups();
        beforeCount = before.size();

        goTo.groupPage();
        if(!group.isGroupWithNamePresented(groupData.getName())){
            group.createGroup(groupData);
            doAdd = true;
        } else {
            reduceBeforeCountDueToDataObjCreation();
        }
        goTo.groupPage();
        groupData.withId(group.getIdOfGroupWithName(groupData.getName()));
        DataSet<GroupData> after = dbHelper.groups();
        assertThat(after.size(), equalTo(beforeCount+1));

        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));

        if(doAdd){
            DataSet<GroupData> expected = before.withAdded(groupData);
            assertThat(after, equalTo(expected));
        } else {
            assertThat(after, equalTo(before));
        }
    }

}
