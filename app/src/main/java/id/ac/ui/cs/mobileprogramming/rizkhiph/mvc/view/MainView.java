package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;

public class MainView extends LinearLayout {

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListeners(OnClickListener onClickListener){
        findViewById(R.id.search_page_button).setOnClickListener(onClickListener);
        findViewById(R.id.notification_view_button).setOnClickListener(onClickListener);
    }
}
