package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.MainControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.MainController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.MainView;

public class MainActivity extends Activity implements MainControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        MainController welcomeController = new MainController(this);
        ((MainView) this.findViewById(R.id.welcome)).setListeners(welcomeController);
    }

    @Override
    public void onSearchButtonClick() {
        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onNotificationButtonClick() {
        Toast toast = Toast.makeText(getApplicationContext(), "This feature not implemented yet", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
