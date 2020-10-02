package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.TimerControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.TimerController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.TimerView;

// Big shoutout to Coding in Flow tutorial
public class TimerActivity extends AppCompatActivity implements TimerControllerListener {
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView textViewCountDown;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_layout);

        TimerController timerController = new TimerController((TimerView) this.findViewById(R.id.timer), this);
        ((TimerView) this.findViewById(R.id.timer)).setListeners(timerController);
        textViewCountDown = findViewById(R.id.text_view_countdown);

    }
    @Override
    public void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftInMillis;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timerRunning = false;
                
            }
        }.start();
        timerRunning = true;
        
    }

    @Override
    public void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        
    }

    @Override
    public void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeLeftFormatted);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);
        editor.apply();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);
        editor.apply();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        timeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        timerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        
        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();
            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDownText();
                
            } else {
                startTimer();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        timeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        timerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        
        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();
            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDownText();
                
            } else {
                startTimer();
            }
        }
    }
}