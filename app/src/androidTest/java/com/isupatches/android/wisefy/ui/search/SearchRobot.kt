package com.isupatches.android.wisefy.ui.search

import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.android.wisefy.R
import com.isupatches.android.wisefy.TEST_SSID_1
import com.isupatches.android.wisefy.TEST_TIMEOUT
import com.isupatches.android.wisefy.internal.base.BaseRobot
import com.isupatches.android.wisefy.internal.espresso.checkIsChecked
import com.isupatches.android.wisefy.internal.espresso.checkIsDisplayed
import com.isupatches.android.wisefy.internal.espresso.checkIsInvisible
import com.isupatches.android.wisefy.internal.espresso.checkIsNotChecked
import com.isupatches.android.wisefy.internal.espresso.performClick
import com.isupatches.android.wisefy.internal.espresso.performScrollToAndCheckIsDisplayed
import com.isupatches.android.wisefy.internal.espresso.performScrollToAndClick
import com.isupatches.android.wisefy.internal.espresso.performScrollToAndReplaceText
import com.isupatches.android.wisefy.internal.espresso.setProgress
import com.isupatches.android.wisefy.sample.internal.entities.SearchType
import com.isupatches.android.wisefy.sample.ui.search.SearchStore
import com.isupatches.android.wisefy.sample.internal.util.PermissionUtil
import com.isupatches.android.wisefy.sample.ui.search.SearchFragment
import com.isupatches.android.wisefy.sample.ui.main.MainActivity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString

