package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.Controller;

import android.view.View;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.activity.LoginControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.controller.LoginController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.view.LoginView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void loginWithEmptyEmail() {
        boolean result = this.loginController.validate("", "12");
        assertFalse(result);
    }

    @Test
    public void loginWithEmptyPassword() {
        boolean result = this.loginController.validate("anything@gmail", "");
        assertFalse(result);
    }

    @Test
    public void loginButtonOnClickAndCleanCredential() {
        when(this.loginController.getLoginView().getLogin()).thenReturn("anything@gmail");
        when(this.loginController.getLoginView().getPassword()).thenReturn("anything123");
        this.loginController.onClick(mock(View.class));
        verify(this.loginController.getListener(), times(1)).onLoginSuccess();
    }

    @Test
    public void loginButtonOnClickAndNotCleanCredential() {
        when(this.loginController.getLoginView().getLogin()).thenReturn("anything@gmail");
        when(this.loginController.getLoginView().getPassword()).thenReturn("11");
        this.loginController.onClick(mock(View.class));
        verify(this.loginController.getListener(), times(0)).onLoginSuccess();
    }
}
