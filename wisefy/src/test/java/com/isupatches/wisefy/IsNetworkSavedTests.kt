package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a network is in a device's list of saved networks.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsNetworkSavedTests : BaseUnitTest() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isNetworkSaved_failure()
        assertEquals(false, wisefy.isNetworkSaved(TEST_SSID))
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isNetworkSaved_success()
        mockWiseFySearchUtil.isNetworkASavedConfiguration(false)
        assertEquals(false, wisefy.isNetworkSaved(TEST_SSID))
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isNetworkSaved_success()
        mockWiseFySearchUtil.isNetworkASavedConfiguration(true)
        assertEquals(true, wisefy.isNetworkSaved(TEST_SSID))
    }
}
