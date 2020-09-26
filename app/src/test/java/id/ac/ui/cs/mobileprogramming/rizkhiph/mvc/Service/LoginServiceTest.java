package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Service;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service.LoginService;

import static org.junit.Assert.assertTrue;

public class LoginServiceTest {

    LoginService loginService = new LoginService();
    @Test
    public void loginWithCorrectCreds() {
        boolean result = this.loginService.login("Anything@email", "Anything123");
        assertTrue(result);
    }
}
