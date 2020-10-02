package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller;

import android.util.Log;
import android.view.View;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.TimerControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.TimerView;

public class TimerController implements View.OnClickListener {
    private final String TAG = "Search controller";

    private TimerControllerListener listener;
    private TimerView timerView;

    public TimerController(TimerView timerView, TimerControllerListener listener) {
        this.timerView = timerView;
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_pause) {
            Log.i(TAG, "[+] Button Pause Clicked");
            this.getListener().pauseTimer();
        } else if (view.getId() == R.id.button_start) {
            Log.i(TAG, "[+] Button Start Clicked");
            this.getListener().startTimer();
        } else {
            Log.i(TAG, "[+] Button Reset Clicked");
            this.getListener().resetTimer();
        }
    }

    public TimerView getTimerView() {
        return timerView;
    }

    public TimerControllerListener getListener() {
        return listener;
    }
}
