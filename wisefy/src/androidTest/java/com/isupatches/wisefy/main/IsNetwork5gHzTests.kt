package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_NETWORK_FREQUENCY_5GHZ
import com.isupatches.wisefy.TEST_NETWORK_FREQUENCY_ABOVE_5GHZ
import com.isupatches.wisefy.TEST_NETWORK_FREQUENCY_BELOW_5GHZ

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network is 5 gHz.
 *
 * @author Patches
 */
internal class IsNetwork5gHzTests : BaseAndroidJUnit4TestClass() {

    @Test fun currentNetwork_failure_above5ghz() {
        if (preLollipop()) {
            return
        }

        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ)
        assertEquals(false, wiseFy.isNetwork5gHz())
    }

    @Test fun currentNetwork_failure_below5ghz() {
        if (preLollipop()) {
            return
        }

        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_BELOW_5GHZ)
        assertEquals(false, wiseFy.isNetwork5gHz())
    }

    @Test fun currentNetwork_failure_null() {
        if (preLollipop()) {
            return
        }

        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork_null()
        assertEquals(false, wiseFy.isNetwork5gHz())
    }

    @Test fun currentNetwork_success() {
        if (preLollipop()) {
            return
        }

        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_5GHZ)
        assertEquals(true, wiseFy.isNetwork5gHz())
    }

    @Test fun provideWifiInfo_failure_above5ghz() {
        if (preLollipop()) {
            return
        }

        val network = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_ABOVE_5GHZ)
        assertEquals(false, wiseFy.isNetwork5gHz(network))
    }

    @Test fun provideWifiInfo_failure_below5ghz() {
        if (preLollipop()) {
            return
        }

        val network = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_BELOW_5GHZ)
        assertEquals(false, wiseFy.isNetwork5gHz(network))
    }

    @Test fun provideWifiInfo_failure_null() {
        if (preLollipop()) {
            return
        }

        assertEquals(false, wiseFy.isNetwork5gHz(null))
    }

    @Test fun provideWifiInfo_success() {
        if (preLollipop()) {
            return
        }

        val mockWifiInfo = mockNetworkUtil.networkWithFrequency(TEST_NETWORK_FREQUENCY_5GHZ)
        assertEquals(true, wiseFy.isNetwork5gHz(mockWifiInfo))
    }
}
