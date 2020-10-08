package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.model.ListContent;

public class ListContentViewModel extends ViewModel {
    private final MutableLiveData<ListContent> selected = new MutableLiveData<ListContent>();

    public LiveData<ListContent> getSelected() {
        return selected;
    }

    public void select(ListContent content) {
        selected.setValue(content);
    }
}
