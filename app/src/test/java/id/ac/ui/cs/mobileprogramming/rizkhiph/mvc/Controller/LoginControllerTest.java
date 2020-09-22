package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Controller;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.LoginControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.LoginController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.LoginView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class LoginControllerTest {

    LoginController loginController = new LoginController(mock(LoginView.class), mock(LoginControllerListener.class));
    @Test
    public void loginWithCorrectCreds() {
        boolean result = this.loginController.validate("Anything@email", "Anything123");
        assertTrue(result);
    }

    @Test
    public void loginWithFalseEmail() {
        boolean result = this.loginController.validate("Anything", "Anything123");
        assertFalse(result);
    }

    @Test
    public void loginWithFalsePassword() {
        boolean result = this.loginController.validate("Anything@email", "12");
        assertFalse(result);
    }
}
