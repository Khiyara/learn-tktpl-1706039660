package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.LoginControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.constant.ErrorConstants;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service.LoginService;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.LoginView;

@SuppressLint("NewApi")
public class LoginController implements View.OnClickListener {

    private final String TAG = "Login controller";

    private LoginView loginView;
    private LoginControllerListener listener;
    private LoginService loginService = new LoginService();

    public LoginController(LoginView loginView, LoginControllerListener listener) {
        this.loginView = loginView;
        this.listener = listener;
    }

    /**
     * Login button was clicked
     */
    @Override
    public void onClick(View v) {
        Log.i(TAG, "[+] Login Button Clicked");
        String email = loginView.getLogin();
        String password = loginView.getPassword();
        boolean clean = this.validate(email, password);
        if(clean && this.loginService.login(email, password)) {
            listener.onLoginSuccess();
        }
    }

    public boolean validate(String email, String password) {
        if(email.equals("") || email.isEmpty()) {
            loginView.setLoginError(ErrorConstants.ERROR_FIELD_REQUIRED);
            return false;
        }
        if(!email.contains("@")) {
            loginView.setLoginError(ErrorConstants.ERROR_INVALID_EMAIL);
            return false;
        }
        if(password.equals("") || password.isEmpty()) {
            loginView.setPasswordError(ErrorConstants.ERROR_FIELD_REQUIRED);
            return false;
        }
        if(password.length() < 3){
            loginView.setPasswordError(ErrorConstants.ERROR_INVALID_PASSWORD);
            return false;
        }
        return true;
    }

}
