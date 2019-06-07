package com.isupatches.wisefysample.ui.search

import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.TEST_TIMEOUT
import com.isupatches.wisefysample.internal.base.BaseRobot
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndReplaceText
import com.isupatches.wisefysample.internal.espresso.setProgress
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.ui.main.MainActivity

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

internal class SearchRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi,
    permissionUtil: PermissionUtil
) : BaseRobot(activityTestRule, permissionUtil) {

    companion object {
        private val SAVED_NETWORK = mock<WifiConfiguration>()
        private val ACCESS_POINT = mock<ScanResult>()
    }

    /*
     * Preconditions
     */

    fun withSuccessSearchingForAccessPoint(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointFound(ACCESS_POINT)
        }.whenever(wiseFyPublicApi).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT),
            eq(filterDuplicates),
            any()
        )
    }

    fun withAccessPointNotFound() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointNotFound()
        }.whenever(wiseFyPublicApi).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            eq(true),
            any()
        )
    }

    fun withWiseFyFailureSearchingForAccessPoint(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            eq(filterDuplicates),
            any()
        )
    }

    fun withSuccessSearchingForAccessPoints() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.foundAccessPoints(listOf(ACCESS_POINT))
        }.whenever(wiseFyPublicApi).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())
    }

    fun withNoAccessPointsFound(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.noAccessPointsFound()
        }.whenever(wiseFyPublicApi).searchForAccessPoints(eq(TEST_SSID_1), eq(filterDuplicates), any())
    }

    fun withWiseFyFailureSearchingForAccessPoints() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForAccessPoints(eq(TEST_SSID_1), eq(true), any())
    }

    fun withSuccessSearchingForSavedNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworkCallbacks
            callback.retrievedSavedNetwork(SAVED_NETWORK)
        }.whenever(wiseFyPublicApi).searchForSavedNetwork(eq(TEST_SSID_1), any())
    }

    fun withSavedNetworkNotFound() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworkCallbacks
            callback.savedNetworkNotFound()
        }.whenever(wiseFyPublicApi).searchForSavedNetwork(eq(TEST_SSID_1), any())
    }

    fun withWiseFyFailureSearchingForSavedNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForSavedNetwork(eq(TEST_SSID_1), any())
    }

    fun withSuccessSearchingForSavedNetworks() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworksCallbacks
            callback.retrievedSavedNetworks(listOf(SAVED_NETWORK))
        }.whenever(wiseFyPublicApi).searchForSavedNetworks(eq(TEST_SSID_1), any())
    }

    fun withNotSavedNetworksFound() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworksCallbacks
            callback.noSavedNetworksFound()
        }.whenever(wiseFyPublicApi).searchForSavedNetworks(eq(TEST_SSID_1), any())
    }

    fun withWiseFyFailureSearchingForSavedNetworks() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSavedNetworksCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForSavedNetworks(eq(TEST_SSID_1), any())
    }

    fun withSuccessSearchingForSSID() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.ssidFound(TEST_SSID_1)
        }.whenever(wiseFyPublicApi).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            any()
        )
    }

    fun withSSIDNotFound() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.ssidNotFound()
        }.whenever(wiseFyPublicApi).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            any()
        )
    }

    fun withWiseFyFailureSearchForSSID() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            any()
        )
    }

    fun withSuccessSearchForSSIDs() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.retrievedSSIDs(listOf(TEST_SSID_1))
        }.whenever(wiseFyPublicApi).searchForSSIDs(eq(TEST_SSID_1), any())
    }

    fun withNoSSIDsFound() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.noSSIDsFound()
        }.whenever(wiseFyPublicApi).searchForSSIDs(eq(TEST_SSID_1), any())
    }

    fun withWiseFyFailureSearchingForSSIDs() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as SearchForSSIDsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForSSIDs(eq(TEST_SSID_1), any())
    }

    /*
     * Actions
     */

    fun launchSearchScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_search)).performClick()
    }

    fun searchForAccessPoint(filterDuplicates: Boolean) {
        fillOutFormForAccessPoint()
        onView(withId(R.id.noFullListRdb)).performScrollToAndClick()
        if (filterDuplicates) {
            onView(withId(R.id.yesFilterDupesRdb)).performScrollToAndClick()
        } else {
            onView(withId(R.id.noFilterDupesRdb)).performScrollToAndClick()
        }
        setTimeoutSeek()
        clickSearchButton()
    }

    fun searchForAccessPoints(filterDuplicates: Boolean) {
        fillOutFormForAccessPoint()
        onView(withId(R.id.yesFullListRdb)).performScrollToAndClick()
        if (filterDuplicates) {
            onView(withId(R.id.yesFilterDupesRdb)).performScrollToAndClick()
        } else {
            onView(withId(R.id.noFilterDupesRdb)).performScrollToAndClick()
        }
        clickSearchButton()
    }

    fun searchForSavedNetwork() {
        fillOutFormForForSavedNetwork()
        onView(withId(R.id.noFullListRdb)).performScrollToAndClick()
        clickSearchButton()
    }

    fun searchForSavedNetworks() {
        fillOutFormForForSavedNetwork()
        onView(withId(R.id.yesFullListRdb)).performScrollToAndClick()
        clickSearchButton()
    }

    fun searchForSSID() {
        fillOutFormFormForSSID()
        onView(withId(R.id.noFullListRdb)).performScrollToAndClick()
        setTimeoutSeek()
        clickSearchButton()
    }

    fun searchForSSIDs() {
        fillOutFormFormForSSID()
        onView(withId(R.id.yesFullListRdb)).performScrollToAndClick()
        clickSearchButton()
    }

    /*
     * View Helpers
     */

    private fun clickSearchButton() {
        onView(withId(R.id.searchBtn)).performScrollToAndClick()
    }

    private fun fillOutFormForAccessPoint() {
        onView(withId(R.id.accessPointRdb)).performClick()
        setSearchRegex()
    }

    private fun fillOutFormForForSavedNetwork() {
        onView(withId(R.id.savedNetworkRdb)).performClick()
        setSearchRegex()
    }

    private fun fillOutFormFormForSSID() {
        onView(withId(R.id.ssidRdb)).performClick()
        setSearchRegex()
    }

    private fun setSearchRegex() {
        onView(withId(R.id.searchRegexEdt)).performScrollToAndReplaceText(TEST_SSID_1)
    }

    private fun setTimeoutSeek() {
        onView(withId(R.id.timeoutSeek)).perform(scrollTo()).perform(setProgress(TEST_TIMEOUT))
    }

    /*
    * Permission Helpers
    */

    fun permissionCallbackTriggered(requestCode: Int, permissionResult: Int) {
        val activity = activityTestRule.activity
        val fragment: SearchFragment? = activity
            .supportFragmentManager
            .findFragmentByTag(
                SearchFragment.TAG
            ) as? SearchFragment

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
        val fragment: SearchFragment? = activity
            .supportFragmentManager
            .findFragmentByTag(
                SearchFragment.TAG
            ) as? SearchFragment

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

    fun verifySearchedForAccessPoint(filterDuplicates: Boolean, times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            eq(filterDuplicates),
            any()
        )
    }

    fun verifySearchedForAccessPoints(filterDuplicates: Boolean, times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).searchForAccessPoints(
            eq(TEST_SSID_1),
            eq(filterDuplicates),
            any()
        )
    }

    fun verifySearchedForSavedNetwork(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).searchForSavedNetwork(
            eq(TEST_SSID_1),
            any()
        )
    }

    fun verifySearchedForSavedNetworks(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).searchForSavedNetworks(
            eq(TEST_SSID_1),
            any()
        )
    }

    fun verifySearchedForSSID(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            any()
        )
    }

    fun verifySearchedForSSIDs(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).searchForSSIDs(
            eq(TEST_SSID_1),
            any()
        )
    }
}
