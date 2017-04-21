package ru.stqua.pft.addressbook.web.appmanager.helpers.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupHelper extends BaseHelper implements PageInteractor {
    public static final String GROUPS_URL = "http://localhost/addressbook/group.php";

    private final String GROUP_NAME_INPUT_CSS = "input[type='text'][name='group_name']";
    private final String DELETE_BUTTON_CSS = "input[name='delete']";
    private final String SUBMIT_BUTTON_CSS = "input[name='submit']";
    private final String BUTTON_EDIT_CSS = "form input[type='submit'][name='edit']";

    private final String GROUP_HEADER_TEXT_AREA_CSS = "textarea[name='group_header']";
    private final String GROUP_FOOTER_TEXT_AREA_CSS = "textarea[name='group_footer']";
    private final String UPDATE_BUTTON_CSS = "input[name='update']";

    private final String CHECKBOX_CHOOSE_GROUP_CSS = "input[type='checkbox']";

    private final String BUTTON_UPDATE_EDIT_CSS = "form input[type='submit']";

    private final String LIST_OF_GROUPS_CSS = "form span";

    private DataSet<GroupData> groupCache = null;

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void openNewGroupPage() {
        click(By.cssSelector("input[type='submit'][name='new']"));
    }

    public DataSet<GroupData> all() {
        new NavigationHelper(driver).groupPage();
        if (groupCache != null) {
            return new DataSet<>(groupCache);
        }
        List<WebElement> elements = findAll(By.cssSelector("form span"));
        groupCache = new DataSet<>();
        for (WebElement element : elements) {
            groupCache.add(transformElementToGroupData(element));
        }
        return groupCache;
    }

    public boolean isAnyGroupPresented() {
        List<WebElement> groups = findAll(By.cssSelector(LIST_OF_GROUPS_CSS));
        if (groups.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isGroupWithNamePresented(String name) {
        WebElement group = find(By.xpath("//span[contains(., '" + name + "')]"));
        if (group == null) {
            return false;
        } else {
            return true;
        }
    }

    public void createGroup(GroupData groupData) {
        editGroupName(groupData.getName());
        find(By.cssSelector(GROUP_HEADER_TEXT_AREA_CSS)).sendKeys(groupData.getHeader());
        find(By.cssSelector(GROUP_FOOTER_TEXT_AREA_CSS)).sendKeys(groupData.getFooter());
        click(By.cssSelector(SUBMIT_BUTTON_CSS));
    }

    public void submitChanges() {
        click(By.cssSelector(SUBMIT_BUTTON_CSS));
    }

    public void clickUpdate() {
        click(By.cssSelector(UPDATE_BUTTON_CSS));
    }

    public GroupData editFirstGroup(GroupData newGroupData) {
        groupCache = null;
        WebElement groupElement = find(By.cssSelector(LIST_OF_GROUPS_CSS));
        GroupData oldGroup = transformElementToGroupData(groupElement);

        chooseGroup(groupElement);
        click(By.cssSelector(BUTTON_EDIT_CSS));
        editGroup(newGroupData);
        click(BUTTON_UPDATE_EDIT_CSS);
        return oldGroup;
    }

    public GroupData removeFirstGroup() {
        WebElement groupElement = find(By.cssSelector(LIST_OF_GROUPS_CSS));
        GroupData removedGroup = transformElementToGroupData(groupElement);
        removeGroup(groupElement);
        return removedGroup;
    }

    private void removeGroup(WebElement group) {
        groupCache = null;
        chooseGroup(group);
        click(By.cssSelector(DELETE_BUTTON_CSS));
    }

    private void chooseGroup(WebElement group) {
        group.findElement(By.cssSelector(CHECKBOX_CHOOSE_GROUP_CSS)).click();
    }

    public void removeGroupWithName(String name) {
        groupCache = null;
        List<WebElement> groups = findAll(By.cssSelector(LIST_OF_GROUPS_CSS));
        for (WebElement group : groups) {
            if (group.getText().contains(name)) {
                removeGroup(group);
                return;
            }
        }
    }

    public void editGroup(GroupData data) {
        groupCache = null;
        editGroupName(data.getName());
        editHeaderText(data.getHeader());
        editFooterText(data.getFooter());
    }

    public void editGroupName(String groupName) {
        type(By.cssSelector(GROUP_NAME_INPUT_CSS), groupName);
    }

    public void editHeaderText(String headerText) {
        type(By.cssSelector(GROUP_HEADER_TEXT_AREA_CSS), headerText);
    }

    public void editFooterText(String footerText) {
        type(By.cssSelector(GROUP_FOOTER_TEXT_AREA_CSS), footerText);
    }

    public void openEditGroupWithName(String name) {
        WebElement row = find(By.xpath("//span[contains(., '" + name + "')]"));
        openEditPage(row);
    }

    public void openEditFirstGroup() {
        WebElement row = findAll(By.cssSelector("form span")).get(0);
        openEditPage(row);
    }

    private void openEditPage(WebElement row) {
        row.findElement(By.cssSelector("input")).click();
        find(By.cssSelector("input[name='edit']")).click();
    }

    public GroupData createGroup(GroupLabels group) {
        open();
        openNewGroupPage();
        GroupData groupData = GroupProvider.get(group);
        createGroup(groupData);
        return groupData;
    }

    public List<GroupData> list() {
        new NavigationHelper(driver).groupPage();
        List<WebElement> groups = findAll(By.cssSelector("form span"));
        List<GroupData> gd = new ArrayList<>();
        for (WebElement group : groups) {
            String name = group.getText();
            int id = Integer.parseInt(group.findElement(By.tagName("input")).getAttribute("value"));
            gd.add(new GroupData()
                    .withId(id)
                    .withName(name));
        }
        return gd;
    }


    public GroupData transformElementToGroupData(WebElement element) {
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        return new GroupData()
                .withId(id)
                .withName(name);
    }

    public void modifyGroupHeader(GroupData groupData, String newHeader, NavigationHelper navigationHelper) {
        groupData.withHeader(newHeader);
        navigationHelper.groupPage();
        openEditFirstGroup();
        editGroup(groupData);
        clickUpdate();
        navigationHelper.groupPage();
    }

    @Override
    public void cleanup() {
        groupCache = null;
        driver.get(GROUPS_URL);
        List<WebElement> groups = findAll(By.cssSelector("form span"));
        for (WebElement group : groups) {
            group.click();
        }
        click(By.cssSelector(DELETE_BUTTON_CSS));
    }

    @Override
    public void open() {
        driver.get(GROUPS_URL);
    }

    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
