package com.isupatches.wisefy;


import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import org.junit.Before;
import org.junit.Test;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.base.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.base.TestUtils.TEST_TIMEOUT;
import static com.isupatches.wisefy.base.TestUtils.TEST_TYPE1;
import static com.isupatches.wisefy.base.TestUtils.TEST_TYPE2;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WiseFyConnectionTests extends BaseAndroidJUnit4TestClass {

    @Before
    public void setUp() {
        super.setUp();
        WiseFyConnection.getInstance().mWiseFyPrerequisites.setWifiManager(mMockWiFiManager);
        WiseFyConnection.getInstance().mWiseFyPrerequisites.setConnectivityManager(mMockConnectivityManager);
    }

    /*
     * isCurrentNetworkConnectedToSSID tests
     */

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_nullSSIDParam() {
        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(null));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_nullConnectionInfo() {
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(TEST_SSID));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_nullSSID() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(null);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);
        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(null));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_differentSSID() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID2);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);
        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(TEST_SSID));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_notAvailable() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(TEST_SSID));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_notAvailableOrConnected() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(TEST_SSID));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_failure_notConnected() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(TEST_SSID));
    }

    @Test
    public void isCurrentNetworkConnectedToSSID_success() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertTrue(WiseFyConnection.getInstance().isCurrentNetworkConnectedToSSID(TEST_SSID));
    }

    /*
     * isNetworkConnected tests
     */

    @Test
    public void isNetworkConnected_failure_nullNetworkInfoParam() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnected(null));
    }

    @Test
    public void isNetworkConnected_failure_notAvailable() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnected(networkInfo));
    }

    @Test
    public void isNetworkConnected_failure_notAvailableOrConnected() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnected(networkInfo));
    }

    @Test
    public void isNetworkConnected_failure_notConnected() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnected(networkInfo));
    }

    @Test
    public void isNetworkConnected_success() {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertTrue(WiseFyConnection.getInstance().isNetworkConnected(networkInfo));
    }

    /*
     * isNetworkConnectedAndMatchesType tests
     */

    @Test
    public void isNetworkConnectedAndMatchesType_failure_nullNetworkInfoParam() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(null, TEST_TYPE1));
    }

    @Test
    public void isNetworkConnectedAndMatchesType_failure_notAvailable() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
    }

    @Test
    public void isNetworkConnectedAndMatchesType_failure_notAvailableOrConnected() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
    }

    @Test
    public void isNetworkConnectedAndMatchesType_failure_notConnected() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
    }

    @Test
    public void isNetworkConnectedAndMatchesType_failure_nullTypeName() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.getTypeName()).thenReturn(null);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
    }

    @Test
    public void isNetworkConnectedAndMatchesType_failure_differentTypeName() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.getTypeName()).thenReturn(TEST_TYPE2);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
    }

    @Test
    public void isNetworkConnectedAndMatchesType_success() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        when(networkInfo.getTypeName()).thenReturn(TEST_TYPE1);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertTrue(WiseFyConnection.getInstance().isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
    }

    /*
     * waitToConnectToSSID tests
     */

    @Test
    public void waitToConnectToSSID_failure_nullSSIDParam() {
        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(null, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_failure_nullConnectionInfo() {
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(null);
        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_failure_nullSSID() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(null);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);
        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(null, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_failure_differentSSID() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID2);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);
        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_failure_notAvailable() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_failure_notAvailableOrConnected() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(false);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_failure_notConnected() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(false);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertFalse(WiseFyConnection.getInstance().waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
    }

    @Test
    public void waitToConnectToSSID_success() {
        WifiInfo currentNetwork = mock(WifiInfo.class);
        when(currentNetwork.getSSID()).thenReturn(TEST_SSID);
        when(mMockWiFiManager.getConnectionInfo()).thenReturn(currentNetwork);

        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isAvailable()).thenReturn(true);
        when(networkInfo.isConnected()).thenReturn(true);
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

        assertTrue(WiseFyConnection.getInstance().waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
    }
}
