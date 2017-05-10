package mantis.tests;

import org.junit.Test;

/**
 * Created by Александр on 10.05.2017.
 */
public class RegistrationTest extends TestBase {
    @Test
    public void testRegistration(){
        app.registration().start("user1", "user@localhost.localdomain");
    }
}
