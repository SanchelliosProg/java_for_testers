package mantis.tests;

import mantis.appmanager.*;

import mantis.model.MailMessage;
import mantis.model.User;
import org.testng.AssertJUnit;
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

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testReset() throws IOException, MessagingException {
        DbHelper dbHelper = new DbHelper();
        List<User> users = dbHelper.users();
        User user;
        if(users.size() < 2){
            user = createNewUser();
        } else {
            user = users.get(users.size() - 1);
        }
        String password = app.getProperty("web.adminPassword");
        AdminPageHelper adminPageHelper = goTo.loginPage().login(app.getProperty("web.adminLogin"), password);
        adminPageHelper.goToManageUsersPage();
        adminPageHelper.chooseUserWithName(user.getUsername());
        adminPageHelper.clickResetPasswordButton();
        List<MailMessage> messages = app.mail().waitForMail(1, 10000);
        String link = findResetLink(messages, user.getEmail());
        goTo.page(link);
        String newPassword = String.valueOf(System.currentTimeMillis());
        adminPageHelper.resetPassword(newPassword);
        HttpSession session = new HttpSession(app);
        session.login(user.getUsername(), newPassword);
        assertTrue(session.isLoggedAs(user.getUsername()));
        debugWait();
    }

    private User createNewUser() throws IOException, MessagingException {
        User user = new User();
        user.setEmail("alex@localhost.localdomain");
        user.setUsername("alex");
        app.registration().start(user.getUsername(), user.getEmail());
        List<MailMessage> messages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(messages, user.getEmail());
        app.registration().finish(confirmationLink, "pass");
        AssertJUnit.assertTrue(app.newSession().login(user.getUsername(), "pass"));
        return user;
    }

    private String findConfirmationLink(List<MailMessage> messages, String email) {
        MailMessage message = messages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
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