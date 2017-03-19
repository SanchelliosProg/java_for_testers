package ru.stqua.pft.addressbook.web.tests.utils.address;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqua.pft.addressbook.web.tests.models.AddressData;
import ru.stqua.pft.addressbook.web.tests.utils.BaseHelper;
import ru.stqua.pft.addressbook.web.tests.utils.PageInteractor;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressHelper extends BaseHelper implements PageInteractor {
    public static final String ADD_ADDRESS_URL = "http://localhost/addressbook/edit.php";

    public AddressHelper(WebDriver driver) {
        super(driver);
    }

    public void addAddress(AddressData data){
        String dataEntry = data.getFirstName() + Keys.TAB +
                data.getLastName() + Keys.TAB + Keys.TAB + data.getLastName();
        find("input[name='firstname']").sendKeys(dataEntry);
        find("textarea[name='address']").sendKeys(data.getAddress());
        find("input[name='home']").sendKeys(data.getPhone());
        find("input[name='email']").sendKeys(data.getEmail());
        Select select = new Select(find("select[name='new_group']"));
        select.selectByVisibleText(data.getGroupName());
        click("input[name='submit']");
    }

    public boolean isElementWithTextExists(String text){
        try{
            return findByXapth("//td[contains(., \""+ text +"\")]").isDisplayed();
        }catch (ElementNotFoundException ex){
            return false;
        }
    }

    public void cleanup(){
        click("input#MassCB");
        click("input[onclick='DeleteSel()']");
        driver.switchTo().alert().accept();
    }

    @Override
    public void open() {
        driver.get(ADD_ADDRESS_URL);
    }
}
