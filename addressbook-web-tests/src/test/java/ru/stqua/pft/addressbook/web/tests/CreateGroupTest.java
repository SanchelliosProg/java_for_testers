package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
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
