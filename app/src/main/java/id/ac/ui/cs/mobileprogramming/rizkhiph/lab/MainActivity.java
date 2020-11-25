package id.ac.ui.cs.mobileprogramming.rizkhiph.lab;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.fragment.ListWifiFragment;

// Will implement later main page when App started
public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1001;
    private static final String TAG = "Main Activity";
    private static final int MY_PERMISSIONS_REQUEST = 1042;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        if (savedInstanceState == null) {
            ListWifiFragment fragment = new ListWifiFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, ListWifiFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                &&  grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "[+] Permission Granted");
        }
    }

    public boolean checkPermission() {
        Activity activity = (Activity) this;
        boolean permission = true;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> requiringList = new ArrayList<>();

            permission = activity.checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, "ACCESS_WIFI_STATE: " + permission);
            if (!permission) {
                requiringList.add(Manifest.permission.ACCESS_WIFI_STATE);
            }

            permission = activity.checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, "CHANGE_WIFI_STATE: " + permission);
            if (!permission) {
                requiringList.add(Manifest.permission.CHANGE_WIFI_STATE);
            }

            permission = activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, "ACCESS_COARSE_LOCATION: " + permission);
            if (!permission) {
                requiringList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (requiringList.size() > 0) {
                String[] stringArray = requiringList.toArray(new String[0]);
                activity.requestPermissions(stringArray, MY_PERMISSIONS_REQUEST);
            }
        }
        return permission;
    }

    public void buildTurnOnGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS are disabled, do you want to enable it allow us scan wifi?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to quit ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }
}
