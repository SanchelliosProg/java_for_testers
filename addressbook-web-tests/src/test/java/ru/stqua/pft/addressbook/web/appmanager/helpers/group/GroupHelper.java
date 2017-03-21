package ru.stqua.pft.addressbook.web.appmanager.helpers.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void createGroup(GroupData groupData) {
        editGroupName(groupData.getName());
        find(GROUP_HEADER_TEXT_AREA_CSS).sendKeys(groupData.getHeader());
        find(GROUP_FOOTER_TEXT_AREA_CSS).sendKeys(groupData.getFooter());
        click(SUBMIT_BUTTON_CSS);
    }

    public void editGroupName(String groupName) {
        editInputField(GROUP_NAME_INPUT_CSS, groupName);
    }

    public void editHeaderText(String headerText){
        editInputField(GROUP_HEADER_TEXT_AREA_CSS, headerText);
    }

    public void editFooterText(String footerText) {
        editInputField(GROUP_FOOTER_TEXT_AREA_CSS, footerText);
    }

    private void editInputField(String cssSelector, String text) {
        WebElement input = find(cssSelector);
        clearInputText(input);
        input.sendKeys(text);
    }

    public void openNewGroupPage() {
        click("input[type='submit'][name='new']");
    }

    public void submitChanges(){
        click(SUBMIT_BUTTON_CSS);
    }

    public void clickUpdate(){
        click(UPDATE_BUTTON_CSS);
    }

    public void removeFirstGroup(){
        WebElement group = find(LIST_OF_GROUPS_CSS);
        group.findElement(By.cssSelector("input[type='checkbox']")).click();
        click(DELETE_BUTTON_CSS);
    }

    public boolean isGroupsPresented () {
        List<WebElement> groups = findAll(LIST_OF_GROUPS_CSS);
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

    public void openEditGroupWithName(String name){
        WebElement row = findByXpath("//span[contains(., '" +name+"')]");
        row.findElement(By.cssSelector("input")).click();
        find("input[name='edit']").click();
    }

    public GroupData addGroup(Groups group) {
        open();
        openNewGroupPage();
        GroupData groupData = GroupProvider.get(group);
        createGroup(groupData);
        return groupData;
    }

    public void editGroup(GroupData data){
        editGroupName(data.getName());
        editHeaderText(data.getHeader());
        editFooterText(data.getFooter());
    }

    @Override
    public void cleanup() {
        driver.get(GROUPS_URL);
        List<WebElement> groups = findAll("form span");
        for (WebElement group : groups) {
            group.click();
        }
        click(DELETE_BUTTON_CSS);
    }

    @Override
    public void open() {
        driver.get(GROUPS_URL);
    }
}
