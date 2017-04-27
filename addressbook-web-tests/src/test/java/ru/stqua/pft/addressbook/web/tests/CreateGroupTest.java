package ru.stqua.pft.addressbook.web.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
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
    public Iterator<Object[]> validGroups() throws IOException {
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

    @Test(dataProvider = "validGroups")
    public void createGroup(GroupData groupData) {
        boolean doAdd = false;
        DataSet<GroupData> before = group.all();
        beforeCount = group.count();

        goTo.groupPage();
        if(!group.isGroupWithNamePresented(groupData.getName())){
            group.createGroup(groupData);
            doAdd = true;
        } else {
            reduceBeforeCountDueToDataObjCreation();
        }
        goTo.groupPage();
        assertThat(group.count(), equalTo(beforeCount+1));
        DataSet after = group.all();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));

        if(doAdd){
            assertThat(after, equalTo(before.withAdded(groupData)));
        } else {
            assertThat(after, equalTo(before));
        }


    }

}
