package id.ac.ui.cs.mobileprogramming.rizkhiph.lab.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.model.AccessPoint;

public class ListWifiViewModel extends AndroidViewModel {

    private final MutableLiveData<List<AccessPoint>> scanResults;

    public ListWifiViewModel(@NonNull Application application) {
        super(application);

        scanResults = new MutableLiveData<>();
    }

    public LiveData<List<AccessPoint>> getScanResults() {
        return scanResults;
    }

    public void setScanResults(List<AccessPoint> accessPointList) {
        scanResults.setValue(accessPointList);
    }
}
