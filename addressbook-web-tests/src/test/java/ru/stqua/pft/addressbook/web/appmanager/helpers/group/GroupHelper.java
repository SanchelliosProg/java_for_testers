package ru.stqua.pft.addressbook.web.appmanager.helpers.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupHelper extends BaseHelper implements PageInteractor {
    public static final String GROUPS_URL = "http://localhost/addressbook/group.php";

    private final String GROUP_NAME_INPUT_CSS = "input[type='text'][name='group_name']";
    private final String DELETE_BUTTON_CSS = "input[name='delete']";
    private final String SUBMIT_BUTTON_CSS = "input[name='submit']";

    private final String GROUP_HEADER_TEXT_AREA_CSS = "textarea[name='group_header']";
    private final String GROUP_FOOTER_TEXT_AREA_CSS = "textarea[name='group_footer']";
    private final String UPDATE_BUTTON_CSS = "input[name='update']";

    private final String LIST_OF_GROUPS_CSS = "form span";

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void openNewGroupPage() {
        click(By.cssSelector("input[type='submit'][name='new']"));
    }

    public boolean isAnyGroupPresented() {
        List<WebElement> groups = findAll(By.cssSelector(LIST_OF_GROUPS_CSS));
        if(groups.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isGroupWithNamePresented(String name){
        WebElement group = findByXpath("//span[contains(., '" +name+"')]");
        if (group == null){
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

    public void submitChanges(){
        click(By.cssSelector(SUBMIT_BUTTON_CSS));
    }

    public void clickUpdate(){
        click(By.cssSelector(UPDATE_BUTTON_CSS));
    }

    public void removeFirstGroup(){
        WebElement group = find(By.cssSelector(LIST_OF_GROUPS_CSS));
        removeGroup(group);
    }

    private void removeGroup(WebElement group){
        group.findElement(By.cssSelector("input[type='checkbox']")).click();
        click(By.cssSelector(DELETE_BUTTON_CSS));
    }

    public void removeGroupWithName(String name){
        List<WebElement> groups = findAll(By.cssSelector(LIST_OF_GROUPS_CSS));
        for (WebElement group : groups) {
            if(group.getText().contains(name)){
                removeGroup(group);
                return;
            }
        }
    }

    public void editGroup(GroupData data){
        editGroupName(data.getName());
        editHeaderText(data.getHeader());
        editFooterText(data.getFooter());
    }

    public void editGroupName(String groupName) {
        type(By.cssSelector(GROUP_NAME_INPUT_CSS), groupName);
    }

    public void editHeaderText(String headerText){
        type(By.cssSelector(GROUP_HEADER_TEXT_AREA_CSS), headerText);
    }

    public void editFooterText(String footerText) {
        type(By.cssSelector(GROUP_FOOTER_TEXT_AREA_CSS), footerText);
    }

    public void openEditGroupWithName(String name){
        WebElement row = find(By.xpath("//span[contains(., '" +name+"')]"));
        openEditPage(row);
    }

    public void openEditFirstGroup(){
        WebElement row = findAll(By.cssSelector("form span")).get(0);
        openEditPage(row);
    }

    private void openEditPage(WebElement row){
        row.findElement(By.cssSelector("input")).click();
        find(By.cssSelector("input[name='edit']")).click();
    }

    public GroupData createGroup(Groups group) {
        open();
        openNewGroupPage();
        GroupData groupData = GroupProvider.get(group);
        createGroup(groupData);
        return groupData;
    }

    public List<GroupData> list(){
        new NavigationHelper(driver).groupPage();
        List<WebElement> groups = findAll(By.cssSelector("form span"));
        List<GroupData> gd = new ArrayList<>();
        for (WebElement group : groups){
            String name = group.getText();
            int id = Integer.parseInt(group.findElement(By.tagName("input")).getAttribute("value"));
            gd.add(new GroupData()
                    .withId(id)
                    .withName(name));
        }
        return gd;
    }

    public Set<GroupData> all(){
        new NavigationHelper(driver).groupPage();
        List<WebElement> elements = findAll(By.cssSelector("form span"));
        Set<GroupData> groups = new HashSet<>();
        for (WebElement group : elements){
            String name = group.getText();
            int id = Integer.parseInt(group.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData()
                    .withId(id)
                    .withName(name));
        }
        return groups;
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
