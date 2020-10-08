package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.ContentListListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.model.Content;

public class DetailContentFragment extends Fragment implements ContentListListener {
    private static final String TAG = "Detail Content Fragment";

    private ContentListListener listener;
    private ListContentViewModel model;
    private PositionContentViewModel position;
    private TextView id;
    private TextView title;
    private TextView time;
    private TextView date;

    public DetailContentFragment() {}

    public static DetailContentFragment newInstance() {
        return new DetailContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Content content = this.model.getSelected().getValue().getContents().get(this.position.getContentPosition().getValue());
        View view =  inflater.inflate(R.layout.detail_content_fragment, container, false);
        id = view.findViewById(R.id.detail_id);
        title = view.findViewById(R.id.detail_title);
        time = view.findViewById(R.id.detail_time);
        date = view.findViewById(R.id.detail_date);
        this.changeLayout(content.getId(), content.getTitle(), content.getTime(), content.getDate());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(ListContentViewModel.class);
        position = ViewModelProviders.of(requireActivity()).get(PositionContentViewModel.class);
    }

    public void changeLayout(String uid, String uTitle, String uTime, String uDate) {
        Log.i(TAG, "[+] Change Layout ID " + uid + " title " + uTitle + " time " + uTime);
        id.setText("ID: " + uid);
        title.setText("Title: " + uTitle);
        time.setText("Time Notification: " + uTime);
        date.setText("Date: " + uDate);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ContentListListener) {
            listener = (ContentListListener) activity;
        } else {
            throw new IllegalArgumentException("Fragment host must implement ContentListListener");
        }
    }

    @Override
    public void onFragmentChangeRequest(Fragment newFragment) {
        listener.onFragmentChangeRequest(newFragment);
    }
}
