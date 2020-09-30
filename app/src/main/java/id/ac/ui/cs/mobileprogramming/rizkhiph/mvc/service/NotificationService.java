package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Calendar;

public class NotificationService {
    private final String TAG = "Notification Service";

    public NotificationService() {};

    public void createNotification(String date, String time, String title, final NotificationInterface service) {
        Log.i(TAG, "[+] On Create Notification");
        BackgroundNotification notif = new BackgroundNotification(new BackgroundNotification.AsyncResponse() {
            @Override
            public void processFinish(String title) {
                Log.i(TAG, "[+] Push notification now");
                service.onReceive(title);
            }
        }, date, time, title);

        notif.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class BackgroundNotification extends AsyncTask<Void, Void, String> {
        private static final String TAG = "Background Notification";
        AsyncResponse delegate;
        String date;
        String time;
        String title;

        public BackgroundNotification(AsyncResponse delegate, String date, String time, String title) {
            this.delegate = delegate;
            this.date = date;
            this.time = time;
            this.title = title;
        }

        public interface AsyncResponse {
            void processFinish(String title);
        }

        @Override
        protected String doInBackground(Void... voids) {
            Long now = System.currentTimeMillis();
            Long dateToTrigger = parseDate(this.date, this.time);
            try {
                Log.i(TAG, "[+] Background process created for " + (dateToTrigger - now));
                Thread.sleep((dateToTrigger - now));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.title;
        }

        @Override
        protected void onPostExecute(String title) {
            this.delegate.processFinish(title);
        }

        public Long parseDate(String date, String time) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DATE);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            int dayToTrigger = dateToInt(date);
            String[] timeToTrigger = time.split(":");
            int hourToTrigger = Integer.parseInt(timeToTrigger[0]);
            int minuteToTrigger = Integer.parseInt(timeToTrigger[1]);
            calendar.set(year, month,
                    dayToTrigger < dayOfWeek ?
                    day + (dayToTrigger + 7 - dayOfWeek) :
                    day + (dayToTrigger - dayOfWeek),
                    hourToTrigger + 7, minuteToTrigger
            );
            return calendar.getTimeInMillis();
        }

        public int dateToInt(String date) {
            switch (date) {
                case "Sundays":
                    return 1;
                case "Mondays":
                    return 2;
                case "Tuesdays":
                    return 3;
                case "Wednesdays":
                    return 4;
                case "Thursdays":
                    return 5;
                case "Fridays":
                    return 6;
                case "Saturdays":
                    return 7;
                default:
                    return 1;
            }
        }
    }
}
