package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.LoginControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.LoginController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.LoginView;

public class LoginActivity extends Activity implements LoginControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //Activity links the view and the controller
        LoginController loginController = new LoginController((LoginView) this.findViewById(R.id.login), this);
        ((LoginView) this.findViewById(R.id.login)).setListeners(loginController);
    }

    /**
     * Controller calls this method if the login was successful
     */
    @Override
    public void onLoginSuccess() {
        //We open a new welcome screen
        Intent intent = new Intent(this, WelcomeActivity.class);
        this.startActivity(intent);
    }
}

