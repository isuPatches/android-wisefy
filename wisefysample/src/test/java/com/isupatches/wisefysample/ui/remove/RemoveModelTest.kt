package com.isupatches.wisefysample.ui.remove

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefysample.TEST_SSID_1

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify

import org.junit.Test

internal class RemoveModelTest {

    private val wiseFy = mock<WiseFyPublicApi>()

    private val model = RemoveNetworkModel(wiseFy)

    @Test fun removeNetwork() {
        // When
        model.removeNetwork(TEST_SSID_1, object : RemoveNetworkCallbacks {
            override fun failureRemovingNetwork() {
            }

            override fun networkNotFoundToRemove() {
            }

            override fun networkRemoved() {
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
            }
        })

        // Then
        verify(wiseFy, times(1)).removeNetwork(eq(TEST_SSID_1), any())
    }
}
