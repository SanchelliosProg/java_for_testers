package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.PageInteractor;

import java.util.List;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressHelper extends BaseHelper implements PageInteractor {
    public static final String ADD_ADDRESS_URL = "http://localhost/addressbook/edit.php";

    private final String FIRST_NAME_INPUT_CSS = "input[name='firstname']";
    private final String LAST_NAME_INPUT_CSS = "";

    private final String ADDRESS_INPUT_CSS = "textarea[name='address']";
    private final String HOME_INPUT_CSS = "input[name='home']";
    private final String EMAIL_INPUT_CSS = "input[name='email']";

    private final String GROUP_SELECT_CSS = "select[name='new_group']";

    private final String UPDATE_BUTTON_CSS = "input[name='update']";
    private final String SUBMIT_BUTTON = "input[name='submit']";

    private final String LIST_OF_ADDRESSES_CSS = "tr[name='entry']";

    public AddressHelper(WebDriver driver) {
        super(driver);
    }

    public void addAddress(AddressData data){
        String dataEntry = data.getFirstName() + Keys.TAB +
                data.getLastName() + Keys.TAB + Keys.TAB + data.getLastName();
        find(FIRST_NAME_INPUT_CSS).sendKeys(dataEntry);
        find(ADDRESS_INPUT_CSS).sendKeys(data.getAddress());
        find(HOME_INPUT_CSS).sendKeys(data.getPhone());
        find(EMAIL_INPUT_CSS).sendKeys(data.getEmail());
        Select select = new Select(find(GROUP_SELECT_CSS));
        select.selectByVisibleText(data.getGroupName());
        click(SUBMIT_BUTTON);
    }

    public boolean isElementWithTextExists(String text){
        try{
            return findByXpath("//td[contains(., \""+ text +"\")]").isDisplayed();
        }catch (ElementNotFoundException ex){
            return false;
        }
    }

    public void openEditAddressWithName(String name){
        WebElement row = getRowContainingName(name);
        row.findElement(By.cssSelector("img[title='Edit']")).click();
    }

    public WebElement getRowContainingName(String name){
        return findByXpath("//tr[contains(., '"+name+"')]");
    }

    public boolean isAddressesPresented(){
        List<WebElement> list = findAll(LIST_OF_ADDRESSES_CSS);
        if(list.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    public void editFirstName(String newName){
        editInputField(FIRST_NAME_INPUT_CSS, newName);
    }

    public void editLastName(String lastName){
        //TODO: edit last name
    }

    public void cleanup(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        click("input#MassCB");
        click("input[onclick='DeleteSel()']");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Override
    public void open() {
        driver.get(ADD_ADDRESS_URL);
    }
}
