package com.isupatches.wisefysample.ui.add

import android.content.Intent
import android.net.wifi.WifiConfiguration
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.BAD_WIFI_MANAGER_RETURN
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.TEST_PASSWORD_1
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.internal.base.BaseRobot
import com.isupatches.wisefysample.internal.espresso.checkIsChecked
import com.isupatches.wisefysample.internal.espresso.checkIsDisplayed
import com.isupatches.wisefysample.internal.espresso.checkIsInvisible
import com.isupatches.wisefysample.internal.espresso.checkIsVisible
import com.isupatches.wisefysample.internal.espresso.getString
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndCheckIsDisplayed
import com.isupatches.wisefysample.internal.espresso.performScrollToAndClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndReplaceText
import com.isupatches.wisefysample.internal.models.NetworkType
import com.isupatches.wisefysample.internal.preferences.AddNetworkStore
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.ui.main.MainActivity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString

internal class AddNetworkRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi,
    private val addNetworkStore: AddNetworkStore,
    permissionUtil: PermissionUtil
) : BaseRobot(activityTestRule, permissionUtil) {

    companion object {
        private const val NETWORK_ID = 1
        private val SAVED_NETWORK = mock<WifiConfiguration>()
    }

    /*
     * Preconditions
     */

    fun withSuccessAddingOpenNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
        }.whenever(wiseFyPublicApi).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun withWifiManagerFailureAddingOpenNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.failureAddingNetwork(BAD_WIFI_MANAGER_RETURN)
        }.whenever(wiseFyPublicApi).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun withWiseFyFailureAddingOpenNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as AddNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun withSuccessAddingWEPNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.networkAdded(NETWORK_ID, SAVED_NETWORK)
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
        }.whenever(wiseFyPublicApi).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withWiseFyFailureAddingWEPNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
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
        }.whenever(wiseFyPublicApi).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withWiseFyFailureAddingWPA2Network() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as AddNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun withOpenNetworkInStore() {
        with(addNetworkStore) {
            setNetworkType(NetworkType.OPEN)
            setLastUsedNetworkName(TEST_SSID_1)
        }
    }

    fun withWEPNetworkInStore() {
        with(addNetworkStore) {
            setNetworkType(NetworkType.WEP)
            setLastUsedNetworkName(TEST_SSID_1)
            setLastUsedNetworkPassword(TEST_PASSWORD_1)
        }
    }

    fun withWPA2NetworkInStore() {
        with(addNetworkStore) {
            setNetworkType(NetworkType.WPA2)
            setLastUsedNetworkName(TEST_SSID_1)
            setLastUsedNetworkPassword(TEST_PASSWORD_1)
        }
    }

    /*
     * Actions
     */

    fun launchAddNetworkScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_add)).performClick()
    }

    fun addOpenNetwork() {
        onView(withId(R.id.openNetworkRdb)).performScrollToAndClick()
        onView(withId(R.id.networkNameEdt)).performScrollToAndReplaceText(TEST_SSID_1)
        onView((withId(R.id.addNetworkBtn))).performScrollToAndClick()
    }

    fun addWEPNetwork() {
        onView(withId(R.id.wepNetworkRdb)).performScrollToAndClick()
        enterNetworkNameAndPassword()
        onView((withId(R.id.addNetworkBtn))).performScrollToAndClick()
    }

    fun addWPA2Network() {
        onView(withId(R.id.wpa2NetworkRdb)).performScrollToAndClick()
        enterNetworkNameAndPassword()
        onView((withId(R.id.addNetworkBtn))).performScrollToAndClick()
    }

    fun checkOpenNetwork() {
        onView(withId(R.id.openNetworkRdb)).performScrollToAndClick()
    }

    fun checkWEPNetwork() {
        onView(withId(R.id.wepNetworkRdb)).performScrollToAndClick()
    }

    fun checkWPA2Network() {
        onView(withId(R.id.wpa2NetworkRdb)).performScrollToAndClick()
    }

    /*
     * Permission Helpers
     */

    fun permissionCallbackTriggered(requestCode: Int, permissionResult: Int) {
        val activity = activityTestRule.activity
        val fragment: AddNetworkFragment? = activity
                .supportFragmentManager
                .findFragmentByTag(
                    AddNetworkFragment.TAG
                ) as? AddNetworkFragment

        activity.runOnUiThread {
            fragment?.onRequestPermissionsResult(
                requestCode,
                emptyArray(),
                intArrayOf(permissionResult)
            )
        }
    }

    fun permissionCallbackTriggeredWithEmptyArray(requestCode: Int) {
        val activity = activityTestRule.activity
        val fragment: AddNetworkFragment? = activity
            .supportFragmentManager
            .findFragmentByTag(
                AddNetworkFragment.TAG
            ) as? AddNetworkFragment

        activity.runOnUiThread {
            fragment?.onRequestPermissionsResult(
                requestCode,
                emptyArray(),
                intArrayOf()
            )
        }
    }

    /*
     * Verification
     */

    fun verifyNewNetworkIsDisplayed() {
        onView(withText(containsString("Network added"))).checkIsDisplayed()
    }

    fun verifyFailureAddingNetworkIsDisplayed() {
        onView(withText(
            activityTestRule.getString(R.string.failure_adding_network, -1)
        )).checkIsDisplayed()
    }

    fun verifyOpenNetworkIsChecked() {
        onView(withId(R.id.openNetworkRdb)).checkIsChecked()
    }

    fun verifyWEPNetworkIsChecked() {
        onView(withId(R.id.wepNetworkRdb)).checkIsChecked()
    }

    fun verifyWPA2NetworkIsChecked() {
        onView(withId(R.id.wpa2NetworkRdb)).checkIsChecked()
    }

    fun verifyOnlyNetworkNameIsVisible() {
        onView(withId(R.id.networkNameEdt)).checkIsVisible()
        onView(withId(R.id.networkPasswordEdt)).checkIsInvisible()
    }

    fun verifyNetworkNameAndPasswordAreBothVisible() {
        onView(withId(R.id.networkNameEdt)).checkIsVisible()
        onView(withId(R.id.networkPasswordEdt)).checkIsVisible()
    }

    fun verifyNetworkNameAndPasswordIsPopulated() {
        onView(allOf(withId(R.id.networkNameEdt), withText(TEST_SSID_1))).performScrollToAndCheckIsDisplayed()
        onView(
            allOf(withId(R.id.networkPasswordEdt), withText(TEST_PASSWORD_1))
        ).performScrollToAndCheckIsDisplayed()
    }

    fun verifyTriedToAddOpenNetwork(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).addOpenNetwork(eq(TEST_SSID_1), any())
    }

    fun verifyTriedToAddWEPNetwork(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).addWEPNetwork(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    fun verifyTriedToAddWPA2Network(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).addWPA2Network(
            eq(TEST_SSID_1),
            eq(TEST_PASSWORD_1),
            any()
        )
    }

    /*
     * Form Helpers
     */

    private fun enterNetworkNameAndPassword() {
        onView(withId(R.id.networkNameEdt)).performScrollToAndReplaceText(TEST_SSID_1)
        onView(withId(R.id.networkPasswordEdt)).performScrollToAndReplaceText(TEST_PASSWORD_1)
    }
}
