package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Controller;

import android.view.View;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.WelcomeControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.WelcomeController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WelcomeControllerTest {

    WelcomeController welcomeController = new WelcomeController(mock(WelcomeControllerListener.class));
    @Test
    public void buttonWelcomeOnClick() {
        this.welcomeController.onClick(mock(View.class));
        verify(this.welcomeController.getListener(), times(1)).onSearchButtonClick();
    }
}
