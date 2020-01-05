package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network is in a device's list of saved networks.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsNetworkSavedTests : BaseInstrumentationTest() {

    @Test
    fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isNetworkSaved_failure()
        assertEquals(false, wisefy.isNetworkSaved(TEST_SSID))
    }

    @Test
    fun failure() {
        mockWiseFyPrechecksUtil.isNetworkSaved_success()
        mockWiseFySearchUtil.isNetworkASavedConfiguration(false)
        assertEquals(false, wisefy.isNetworkSaved(TEST_SSID))
    }

    @Test
    fun success() {
        mockWiseFyPrechecksUtil.isNetworkSaved_success()
        mockWiseFySearchUtil.isNetworkASavedConfiguration(true)
        assertEquals(true, wisefy.isNetworkSaved(TEST_SSID))
    }
}
