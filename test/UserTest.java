
import models.User;
import org.junit.Before;
import org.junit.Test;
import play.data.validation.Validation;
import play.test.Fixtures;
import play.test.UnitTest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GooF
 */
public class UserTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }
    
    @Test
    public void addUser() {
        User u = new User("guy", "guy@home.com");
        u.save();
        assertEquals(User.count(), 1);
    }

    @Test//(expected=IndexOutOfBoundsException.class)
    public void badEmail() {
        User u = new User("badEmailDude", "not a read email address");
        u.save();
        if (!Validation.hasErrors()) {
            fail("email is invalid");
        }
    }

}
