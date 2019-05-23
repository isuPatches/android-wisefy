package com.isupatches.wisefysample.ui.add

import android.content.Intent
import android.net.wifi.WifiConfiguration
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.BAD_WIFI_MANAGER_RETURN
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.TEST_PASSWORD_1
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndReplaceText
import com.isupatches.wisefysample.ui.main.MainActivity

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

internal class AddNetworkRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi
) {

    companion object {
        private const val NETWORK_ID = 1
        private val SAVED_NETWORK = mock<WifiConfiguration>()
    }

    fun launchAddNetworkScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_add)).performClick()
    }

    fun addOpenNetwork() {
        onView(withId(R.id.openNetworkRdb)).performClick()
        onView(withId(R.id.networkNameEdt)).performScrollToAndReplaceText(TEST_SSID_1)
        onView((withId(R.id.addNetworkBtn))).performScrollToAndClick()
    }

    fun addWEPNetwork() {
        onView(withId(R.id.wepNetworkRdb)).performClick()
        enterNetworkNameAndPassword()
        onView((withId(R.id.addNetworkBtn))).performScrollToAndClick()
    }

    fun addWPA2Network() {
        onView(withId(R.id.wpa2NetworkRdb)).performClick()
        enterNetworkNameAndPassword()
        onView((withId(R.id.addNetworkBtn))).performScrollToAndClick()
    }

    fun withSuccessAddingOpenNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
            null
        }.whenever(wiseFyPublicApi).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun withWifiManagerFailureAddingOpenNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
            null
        }.whenever(wiseFyPublicApi).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun withWiseFyFailureAddingOpenNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.failureAddingNetwork(MISSING_PARAMETER)
            null
        }.whenever(wiseFyPublicApi).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun withSuccessAddingWEPNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
            null
        }.whenever(wiseFyPublicApi).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withWifiManagerFailureAddingWEPNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
            null
        }.whenever(wiseFyPublicApi).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withWiseFyFailureAddingWEPNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.failureAddingNetwork(MISSING_PARAMETER)
            null
        }.whenever(wiseFyPublicApi).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withSuccessAddingWPA2Network() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
            null
        }.whenever(wiseFyPublicApi).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withWifiManagerFailureAddingWPA2Network() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
            null
        }.whenever(wiseFyPublicApi).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withWiseFyFailureAddingWPA2Network() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.failureAddingNetwork(MISSING_PARAMETER)
            null
        }.whenever(wiseFyPublicApi).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun verifyTriedToAddOpenNetwork() {
        verify(wiseFyPublicApi, times(1)).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun verifyTriedToAddWEPNetwork() {
        verify(wiseFyPublicApi, times(1)).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun verifyTriedToAddWPA2Network() {
        verify(wiseFyPublicApi, times(1)).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    /*
     * Helpers
     */

    private fun enterNetworkNameAndPassword() {
        onView(withId(R.id.networkNameEdt)).performScrollToAndReplaceText(TEST_SSID_1)
        onView(withId(R.id.networkPasswordEdt)).performScrollToAndReplaceText(TEST_PASSWORD_1)
    }
}
