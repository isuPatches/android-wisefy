package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Tests that pertain to the builder for WiseFy or general use.
 *
 * @author Patches
 * @since 3.0
 */
internal class WiseFyTests : BaseInstrumentationTest() {

    companion object {
        private const val EXPECTED_NUMBER_OF_BARS = 4
        private const val EXPECTED_SIGNAL_RESULT = 35
    }

    @Test
    fun calculateBars() {
        val result = wisefy.calculateBars(TEST_RSSI_LEVEL_HIGH, TEST_NUMBER_OF_BARS)
        assertEquals(EXPECTED_NUMBER_OF_BARS.toLong(), result.toLong())
    }

    @Test
    fun compareSignalLevel() {
        val result = wisefy.compareSignalLevel(TEST_RSSI_LEVEL_HIGH, TEST_RSSI_LEVEL_LOW)
        assertEquals(EXPECTED_SIGNAL_RESULT.toLong(), result.toLong())
    }

    @Test
    fun getWiseFyLock() {
        assertNotNull(wisefy.getWiseFyLock())
    }
}
