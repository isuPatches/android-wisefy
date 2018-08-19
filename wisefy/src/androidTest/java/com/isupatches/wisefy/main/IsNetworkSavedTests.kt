package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network is in a device's list of saved networks.
 *
 * @author Patches
 */
internal class IsNetworkSavedTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isNetworkSaved_failure()
        assertEquals(false, wiseFy.isNetworkSaved(TEST_SSID))
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isNetworkSaved_success()
        mockWiseFySearchUtil.isNetworkASavedConfiguration(false)
        assertEquals(false, wiseFy.isNetworkSaved(TEST_SSID))
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isNetworkSaved_success()
        mockWiseFySearchUtil.isNetworkASavedConfiguration(true)
        assertEquals(true, wiseFy.isNetworkSaved(TEST_SSID))
    }
}
