package com.isupatches.wisefy.test.base;


import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.WiseFy;
import org.junit.Before;
import org.junit.Rule;

import static org.mockito.Mockito.mock;


public abstract class BaseTestClass<T extends Activity> extends ActivityTestRule<T> {

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    protected ConnectivityManager mMockConnectivityManager;

    protected WifiManager mMockWiFiManager;

    protected WiseFy mWiseFy;

    public BaseTestClass(Class<T> activityClass) {
        super(activityClass);
    }

    @Before
    public void setUp() {
        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);

        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);

        mActivityTestRule.launchActivity(new Intent());

        mWiseFy = new WiseFy.withContext(mActivityTestRule.getActivity()).logging(true).getSmarts();
    }

    /**
     * HELPERS
     */

    protected void setManagers() {
        mWiseFy.mWifiManager = mMockWiFiManager;
        mWiseFy.mConnectivityManager = mMockConnectivityManager;
    }

    protected void setManagersToNull() {
        mWiseFy.mWifiManager = null;
        mWiseFy.mConnectivityManager = null;
    }

    protected void setConnectivityManagerToNull() {
        mWiseFy.mConnectivityManager = null;
    }
}