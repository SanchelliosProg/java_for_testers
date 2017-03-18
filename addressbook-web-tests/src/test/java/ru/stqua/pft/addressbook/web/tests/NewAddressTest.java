package ru.stqua.pft.addressbook.web.tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.tests.utils.BaseTest;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupPageController;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupProvider;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

/**
 * Created by Александр on 18.03.2017.
 */
public class NewAddressTest extends BaseTest {
    private GroupPageController groupPageController;
    private final String NEW_CONTACT_URL = "";
    @BeforeClass
    public void setUp(){
        login();
        groupPageController = new GroupPageController(driver);
        groupPageController.createGroup(GroupProvider.get(Groups.BROTHERHOOD_OF_RING));
    }

    @Test
    public void addContact(){

    }

    @AfterClass
    public void tearDown(){

    }
}
