package com.isupatches.wisefy

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry

import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests that pertain to the builder for WiseFy or general use.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class WiseFyTests : BaseUnitTest() {

    companion object {
        private const val EXPECTED_NUMBER_OF_BARS = 4
        private const val EXPECTED_SIGNAL_RESULT = 35
    }

    @Test fun brains_loggingFalse() {
        val wisefy = WiseFy.Brains(InstrumentationRegistry.getInstrumentation().targetContext).logging(false).getSmarts()
        assertEquals(false, wisefy.isLoggingEnabled())
    }

    @Test fun brains_loggingTrue() {
        val wisefy = WiseFy.Brains(InstrumentationRegistry.getInstrumentation().targetContext).logging(true).getSmarts()
        assertEquals(true, wisefy.isLoggingEnabled())
    }

    @Test fun calculateBars() {
        val result = wisefy.calculateBars(TEST_RSSI_LEVEL_HIGH, TEST_NUMBER_OF_BARS)
        assertEquals(EXPECTED_NUMBER_OF_BARS.toLong(), result.toLong())
    }

    @Test fun compareSignalLevel() {
        val result = wisefy.compareSignalLevel(TEST_RSSI_LEVEL_HIGH, TEST_RSSI_LEVEL_LOW)
        assertEquals(EXPECTED_SIGNAL_RESULT.toLong(), result.toLong())
    }

    @Test fun getWiseFyLock() {
        assertNotNull(wisefy.getWiseFyLock())
    }
}
