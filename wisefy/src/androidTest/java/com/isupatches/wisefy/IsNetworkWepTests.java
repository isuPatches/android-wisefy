//package com.isupatches.wisefy;
//
//
//import android.net.wifi.ScanResult;
//import com.isupatches.wisefy.base.TestActivity;
//import com.isupatches.wisefy.constants.Capabilities;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//
//
//public class IsNetworkWepTests extends BaseTestClass<TestActivity> {
//
//    public IsNetworkWepTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void failure_differentCapability() {
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.capabilities = "Other";
//        assertEquals(false, mWiseFy.isNetworkWEP(scanResult));
//    }
//
//    @Test
//    public void failure_emptyCapabilities() {
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.capabilities = "";
//        assertEquals(false, mWiseFy.isNetworkWEP(scanResult));
//    }
//
//    @Test
//    public void failure_nullCapabilities() {
//        ScanResult scanResult = mock(ScanResult.class);
//        assertEquals(false, mWiseFy.isNetworkWEP(scanResult));
//    }
//
//    @Test
//    public void failure_nullScanResult() {
//        assertEquals(false, mWiseFy.isNetworkWEP(null));
//    }
//
//    @Test
//    public void success() {
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.capabilities = Capabilities.WEP;
//        assertEquals(true, mWiseFy.isNetworkWEP(scanResult));
//    }
//}
