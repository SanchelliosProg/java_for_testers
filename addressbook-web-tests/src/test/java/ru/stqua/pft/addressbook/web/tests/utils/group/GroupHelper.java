package ru.stqua.pft.addressbook.web.tests.utils.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.tests.models.GroupData;
import ru.stqua.pft.addressbook.web.tests.utils.BaseHelper;
import ru.stqua.pft.addressbook.web.tests.utils.PageInteractor;

import java.util.List;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupHelper extends BaseHelper implements PageInteractor {
    public static final String GROUPS_URL = "http://localhost/addressbook/group.php";

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void createGroup(GroupData groupData) {
        find("input[type='text'][name='group_name']").sendKeys(groupData.getName());
        find("textarea[name='group_header']").sendKeys(groupData.getHeader());
        find("textarea[name='group_footer']").sendKeys(groupData.getFooter());
        click("input[name='submit']");
    }

    public void openNewGropPage() {
        click("input[type='submit'][name='new']");
    }

    @Override
    public void cleanup() {
        driver.get(GROUPS_URL);
        List<WebElement> groups = findAll("form span");
        for (WebElement group : groups) {
            group.click();
        }
        click("input[name='delete']");
    }

    @Override
    public void open() {
        driver.get(GROUPS_URL);
    }
}
