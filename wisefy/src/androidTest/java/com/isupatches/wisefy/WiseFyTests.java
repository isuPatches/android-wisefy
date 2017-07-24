//package com.isupatches.wisefy;
//
//
//import com.google.android.gms.iid.InstanceID;
//import com.isupatches.wisefy.base.TestActivity;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//
//public class WiseFyTests extends BaseTestClass<TestActivity> {
//
//    public WiseFyTests() {
//        super(TestActivity.class);
//    }
//
//    @Test
//    public void builder_loggingFalse() {
//        WiseFy wiseFy = new WiseFy.brains(mActivityTestRule.getActivity()).logging(false).getSmarts();
//        assertEquals(false, wiseFy.mWiseFyConfiguration.isLoggingEnabled());
//        assertEquals(false, wiseFy.isLoggingEnabled());
//    }
//
//    @Test
//    public void builder_loggingTrue() {
//        WiseFy wiseFy = new WiseFy.brains(mActivityTestRule.getActivity()).logging(true).getSmarts();
//        assertEquals(true, wiseFy.mWiseFyConfiguration.isLoggingEnabled());
//        assertEquals(true, wiseFy.isLoggingEnabled());
//    }
//
//    @Test
//    public void calculateBars() {
//        int result = mWiseFy.calculateBars(-35, 5);
//        assertEquals(4, result);
//    }
//
//    @Test
//    public void compareSignalLevel() {
//        int result = mWiseFy.compareSignalLevel(-35, -70);
//        assertEquals(35, result);
//    }
//
//    @Test
//    public void olderGcm_IllegalAccessError_notThrown() {
//        InstanceID instanceID = InstanceID.getInstance(mActivityTestRule.getActivity());
//        assertNotNull(instanceID);
//    }
//}