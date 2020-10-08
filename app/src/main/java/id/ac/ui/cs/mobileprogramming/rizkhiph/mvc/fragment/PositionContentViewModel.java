package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PositionContentViewModel extends ViewModel {
    private final MutableLiveData<Integer> contentPosition = new MutableLiveData<Integer>();

    public MutableLiveData<Integer> getContentPosition() {
        return contentPosition;
    }

    public void setContentPosition(Integer position) {
        contentPosition.setValue(position);
    }
}
