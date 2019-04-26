package com.isupatches.wisefysample;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.isupatches.wisefy.WiseFy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WiseFy wiseFy = new WiseFy.Brains(this, false, false)
                .logging(true)
                .getSmarts();
        Log.d("WiseFy", "MainActivity - \n" +
            "isDeviceConnectedToMobileNetwork:  " +  wiseFy.isDeviceConnectedToMobileNetwork() + "\n" +
            "isDeviceConnectedToWifiNetwork: " + wiseFy.isDeviceConnectedToWifiNetwork() + "\n" +
            "isDeviceConnectedToMobileOrWifiNetwork: " + wiseFy.isDeviceConnectedToMobileOrWifiNetwork()
        );

        boolean result = wiseFy.isDeviceRoaming();
        Log.d("WiseFy", "Result: " + result);
    }
}
