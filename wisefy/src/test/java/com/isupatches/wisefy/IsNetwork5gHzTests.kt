package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertEquals
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a network is 5 gHz.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsNetwork5gHzTests : BaseUnitTest() {

    @Before fun setup() {
        assumeTrue(
            "Can only run on API Level 21 or newer",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        )
    }

    @Test fun currentNetwork_failure_above5ghz() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ)
        assertEquals(false, wisefy.isNetwork5gHz())
    }

    @Test fun currentNetwork_failure_below5ghz() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_BELOW_5GHZ)
        assertEquals(false, wisefy.isNetwork5gHz())
    }

    @Test fun currentNetwork_failure_null() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork_null()
        assertEquals(false, wisefy.isNetwork5gHz())
    }

    @Test fun currentNetwork_success() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_5GHZ)
        assertEquals(true, wisefy.isNetwork5gHz())
    }

    @Test fun provideWifiInfo_failure_above5ghz() {
        val network = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ)
        assertEquals(false, wisefy.isNetwork5gHz(network))
    }

    @Test fun provideWifiInfo_failure_below5ghz() {
        val network = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_BELOW_5GHZ)
        assertEquals(false, wisefy.isNetwork5gHz(network))
    }

    @Test fun provideWifiInfo_failure_null() {
        assertEquals(false, wisefy.isNetwork5gHz(null))
    }

    @Test fun provideWifiInfo_success() {
        val mockWifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_5GHZ)
        assertEquals(true, wisefy.isNetwork5gHz(mockWifiInfo))
    }
}
