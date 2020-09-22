package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service.SearchService;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.SearchView;

@SuppressLint("SearchApi")
public class SearchController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private final String TAG = "Search controller";

    private SearchControllerListener listener;
    private SearchView searchView;
    private SearchService searchService;

    private String state = "Anime";

    public SearchController(SearchView searchView, SearchControllerListener listener) {
        this.searchView = searchView;
        this.listener = listener;
        this.searchService = new SearchService(listener);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "[+] Search Button Clicked");
        String name = this.searchView.getText();
        if (this.state == "Anime") {
            this.searchService.searchAnime(name);
        } else {
            this.searchService.searchManga(name);
        }
        listener.onSearch();
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        // SearchActivity Change Text
        Log.i(TAG, "[+] Switch Button Clicked");
        this.setState(isChecked);
        Log.i(TAG, "[+] State Now: " + this.state);
        listener.onStateChange(this.state);
    }

    public void setState(boolean isChecked) {
        if (isChecked) {
            this.state = "Manga";
        } else {
            this.state = "Anime";
        }
    }

    public String getState() {
        return this.state;
    }
}
