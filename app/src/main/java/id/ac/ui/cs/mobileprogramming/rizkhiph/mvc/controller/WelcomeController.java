package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.WelcomeControllerListener;

@SuppressLint("NewApi")
public class WelcomeController implements View.OnClickListener {

    private final String TAG = "Welcome controller";

    private WelcomeControllerListener listener;

    public WelcomeController(WelcomeControllerListener listener) {
        this.listener = listener;
    }

    /**
     * Login button was clicked
     */
    @Override
    public void onClick(View v) {
        Log.i(TAG, "[+] Search Button Clicked");
        listener.onSearchButtonClick();
    }

}

