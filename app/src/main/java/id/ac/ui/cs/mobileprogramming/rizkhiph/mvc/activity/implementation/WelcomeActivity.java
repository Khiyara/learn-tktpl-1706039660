package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.WelcomeControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.WelcomeController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.WelcomeView;

public class WelcomeActivity extends Activity implements WelcomeControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        //Activity links the view and the controller
        WelcomeController welcomeController = new WelcomeController(this);
        ((WelcomeView) this.findViewById(R.id.welcome)).setListeners(welcomeController);
    }

    @Override
    public void onSearchButtonClick() {
        //We open a new welcome screen
        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }
}
