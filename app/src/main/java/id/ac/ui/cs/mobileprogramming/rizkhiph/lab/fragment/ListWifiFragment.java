package id.ac.ui.cs.mobileprogramming.rizkhiph.lab.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.JniManager;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.MainActivity;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.common.constant.EndpointDummy;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.databinding.ListWifiFragmentBinding;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.model.AccessPoint;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.viewmodel.ListWifiViewModel;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class ListWifiFragment extends Fragment {
    public static final String TAG = "List Wifi Fragment";

    private ListWifiAdapter adapter;
    private WifiManager wifiManager;
    private ListWifiFragmentBinding binding;
    private ListWifiViewModel viewModel;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            List<AccessPoint> accessPoints = new ArrayList<>();
            JniManager jniManager = new JniManager();
            AccessPoint dummyAccessPoint = jniManager.createAccessPointInfo();
            String dummyName = String.valueOf(binding.dummyNameAcp.getText());
            String dummyMac = String.valueOf(binding.dummyNameAcp.getText());
            int dummyStrength = Integer.parseInt(String.valueOf(binding.dummyStrengthAcp.getText()));
            AccessPoint dummyAccessPointCPP = jniManager.nGetAccessPointInfo(dummyName, dummyMac, dummyStrength);
            for (int i = 0; i < scanResults.size(); i++) {
                String name = scanResults.get(i).SSID;
                String mac = scanResults.get(i).BSSID;
                int strength = scanResults.get(i).level;

                AccessPoint accessPoint = new AccessPoint(name, mac, strength);
                accessPoints.add(accessPoint);
            }
            accessPoints.add(dummyAccessPoint);
            accessPoints.add(dummyAccessPointCPP);
            viewModel.setScanResults(accessPoints);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ListWifiFragmentBinding.inflate(inflater, container, false);
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        getContext().registerReceiver(broadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.setWifiEnabled(true);

        adapter = new ListWifiAdapter();
        binding.list.setAdapter(adapter);
        binding.scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    alertTurnOnGps();
                } else {
                    Log.d(TAG, "[+] Scanning");
                    wifiManager.startScan();
                }
            }
        });
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "[+] Sending Post Request");
                postDate();
            }
        });

        return binding.getRoot();
    }

    private void alertTurnOnGps() {
        ((MainActivity) getActivity()).buildTurnOnGps();
    }

    private void postDate() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject object = new JSONObject();
        try {
            LiveData<List<AccessPoint>> liveData = viewModel.getScanResults();
            JSONArray array = new JSONArray();
            for (int i = 0; i < liveData.getValue().size(); i++) {
                JSONObject accessPoint = new JSONObject();
                accessPoint.put("name", liveData.getValue().get(i).getName());
                accessPoint.put("mac", liveData.getValue().get(i).getMac());
                accessPoint.put("strength", liveData.getValue().get(i).getStrength());
                array.put(accessPoint);
            }
            object.put("accessPoints", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = EndpointDummy.ENDPOINT;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "[+] Success Posting");
                        Toast toast = Toast.makeText(getContext(), "Success Posting Data", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "[-] Error Getting Response");
                        Toast toast = Toast.makeText(getContext(), "Failed Posting Data", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
             });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListWifiViewModel.class);

        subscribe(viewModel.getScanResults());
//        wifiManager.startScan();
    }

    private void subscribe(LiveData<List<AccessPoint>> liveData) {
        liveData.observe(getViewLifecycleOwner(), accessPoint -> {
            if (accessPoint != null ) {
                binding.setIsLoading(false);
                adapter.setList(accessPoint);
            }
        });
    }
}
