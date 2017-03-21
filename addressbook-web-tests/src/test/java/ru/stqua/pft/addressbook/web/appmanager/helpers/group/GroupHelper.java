package ru.stqua.pft.addressbook.web.appmanager.helpers.group;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupHelper extends BaseHelper implements PageInteractor {
    public static final String GROUPS_URL = "http://localhost/addressbook/group.php";

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void createGroup(GroupData groupData) {
        editGroupName(groupData.getName());
        find("textarea[name='group_header']").sendKeys(groupData.getHeader());
        find("textarea[name='group_footer']").sendKeys(groupData.getFooter());
        click("input[name='submit']");
    }

    public void editGroupName(String groupName) {
        WebElement input = find("input[type='text'][name='group_name']");
        String inputText = input.getAttribute("value");
        int length = inputText.length();
        for (int i = 0; i < length; i++){
            input.sendKeys(Keys.BACK_SPACE);
        }
        input.sendKeys(groupName);
    }

    public void openNewGroupPage() {
        click("input[type='submit'][name='new']");
    }

    public void submitChanges(){
        click("input[name='submit']");
    }

    public void clickUpdate(){
        click("input[name='update']");
    }

    public boolean isGroupsPresented () {
        List<WebElement> groups = findAll("form span");
        if(groups.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isGroupWithNamePresented(String name){
        try {
            return findByXapth("//span[contains(., '" +name+"')]").isDisplayed();
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    public void openEditGroupWithName(String name){
        WebElement row = findByXapth("//span[contains(., '" +name+"')]");
        row.findElement(By.cssSelector("input")).click();
        find("input[name='edit']").click();
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
