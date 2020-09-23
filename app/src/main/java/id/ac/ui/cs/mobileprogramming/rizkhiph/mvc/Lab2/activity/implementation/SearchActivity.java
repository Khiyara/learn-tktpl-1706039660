package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.activity.implementation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.controller.SearchController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.view.SearchView;

public class SearchActivity extends Activity implements SearchControllerListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        SearchController searchController = new SearchController((SearchView) this.findViewById(R.id.search), this);
        ((SearchView) this.findViewById(R.id.search)).setListeners(searchController);
    }

    @Override
    public void onSearch() {
    }

    @Override
    public void onStateChange(String text) {
        ((TextView) this.findViewById(R.id.title_text)).setText(text);
    }

    @Override
    public void onGetResponse(String text) {
        ((TextView) this.findViewById(R.id.textView_fetched_results)).setText(text);
    }
}
