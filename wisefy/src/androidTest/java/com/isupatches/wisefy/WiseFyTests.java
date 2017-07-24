package com.isupatches.wisefy;


import android.support.test.InstrumentationRegistry;

import com.google.android.gms.iid.InstanceID;
import org.junit.Test;
import static org.junit.Assert.*;


public class WiseFyTests extends BaseAndroidJUnit4TestClass {

    @Test
    public void builder_loggingFalse() {
        WiseFy wiseFy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(false).getSmarts();
        assertEquals(false, wiseFy.mWiseFyConfiguration.isLoggingEnabled());
        assertEquals(false, wiseFy.isLoggingEnabled());
    }

    @Test
    public void builder_loggingTrue() {
        WiseFy wiseFy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(true).getSmarts();
        assertEquals(true, wiseFy.mWiseFyConfiguration.isLoggingEnabled());
        assertEquals(true, wiseFy.isLoggingEnabled());
    }

    @Test
    public void calculateBars() {
        int result = mWiseFy.calculateBars(-35, 5);
        assertEquals(4, result);
    }

    @Test
    public void compareSignalLevel() {
        int result = mWiseFy.compareSignalLevel(-35, -70);
        assertEquals(35, result);
    }

    @Test
    public void olderGcm_IllegalAccessError_notThrown() {
        InstanceID instanceID = InstanceID.getInstance(InstrumentationRegistry.getContext());
        assertNotNull(instanceID);
    }
}