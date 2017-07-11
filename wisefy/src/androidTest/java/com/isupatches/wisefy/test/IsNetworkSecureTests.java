package com.isupatches.wisefy.test;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.constants.Capabilities;
import com.isupatches.wisefy.test.base.BaseTestClass;
import com.isupatches.wisefy.test.base.TestActivity;
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
    public void success_withWEP() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.WEP;
        assertEquals(true, mWiseFy.isNetworkSecure(scanResult));
    }
}
