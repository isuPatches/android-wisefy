package com.isupatches.android.wisefy.ui.add

import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.TEST_PASSWORD_1
import com.isupatches.android.wisefy.TEST_SSID_1
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

internal class AddNetworkModelTest {

    private val wiseFy = mock<WiseFyPublicApi>()

    private val model = AddNetworkModel(wiseFy)

    private val addNetworkCallbacks = object : AddNetworkCallbacks {
        override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
        }

        override fun failureAddingNetwork(wifiManagerReturn: Int) {
        }

        override fun wisefyFailure(wisefyFailureCode: Int) {
        }
    }

    @Test
    fun addOpenNetwork() {
        // When
        model.addOpenNetwork(TEST_SSID_1, addNetworkCallbacks)

        // Then
        verify(wiseFy, times(1)).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    @Test
    fun addWepNetwork() {
        // When
        model.addWEPNetwork(TEST_SSID_1, TEST_PASSWORD_1, addNetworkCallbacks)

        // Then
        verify(wiseFy, times(1)).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    @Test
    fun addWPA2Network() {
        // When
        model.addWPA2Network(TEST_SSID_1, TEST_PASSWORD_1, addNetworkCallbacks)

        // Then
        verify(wiseFy, times(1)).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }
}
