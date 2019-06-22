package com.isupatches.wisefysample.ui.misc

import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule

import com.isupatches.wisefy.WiseFy.Companion.MIN_FREQUENCY_5GHZ
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.TEST_IP
import com.isupatches.wisefysample.internal.base.BaseRobot
import com.isupatches.wisefysample.internal.espresso.checkIsDisplayed
import com.isupatches.wisefysample.internal.espresso.getString
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndClick
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.internal.util.SdkUtil
import com.isupatches.wisefysample.ui.main.MainActivity

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.containsString

import org.mockito.Mockito.`when`

internal class MiscRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi,
    private val sdkUtil: SdkUtil,
    permissionUtil: PermissionUtil
) : BaseRobot(activityTestRule, permissionUtil) {

    companion object {
        private val CURRENT_NETWORK = mock<WifiInfo>()
        private val CURRENT_NETWORK_INFO = mock<NetworkInfo>()

        private val ACCESS_POINT = mock<ScanResult>()
        private val SAVED_NETWORK = mock<WifiConfiguration>()
    }

    /*
     * Preconditions
     */

    fun withSuccessDisablingWifi() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as DisableWifiCallbacks
            callback.wifiDisabled()
        }.whenever(wiseFyPublicApi).disableWifi(any())
    }

    fun withFailureDisablingWifi() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as DisableWifiCallbacks
            callback.failureDisablingWifi()
        }.whenever(wiseFyPublicApi).disableWifi(any())
    }

    fun withWiseFyFailureDisablingWifi() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as DisableWifiCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).disableWifi(any())
    }

    fun withSuccessEnablingWifi() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as EnableWifiCallbacks
            callback.wifiEnabled()
        }.whenever(wiseFyPublicApi).enableWifi(any())
    }

    fun withFailureEnablingWifi() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as EnableWifiCallbacks
            callback.failureEnablingWifi()
        }.whenever(wiseFyPublicApi).enableWifi(any())
    }

    fun withWiseFyFailureEnablingWifi() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as EnableWifiCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).enableWifi(any())
    }

    fun withSuccessGettingCurrentNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkCallbacks
            callback.retrievedCurrentNetwork(CURRENT_NETWORK)
        }.whenever(wiseFyPublicApi).getCurrentNetwork(any())
    }

    fun withNoCurrentNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkCallbacks
            callback.noCurrentNetwork()
        }.whenever(wiseFyPublicApi).getCurrentNetwork(any())
    }

    fun withWiseFyFailureGettingCurrentNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).getCurrentNetwork(any())
    }

    fun withSuccessGettingCurrentNetworkInfo() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkInfoCallbacks
            callback.retrievedCurrentNetworkInfo(CURRENT_NETWORK_INFO)
        }.whenever(wiseFyPublicApi).getCurrentNetworkInfo(any())
    }

    fun withNoCurrentNetworkInfo() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkInfoCallbacks
            callback.noCurrentNetworkInfo()
        }.whenever(wiseFyPublicApi).getCurrentNetworkInfo(any())
    }

    fun withWiseFyFailureGettingCurrentNetworkInfo() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetCurrentNetworkInfoCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).getCurrentNetworkInfo(any())
    }

    fun withSuccessGettingFrequency() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetFrequencyCallbacks
            callback.retrievedFrequency(MIN_FREQUENCY_5GHZ)
        }.whenever(wiseFyPublicApi).getFrequency(any<GetFrequencyCallbacks>())
    }

    fun withFailureGettingFrequency() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetFrequencyCallbacks
            callback.failureGettingFrequency()
        }.whenever(wiseFyPublicApi).getFrequency(any<GetFrequencyCallbacks>())
    }

    fun withWiseFyFailureGettingFrequency() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetFrequencyCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).getFrequency(any<GetFrequencyCallbacks>())
    }

    fun withSuccessGettingIP() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetIPCallbacks
            callback.retrievedIP(TEST_IP)
        }.whenever(wiseFyPublicApi).getIP(any())
    }

    fun withFailureGettingIP() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetIPCallbacks
            callback.failureRetrievingIP()
        }.whenever(wiseFyPublicApi).getIP(any())
    }

    fun withWiseFyFailureGettingIP() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetIPCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).getIP(any())
    }

    fun withSuccessGettingNearbyAccessPoints() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetNearbyAccessPointsCallbacks
            callback.retrievedNearbyAccessPoints(listOf(ACCESS_POINT))
        }.whenever(wiseFyPublicApi).getNearbyAccessPoints(eq(true), any())
    }

    fun withNoNearbyAccessPoints() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetNearbyAccessPointsCallbacks
            callback.noAccessPointsFound()
        }.whenever(wiseFyPublicApi).getNearbyAccessPoints(eq(true), any())
    }

    fun withWiseFyFailureGettingNearbyAccessPoints() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as GetNearbyAccessPointsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).getNearbyAccessPoints(eq(true), any())
    }

    fun withSuccessGettingSavedNetworks() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetSavedNetworksCallbacks
            callback.retrievedSavedNetworks(listOf(SAVED_NETWORK))
        }.whenever(wiseFyPublicApi).getSavedNetworks(any())
    }

    fun withNoSavedNetworks() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetSavedNetworksCallbacks
            callback.noSavedNetworksFound()
        }.whenever(wiseFyPublicApi).getSavedNetworks(any())
    }

    fun withWiseFyFailureGettingSavedNetworks() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[0] as GetSavedNetworksCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).getSavedNetworks(any())
    }

    fun withDeviceBelowLollipop() {
        `when`(sdkUtil.isAtLeastLollipop()).thenReturn(false)
    }

    fun withDeviceLollipopOrHigher() {
        `when`(sdkUtil.isAtLeastLollipop()).thenReturn(true)
    }

    /*
     * Actions
     */

    fun disableWifi() {
        onView(withId(R.id.disableWifiBtn)).performScrollToAndClick()
    }

    fun enableWifi() {
        onView(withId(R.id.enableWifiBtn)).performScrollToAndClick()
    }

    fun getCurrentNetwork() {
        onView(withId(R.id.getCurrentNetworkBtn)).performScrollToAndClick()
    }

    fun getCurrentNetworkInfo() {
        onView(withId(R.id.getCurrentNetworkInfoBtn)).performScrollToAndClick()
    }

    fun getFrequency() {
        onView(withId(R.id.getFrequencyBtn)).performScrollToAndClick()
    }

    fun getIP() {
        onView(withId(R.id.getIPBtn)).performScrollToAndClick()
    }

    fun getNearbyAccessPoints() {
        onView(withId(R.id.getNearbyAccessPointsBtn)).performScrollToAndClick()
    }

    fun getSavedNetworks() {
        onView(withId(R.id.getSavedNetworksBtn)).performScrollToAndClick()
    }

    fun launchMiscScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_misc)).performClick()
    }

    /*
     * Permission Helpers
     */

    fun permissionCallbackTriggered(requestCode: Int, permissionResult: Int) {
        val activity = activityTestRule.activity
        val fragment: MiscFragment? = activity
            .supportFragmentManager
            .findFragmentByTag(
                MiscFragment.TAG
            ) as? MiscFragment

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
        val fragment: MiscFragment? = activity
                .supportFragmentManager
                .findFragmentByTag(
                        MiscFragment.TAG
                ) as? MiscFragment

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

    fun verifyWifiDisabledIsDisplayed() {
        onView(withText(R.string.wifi_disabled)).checkIsDisplayed()
    }

    fun verifyFailureDisablingWifiIsDisplayed() {
        onView(withText(R.string.failure_disabling_wifi)).checkIsDisplayed()
    }

    fun verifyWifiEnabledIsDisplayed() {
        onView(withText(R.string.wifi_enabled)).checkIsDisplayed()
    }

    fun verifyFailureEnablingWifiIsDisplayed() {
        onView(withText(R.string.failure_enabling_wifi)).checkIsDisplayed()
    }

    fun verifyCurrentNetworkIsDisplayed() {
        onView(withText(containsString("Current network"))).checkIsDisplayed()
    }

    fun verifyNoCurrentNetworkIsDisplayed() {
        onView(withText(R.string.no_current_network)).checkIsDisplayed()
    }

    fun verifyCurrentNetworkInfoIsDisplayed() {
        onView(withText(containsString("Current network info"))).checkIsDisplayed()
    }

    fun verifyNoCurrentNetworkInfoIsDisplayed() {
        onView(withText(R.string.no_current_network_info)).checkIsDisplayed()
    }

    fun verifyFrequencyIsDisplayed() {
        onView(withText(containsString("$MIN_FREQUENCY_5GHZ"))).checkIsDisplayed()
    }

    fun verifyFailureRetrievingFrequencyIsDisplayed() {
        onView(withText(R.string.failure_retrieving_frequency)).checkIsDisplayed()
    }

    fun verifyIPIsDisplayed() {
        onView(withText(containsString(TEST_IP))).checkIsDisplayed()
    }

    fun verifyFailureRetrievingIPIsDisplayed() {
        onView(withText(R.string.failure_retrieving_ip)).checkIsDisplayed()
    }

    fun verifyTriedToDisableWifi(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).disableWifi(any())
    }

    fun verifyTriedToEnableWifi(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).enableWifi(any())
    }

    fun verifyTriedToGetCurrentNetwork(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).getCurrentNetwork(any())
    }

    fun verifyTriedToGetCurrentNetworkInfo(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).getCurrentNetworkInfo(any())
    }

    fun verifyTriedToGetIP(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).getIP(any())
    }

    fun verifyTriedToGetFrequency(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).getFrequency(any<GetFrequencyCallbacks>())
    }

    fun verifyTriedToGetNearbyAccessPoints(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).getNearbyAccessPoints(eq(true), any())
    }

    fun verifyTriedToGetSavedNetworks(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).getSavedNetworks(any())
    }

    fun verifyFrequencyLollipopNoticeIsDisplayed() {
        onView(withText(
            activityTestRule.getString(R.string.frequency_lollipop_notice)
        ))
        .checkIsDisplayed()
    }
}
