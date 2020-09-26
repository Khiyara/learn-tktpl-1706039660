package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SwitchCompat;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.SearchController;

public class SearchView extends LinearLayout {
    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListeners(SearchController listener){
        findViewById(R.id.search_button).setOnClickListener(listener);
        ((SwitchCompat) findViewById(R.id.type_to_search)).setOnCheckedChangeListener(listener);
    }

    public String getText() {return ((EditText) this.findViewById(R.id.text_to_search)).getText().toString();}
}
