package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.controller.SearchController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service.NotificationInterface;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.service.NotificationService;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.view.SearchView;

public class SearchActivity extends Activity implements SearchControllerListener, NotificationInterface {
    private static final String TAG = "Search Activity";
    private NotificationService notificationService = new NotificationService();

    public SearchActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        Button notificationButton = (Button) this.findViewById(R.id.notification_button);
        notificationButton.setVisibility(View.GONE);

        SearchController searchController = new SearchController((SearchView) this.findViewById(R.id.search), this);
        ((SearchView) this.findViewById(R.id.search)).setListeners(searchController);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onCreateNotification() {
        TextView textView = (TextView) this.findViewById(R.id.mal_id_state);
        String data = (String) textView.getText();
        Log.i(TAG, "[+] On Creating Notification for " + data);
        String[] dateTime = data.split("\\|");
        String[] broadcast = dateTime[1].split(" ");
        String date = broadcast[0];
        String time = broadcast[2];
        String title = dateTime[2];
        String airing = dateTime[dateTime.length - 1];
        Toast toast;
        if (!airing.equals("false")) {
            this.getNotificationService().createNotification(date, time, title, this);
            toast = Toast.makeText(getApplicationContext(), title + " will be remineded at " + date + " " + time, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(getApplicationContext(), title + " is not airing", Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    @Override
    public void onStateChange(String text) {
        ((TextView) this.findViewById(R.id.title_text)).setText(text);
        if (text == "Manga") {
            Button notificationButton = (Button) this.findViewById(R.id.notification_button);
            notificationButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetSearch(String text) {
        try {
            JSONParser parse = new JSONParser();
            JSONObject obj = (JSONObject) parse.parse(text);
            JSONArray result = (JSONArray) obj.get("results");
            ArrayList<String> urls = new ArrayList<String>();
            for (int i = 0; i < result.size(); i++) {
                Long id = (Long) ((JSONObject) result.get(i)).get("mal_id");
                String url = Long.toString(id);
                url += "|";
                url += (String) ((JSONObject) result.get(i)).get("url");
                urls.add(url);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, urls);
            ((ListView) this.findViewById(R.id.textView_fetched_results)).setAdapter(adapter);
        } catch (Exception e) {
            Log.e(TAG, "[-] Something went wrong");
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onGetDetail(String text) {
        try {
            JSONParser parse = new JSONParser();
            JSONObject result = (JSONObject) parse.parse(text);
            String data = (String) Long.toString((Long) result.get("mal_id"))
                    + "|"
                    + (String) result.get("broadcast")
                    + "|"
                    + (String) result.get("title")
                    + "|"
                    + (Boolean) result.get("airing");
            TextView textView = (TextView) this.findViewById(R.id.mal_id_state);
            textView.setText(data);
            String views = this.formatString((String) result.toString());
            ArrayList<String> detail = new ArrayList<>();
            detail.add(views);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, detail);
            ((ListView) this.findViewById(R.id.textView_fetched_results)).setAdapter(adapter);

        } catch (Exception e) {
            Log.e(TAG, "[-] Something went wrong");
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Button notificationButton = (Button) this.findViewById(R.id.notification_button);
        notificationButton.setVisibility(View.VISIBLE);
    }

    public String formatString(String text){
        StringBuilder json = new StringBuilder();
        String indentString = "";
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n").append(indentString).append(letter).append("\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n").append(indentString).append(letter);
                    break;
                case ',':
                    json.append(letter).append("\n").append(indentString);
                    break;
                default:
                    json.append(letter);
                    break;
            }
        }
        return json.toString();
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(String result) {
        Log.i(TAG, "[+] Pushing Notification");
        String channelId = createNotificationChannel(this);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(result + " new episode is relese")
                .setContentTitle(result);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
        Log.i(TAG, "[+] Done Pushing Notification");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String createNotificationChannel(Context context) {
        String channelId = "ReminderNotification";
        String channelName = "MVC";
        int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
        notificationChannel.setDescription("Reminder");
        notificationChannel.enableVibration(false);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        return channelId;
    }
}
