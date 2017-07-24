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
//public class IsNetworkPSKTest extends BaseTestClass<TestActivity> {
//
//    public IsNetworkPSKTest() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void failure_differentCapability() {
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.capabilities = "Other";
//        assertEquals(false, mWiseFy.isNetworkPSK(scanResult));
//    }
//
//    @Test
//    public void failure_emptyCapabilities() {
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.capabilities = "";
//        assertEquals(false, mWiseFy.isNetworkPSK(scanResult));
//    }
//
//    @Test
//    public void failure_nullCapabilities() {
//        ScanResult scanResult = mock(ScanResult.class);
//        assertEquals(false, mWiseFy.isNetworkPSK(scanResult));
//    }
//
//    @Test
//    public void failure_nullScanResult() {
//        assertEquals(false, mWiseFy.isNetworkPSK(null));
//    }
//
//    @Test
//    public void success() {
//        ScanResult scanResult = mock(ScanResult.class);
//        scanResult.capabilities = Capabilities.PSK;
//        assertEquals(true, mWiseFy.isNetworkPSK(scanResult));
//    }
//}
