package mantis.tests;

import mantis.appmanager.AdminPageHelper;
import mantis.appmanager.HttpSession;
import mantis.appmanager.LoginPageHelper;
import mantis.appmanager.NavigationHelper;

import mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Александр on 13.05.2017.
 */
public class ResetPasswordTest extends TestBase{
    NavigationHelper goTo = new NavigationHelper(app);
    String testUser = "alex";
    String testEmail = "alex@localhost.localdomain";
    String testPassword = "password";

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testReset() throws IOException, MessagingException {
        String password = app.getProperty("web.adminPassword");
        AdminPageHelper adminPageHelper = goTo.loginPage().login(app.getProperty("web.adminLogin"), password);
        adminPageHelper.goToManageUsersPage();
        adminPageHelper.chooseUserWithName("alex");
        adminPageHelper.clickResetPasswordButton();
        List<MailMessage> messages = app.mail().waitForMail(1, 10000);
        String link = findResetLink(messages, testEmail);
        goTo.page(link);
        String newPassword = String.valueOf(System.currentTimeMillis());
        adminPageHelper.resetPassword(newPassword);
        HttpSession session = new HttpSession(app);
        session.login(testUser, newPassword);
        assertTrue(session.isLoggedAs(testUser));
        debugWait();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        app.mail().stop();
        app.stop();
    }

    private String findResetLink(List<MailMessage> messages, String email) {
        MailMessage message = messages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
    }
}