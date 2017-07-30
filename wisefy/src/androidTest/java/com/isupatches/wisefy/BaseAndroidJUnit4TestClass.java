package com.isupatches.wisefy;


import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public abstract class BaseAndroidJUnit4TestClass {

    ConnectivityManager mMockConnectivityManager;

    protected WifiManager mMockWiFiManager;

    protected WiseFy mWiseFy;

    protected static final Integer VERIFICATION_SUCCESS_TIMEOUT = 5000;

    static final Integer VERIFICATION_FAILURE_TIMEOUT = 3000;

    @Before
    public void setUp() {
        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);

        mMockWiFiManager = mock(WifiManager.class);
        mMockConnectivityManager = mock(ConnectivityManager.class);

        mWiseFy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(true).getSmarts();
        setManagers();
    }

    @After
    public void tearDown() {
        mWiseFy.dump();
    }

    /**
     * HELPERS
     */

    public void missingPrerequisite() {
        WiseFyPrerequisites mockPrereqs = mock(WiseFyPrerequisites.class);
        mWiseFy.mWiseFyPrerequisites = mockPrereqs;
        when(mockPrereqs.hasPrerequisites()).thenReturn(false);
    }

    void networkAlreadyInConfigurationList() {
        WiseFySearch mockWiseFySearch = mock(WiseFySearch.class);
        mWiseFy.mWiseFySearch = mockWiseFySearch;
        when(mockWiseFySearch.isNetworkASavedConfiguration(anyString())).thenReturn(true);
    }

    private void setManagers() {
        WiseFyPrerequisites mockPrereqs = mock(WiseFyPrerequisites.class);
        mWiseFy.mWiseFyPrerequisites = mockPrereqs;
        mWiseFy.mWiseFySearch.mWiseFyPrerequisites = mockPrereqs;
        when(mockPrereqs.hasPrerequisites()).thenReturn(true);
        when(mockPrereqs.getWifiManager()).thenReturn(mMockWiFiManager);
        when(mockPrereqs.getConnectivityManager()).thenReturn(mMockConnectivityManager);
    }
}
