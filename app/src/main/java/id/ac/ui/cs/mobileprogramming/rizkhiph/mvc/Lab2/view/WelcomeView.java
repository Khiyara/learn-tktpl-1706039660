package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;

public class WelcomeView extends LinearLayout {

    public WelcomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListeners(OnClickListener onClickListener){
        findViewById(R.id.search_page_button).setOnClickListener(onClickListener);
    }
}
