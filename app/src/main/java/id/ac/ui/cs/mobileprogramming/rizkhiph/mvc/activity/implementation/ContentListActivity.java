package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.ContentListListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.fragment.ListContentFragment;

public class ContentListActivity extends AppCompatActivity implements ContentListListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);
        this.addDynamicFragment();
    }

    private void addDynamicFragment() {
        Fragment fg = ListContentFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fg).commit();
    }

    @Override
    public void onFragmentChangeRequest(Fragment newFragment) {
        this.changeDetailFragment(newFragment);
    }

    public void changeDetailFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