internal class SearchRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi,
    private val searchStore: SearchStore,
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
            eq(TEST_TIMEOUT * 1000),
            eq(filterDuplicates),
            any()
        )
    }

    fun withAccessPointNotFound(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[3] as SearchForAccessPointCallbacks
            callback.accessPointNotFound()
        }.whenever(wiseFyPublicApi).searchForAccessPoint(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            eq(filterDuplicates),
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

    fun withSuccessSearchingForAccessPoints(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.foundAccessPoints(listOf(ACCESS_POINT))
        }.whenever(wiseFyPublicApi).searchForAccessPoints(
            eq(TEST_SSID_1),
            eq(filterDuplicates),
            any()
        )
    }

    fun withNoAccessPointsFound(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.noAccessPointsFound()
        }.whenever(wiseFyPublicApi).searchForAccessPoints(eq(TEST_SSID_1), eq(filterDuplicates), any())
    }

    fun withWiseFyFailureSearchingForAccessPoints(filterDuplicates: Boolean) {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForAccessPointsCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForAccessPoints(eq(TEST_SSID_1), eq(filterDuplicates), any())
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

    fun withWiseFyFailureSearchingForSSID() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[2] as SearchForSSIDCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
        }.whenever(wiseFyPublicApi).searchForSSID(
            eq(TEST_SSID_1),
            eq(TEST_TIMEOUT * 1000),
            any()
        )
    }

    fun withSuccessSearchingForSSIDs() {
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

    fun withAccessPointInStore(filterDuplicates: Boolean) {
        searchStore.setLastUsedRegex(TEST_SSID_1)
        searchStore.setSearchType(SearchType.ACCESS_POINT)
        searchStore.setFilterDuplicates(filterDuplicates)
        searchStore.setReturnFullList(false)
        searchStore.setTimeout(TEST_TIMEOUT)
    }

    fun withAccessPointsInStore(filterDuplicates: Boolean) {
        searchStore.setLastUsedRegex(TEST_SSID_1)
        searchStore.setSearchType(SearchType.ACCESS_POINT)
        searchStore.setFilterDuplicates(filterDuplicates)
        searchStore.setReturnFullList(true)
        searchStore.setTimeout(TEST_TIMEOUT)
    }

    fun withSSIDInStore() {
        searchStore.setLastUsedRegex(TEST_SSID_1)
        searchStore.setSearchType(SearchType.SSID)
        searchStore.setReturnFullList(false)
        searchStore.setTimeout(TEST_TIMEOUT)
    }

    fun withSSIDsInStore() {
        searchStore.setLastUsedRegex(TEST_SSID_1)
        searchStore.setSearchType(SearchType.SSID)
        searchStore.setReturnFullList(true)
        searchStore.setTimeout(TEST_TIMEOUT)
    }

    fun withSavedNetworkInStore() {
        searchStore.setLastUsedRegex(TEST_SSID_1)
        searchStore.setSearchType(SearchType.SAVED_NETWORK)
        searchStore.setReturnFullList(false)
        searchStore.setTimeout(TEST_TIMEOUT)
    }

    fun withSavedNetworksInStore() {
        searchStore.setLastUsedRegex(TEST_SSID_1)
        searchStore.setSearchType(SearchType.SAVED_NETWORK)
        searchStore.setReturnFullList(true)
        searchStore.setTimeout(TEST_TIMEOUT)
    }

    /*
     * Actions
     */

    fun fillOutForAccessPoint(ssid: String? = null, filterDuplicates: Boolean? = null) {
        onView(withId(R.id.accessPointRdb)).performScrollToAndClick()
        setSearchRegex(ssid)
        onView(withId(R.id.noFullListRdb)).performScrollToAndClick()
        setFilterDuplicates(filterDuplicates)
        setTimeoutSeek()
    }

    fun fillOutForAccessPoints(ssid: String? = null, filterDuplicates: Boolean? = null) {
        onView(withId(R.id.accessPointRdb)).performScrollToAndClick()
        setSearchRegex(ssid)
        onView(withId(R.id.yesFullListRdb)).performScrollToAndClick()
        setFilterDuplicates(filterDuplicates)
    }

    fun fillOutForSavedNetwork(ssid: String? = null) {
        onView(withId(R.id.savedNetworkRdb)).performScrollToAndClick()
        setSearchRegex(ssid)
        onView(withId(R.id.noFullListRdb)).performScrollToAndClick()
    }

    fun fillOutForSavedNetworks(ssid: String? = null) {
        onView(withId(R.id.savedNetworkRdb)).performScrollToAndClick()
        setSearchRegex(ssid)
        onView(withId(R.id.yesFullListRdb)).performScrollToAndClick()
    }

    fun fillOutForSSID(ssid: String? = null) {
        onView(withId(R.id.ssidRdb)).performScrollToAndClick()
        setSearchRegex(ssid)
        onView(withId(R.id.noFullListRdb)).performScrollToAndClick()
        setTimeoutSeek()
    }

    fun fillOutForSSIDs(ssid: String? = null) {
        onView(withId(R.id.ssidRdb)).performScrollToAndClick()
        setSearchRegex(ssid)
        onView(withId(R.id.yesFullListRdb)).performScrollToAndClick()
    }

    fun launchSearchScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_search)).performClick()
    }

    fun setFilterDupesToNo() {
        onView(withId(R.id.noFilterDupesRdb)).performScrollToAndClick()
    }

    fun setFilterDupesToYes() {
        onView(withId(R.id.yesFilterDupesRdb)).performScrollToAndClick()
    }

    fun searchForAccessPoint(ssid: String, filterDuplicates: Boolean) {
        fillOutForAccessPoint(ssid, filterDuplicates)
        clickSearchButton()
    }

    fun searchForAccessPoints(ssid: String, filterDuplicates: Boolean) {
        fillOutForAccessPoints(ssid, filterDuplicates)
        clickSearchButton()
    }

    fun searchForSavedNetwork(ssid: String) {
        fillOutForSavedNetwork(ssid)
        clickSearchButton()
    }

    fun searchForSavedNetworks(ssid: String) {
        fillOutForSavedNetworks(ssid)
        clickSearchButton()
    }

    fun searchForSSID(ssid: String) {
        fillOutForSSID(ssid)
        clickSearchButton()
    }

    fun searchForSSIDs(ssid: String) {
        fillOutForSSIDs(ssid)
        clickSearchButton()
    }

    /*
     * View Helpers
     */

    private fun clickSearchButton() {
        onView(withId(R.id.searchBtn)).performScrollToAndClick()
    }

    private fun setFilterDuplicates(filterDuplicates: Boolean?) {
        filterDuplicates?.let {
            if (it) {
                setFilterDupesToYes()
            } else {
                setFilterDupesToNo()
            }
        }
    }

    private fun setSearchRegex(ssid: String?) {
        ssid?.let {
            onView(withId(R.id.searchRegexEdt)).performScrollToAndReplaceText(ssid)
        }
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

    fun verifyAccessPointIsDisplayed() {
        onView(withText(containsString("Access point"))).checkIsDisplayed()
    }

    fun verifyAccessPointNotFoundIsDisplayed() {
        onView(withText(R.string.access_point_not_found)).checkIsDisplayed()
    }

    fun verifySavedNetworkIsDisplayed() {
        onView(withText(containsString("Saved network"))).checkIsDisplayed()
    }

    fun verifySavedNetworkNotFoundIsDisplayed() {
        onView(withText(R.string.saved_network_not_found)).checkIsDisplayed()
    }

    fun verifySSIDIsDisplayed() {
        onView(withText(containsString("SSID"))).checkIsDisplayed()
    }

    fun verifySSIDNotFoundIsDisplayed() {
        onView(withText(R.string.ssid_not_found)).checkIsDisplayed()
    }

    fun verifySSIDsAreDisplayed() {
        onView(withText(containsString("SSIDs"))).checkIsDisplayed()
    }

    fun verifyNoSSIDsFoundIsDisplayed() {
        onView(withText(R.string.no_ssids_found)).checkIsDisplayed()
    }

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

    fun verifySearchRegexIsPopulated() {
        onView(allOf(withId(R.id.searchRegexEdt), withText(TEST_SSID_1))).performScrollToAndCheckIsDisplayed()
    }

    fun verifyAccessPointIsChecked() {
        onView(withId(R.id.accessPointRdb)).checkIsChecked()
    }

    fun verifySSIDsChecked() {
        onView(withId(R.id.ssidRdb)).checkIsChecked()
    }

    fun verifySavedNetworkIsChecked() {
        onView(withId(R.id.savedNetworkRdb)).checkIsChecked()
    }

    fun verifyFilterDupesSetToYes() {
        onView(withId(R.id.yesFilterDupesRdb)).checkIsChecked()
        onView((withId(R.id.noFilterDupesRdb))).checkIsNotChecked()
    }

    fun verifyFilterDupesSetToNo() {
        onView(withId(R.id.noFilterDupesRdb)).checkIsChecked()
        onView((withId(R.id.yesFilterDupesRdb))).checkIsNotChecked()
    }

    fun verifyReturnFullListIsSetToYes() {
        onView(withId(R.id.yesFullListRdb)).checkIsChecked()
        onView((withId(R.id.noFullListRdb))).checkIsNotChecked()
    }

    fun verifyReturnFullListIsSetToNo() {
        onView(withId(R.id.noFullListRdb)).checkIsChecked()
        onView((withId(R.id.yesFullListRdb))).checkIsNotChecked()
    }

    fun verifyTimeoutIsDisplayed() {
        onView(withId(R.id.timeoutSeek)).performScrollToAndCheckIsDisplayed()
    }

    fun verifyTimeoutIsNotDisplayed() {
        onView(withId(R.id.timeoutSeek)).checkIsInvisible()
    }

    fun verifyFilterDuplicatesIsDisplayed() {
        onView(withId(R.id.filterDupesRdg)).performScrollToAndCheckIsDisplayed()
    }

    fun verifyFilterDuplicatesIsNotDisplayed() {
        onView(withId(R.id.filterDupesRdg)).checkIsInvisible()
    }
}
