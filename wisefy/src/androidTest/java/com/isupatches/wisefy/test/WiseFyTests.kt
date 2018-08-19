package com.isupatches.wisefy.test

import android.support.test.InstrumentationRegistry

import com.google.android.gms.iid.InstanceID

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_NUMBER_OF_BARS
import com.isupatches.wisefy.TEST_RSSI_LEVEL_HIGH
import com.isupatches.wisefy.TEST_RSSI_LEVEL_LOW
import com.isupatches.wisefy.WiseFy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Tests that pertain to the builder for WiseFy or general use.
 *
 * @author Patches
 */
internal class WiseFyTests : BaseAndroidJUnit4TestClass() {

    companion object {
        private const val EXPECTED_NUMBER_OF_BARS = 4
        private const val EXPECTED_SIGNAL_RESULT = 35
    }

    @Test fun brains_loggingFalse() {
        val wisefy = WiseFy.Brains(InstrumentationRegistry.getContext()).logging(false).getSmarts()
        assertEquals(false, wisefy.isLoggingEnabled())
    }

    @Test fun brains_loggingTrue() {
        val wisefy = WiseFy.Brains(InstrumentationRegistry.getContext()).logging(true).getSmarts()
        assertEquals(true, wisefy.isLoggingEnabled())
    }

    @Test fun calculateBars() {
        val result = wiseFy.calculateBars(TEST_RSSI_LEVEL_HIGH, TEST_NUMBER_OF_BARS)
        assertEquals(EXPECTED_NUMBER_OF_BARS.toLong(), result.toLong())
    }

    @Test fun compareSignalLevel() {
        val result = wiseFy.compareSignalLevel(TEST_RSSI_LEVEL_HIGH, TEST_RSSI_LEVEL_LOW)
        assertEquals(EXPECTED_SIGNAL_RESULT.toLong(), result.toLong())
    }

    @Test fun getWiseFyLock() {
        assertNotNull(wiseFy.getWiseFyLock())
    }

    @Test fun olderGcm_IllegalAccessError_notThrown() {
        val instanceID = InstanceID.getInstance(InstrumentationRegistry.getContext())
        assertNotNull(instanceID)
    }
}
