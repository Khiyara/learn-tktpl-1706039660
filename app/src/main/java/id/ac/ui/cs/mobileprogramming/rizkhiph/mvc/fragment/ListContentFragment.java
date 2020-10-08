package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.ContentListListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.activity.implementation.SearchActivity;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.model.Content;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.model.ListContent;

public class ListContentFragment extends ListFragment {
    private static final String TAG = "List Content Fragment";

    private ContentListListener listener;
    private ListContentViewModel model;
    private PositionContentViewModel position;
    private ListContent listContent;

    public ListContentFragment() {}

    public static ListContentFragment newInstance() {
        return new ListContentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(requireActivity()).get(ListContentViewModel.class);
        SharedPreferences mPrefs = getActivity().
                getSharedPreferences(SearchActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Content", "");
        if (json.equals("")) {
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            ListContent listContent = new ListContent();
            String jsonContent = gson.toJson(listContent);
            prefsEditor.putString("Content", jsonContent);
            prefsEditor.apply();
        }
        Log.i(TAG, "[+] JSON " + json);
        listContent = gson.fromJson(json, ListContent.class);
        model.select(listContent);
        position = ViewModelProviders.of(requireActivity()).get(PositionContentViewModel.class);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_content_fragment, container, false);
        ArrayAdapter<Content> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, this.getListContent().getContents());
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Content content = this.getListContent().getContents().get(position);
        Log.i(TAG, "[+] Id " + content.getId() + " title " + content.getTitle() + " ");
        this.getPosition().setContentPosition(position);
        this.notifyFragmentChange(DetailContentFragment.newInstance());
    }

    private void notifyFragmentChange(Fragment newFragment) {
        this.getListener().onFragmentChangeRequest(newFragment);
    }

    public ListContent getListContent() {
        return listContent;
    }

    public ListContentViewModel getModel() {
        return model;
    }

    public PositionContentViewModel getPosition() {
        return position;
    }

    public ContentListListener getListener() {
        return listener;
    }
}
