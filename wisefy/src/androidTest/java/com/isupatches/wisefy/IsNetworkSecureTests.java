package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.base.TestActivity;
import com.isupatches.wisefy.constants.Capabilities;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class IsNetworkSecureTests extends BaseTestClass<TestActivity> {

    public IsNetworkSecureTests() {
        super(TestActivity.class);
    }

    @Test
    public void failure_emptyCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "";
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void failure_nullCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void failure_nullScanResult() {
        assertEquals(false, mWiseFy.isNetworkSecure(null));
    }

    @Test
    public void success_withEAP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.EAP;
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void success_withPSK() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.PSK;
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void success_withWEP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.WEP;
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void success_withWPA() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.WPA;
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void success_withWPA2() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.WPA2;
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }

    @Test
    public void failure_otherCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "Other";
        assertEquals(false, mWiseFy.isNetworkSecure(scanResult));
    }
}
