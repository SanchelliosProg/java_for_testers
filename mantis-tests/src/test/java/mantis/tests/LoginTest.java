package mantis.tests;

import mantis.appmanager.HttpSession;
import static org.testng.Assert.assertTrue;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Александр on 09.05.2017.
 */
public class LoginTest extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login(props.getProperty("web.adminLogin"),
                props.getProperty("web.adminPassword")));


    }
}