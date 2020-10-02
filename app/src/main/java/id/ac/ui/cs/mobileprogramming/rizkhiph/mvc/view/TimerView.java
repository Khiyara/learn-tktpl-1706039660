package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;

public class TimerView extends RelativeLayout {
    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.button_pause).setOnClickListener(onClickListener);
        findViewById(R.id.button_start).setOnClickListener(onClickListener);
        findViewById(R.id.button_reset).setOnClickListener(onClickListener);
    }
}
