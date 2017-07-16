package com.isupatches.wisefy;


import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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

        mWiseFy = new WiseFy.brains(mActivityTestRule.getActivity()).logging(true).getSmarts();
        setManagers();
    }

    @After
    public void tearDown() {
        mWiseFy.dump();
    }

    /**
     * HELPERS
     */

    void missingPrerequisite() {
        WiseFyPrerequisites mockPrereqs = mock(WiseFyPrerequisites.class);
        mWiseFy.mWiseFyPrerequisites = mockPrereqs;
        when(mockPrereqs.hasPrerequisites()).thenReturn(false);
    }

    void networkAlreadyInConfigurationList() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.checkIfNetworkInConfigurationList(anyString())).thenReturn(true);
    }

    void setupConnection(String ssid, boolean isAvailable, boolean isConnected) {
        WifiInfo mockWiFiInfo = mock(WifiInfo.class);
        when(mockWiFiInfo.getSSID()).thenReturn(ssid);

        when(mMockWiFiManager.getConnectionInfo()).thenReturn(mockWiFiInfo);
        NetworkInfo mockNetworkInfo = mock(NetworkInfo.class);

        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isAvailable()).thenReturn(isAvailable);
        when(mockNetworkInfo.isConnected()).thenReturn(isConnected);
    }

    void setManagers() {
        WiseFyPrerequisites mockPrereqs = mock(WiseFyPrerequisites.class);
        mWiseFy.mWiseFyPrerequisites = mockPrereqs;
        mWiseFy.mWiseFySearch.mWiseFyPrerequisites = mockPrereqs;
        when(mockPrereqs.hasPrerequisites()).thenReturn(true);
        when(mockPrereqs.getWifiManager()).thenReturn(mMockWiFiManager);
        when(mockPrereqs.getConnectivityManager()).thenReturn(mMockConnectivityManager);
    }
}