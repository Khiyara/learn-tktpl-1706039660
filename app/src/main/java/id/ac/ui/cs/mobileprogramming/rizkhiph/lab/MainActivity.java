package id.ac.ui.cs.mobileprogramming.rizkhiph.lab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.databinding.MainLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000;
    private static final String TAG = "Main Activity";
    private MainLayoutBinding binding;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;
    private long endTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonStart.setOnClickListener(v -> {
            Log.d(TAG, "[+] Clicked");
            startTimer();
        });
    }

    void updateCountDownText() {
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        Log.d(TAG, "[+]" + seconds);
        String timeLeft = String.format(Locale.getDefault(), "%2d", seconds);
        binding.textViewCountdown.setText(timeLeft);
        if (seconds == 0) {
            binding.textViewDone.setVisibility(View.VISIBLE);
        }
    }

    public void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();

    }

    private void startTimer() {
        resetTimer();
        binding.textViewDone.setVisibility(View.GONE);
        endTime = System.currentTimeMillis() + timeLeftInMillis;
        Log.d(TAG, "[+] Start Timer" + endTime);
        if (!timerRunning) {
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    Log.d(TAG, "[+] Ticking");
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    timerRunning = false;
                    Log.d(TAG, "[+] Finish");
                }
            }.start();
            timerRunning = true;
        }
    }

    private void stopTimer() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);
        editor.apply();
    }

    private void resumeTimer() {
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
    protected void onStop() {
        super.onStop();
        stopTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        resumeTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeTimer();
    }
}
