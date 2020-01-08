package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule
import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SearchFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_FINE_LOCATION)

    private lateinit var searchRobot: SearchRobot

    @Before
    override fun setUp() {
        super.setUp()
        searchRobot = SearchRobot(activityTestRule, wiseFy, searchStore, permissionUtil)
    }

    @Test
    fun adaptiveUI() {
        with(searchRobot) {
            // Given
            withAccessPointInStore(false)
            launchSearchScreen()

            // When
            fillOutForAccessPoint()

            // Then - Access Point
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToNo()

            verifyFilterDuplicatesIsDisplayed()
            setFilterDupesToNo()
            verifyFilterDupesSetToNo()
            setFilterDupesToYes()
            verifyFilterDupesSetToYes()

            verifyTimeoutIsDisplayed()

            // And When
            fillOutForAccessPoints()

            // Then - Access Points
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToYes()

            verifyFilterDuplicatesIsDisplayed()
            setFilterDupesToNo()
            verifyFilterDupesSetToNo()
            setFilterDupesToYes()
            verifyFilterDupesSetToYes()

            verifyTimeoutIsNotDisplayed()

            // And When
            fillOutForSSID()

            // Then - SSID
            verifySSIDsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsDisplayed()

            // And When
            fillOutForSSIDs()

            // Then - SSIDS
            verifySSIDsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()

            // And When
            fillOutForSavedNetwork()

            // Then - Saved Network
            verifySavedNetworkIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()

            // And When
            fillOutForSavedNetworks()

            // Then - Saved Networks
            verifySavedNetworkIsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()

            // And When
            fillOutForAccessPoint()

            // Then Finally - Access Point
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsDisplayed()
            verifyTimeoutIsDisplayed()
        }
    }

    @Test
    fun loadsAccessPointFromStore_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withAccessPointInStore(false)

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDupesSetToNo()
            verifyTimeoutIsDisplayed()
        }
    }

    @Test
    fun loadsAccessPointFromStore_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withAccessPointInStore(true)

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDupesSetToYes()
            verifyTimeoutIsDisplayed()
        }
    }

    @Test
    fun loadsAccessPointsFromStore_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withAccessPointsInStore(false)

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDupesSetToNo()
            verifyTimeoutIsNotDisplayed()
        }
    }

    @Test
    fun loadsAccessPointsFromStore_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withAccessPointsInStore(true)

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDupesSetToYes()
            verifyTimeoutIsNotDisplayed()
        }
    }

    @Test
    fun loadsSSIDFromStore() {
        with(searchRobot) {
            // Given
            withSSIDInStore()

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifySSIDsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsDisplayed()
        }
    }

    @Test
    fun loadsSSIDSFromStore() {
        with(searchRobot) {
            // Given
            withSSIDsInStore()

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifySSIDsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()
        }
    }

    @Test
    fun loadsSavedNetworkFromStore() {
        with(searchRobot) {
            // Given
            withSavedNetworkInStore()

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifySavedNetworkIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()
        }
    }

    @Test
    fun loadsSavedNetworksFromStore() {
        with(searchRobot) {
            // Given
            withSavedNetworksInStore()

            // When
            launchSearchScreen()

            // Then
            verifySearchRegexIsPopulated()
            verifySavedNetworkIsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()
        }
    }

    @Test
    fun searchForAccessPoint_permissionErrorDialog_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionError_once_accessFineLocation_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForAccessPoint(true)
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, true)

            // Then
            verifySearchedForAccessPoint(true)
            verifyAccessPointIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionError_emptyList_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionError_permissionDenied_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_success_afterPermissionGranted_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoint(true)
            launchSearchScreen()
            fillOutForAccessPoint(TEST_SSID_1, true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoint(true)
            verifyAccessPointIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_success_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoint(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, true)

            // Then
            verifySearchedForAccessPoint(true)
            verifyAccessPointIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_accessPointNotFound_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withAccessPointNotFound(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, true)

            // Then
            verifySearchedForAccessPoint(true)
            verifyAccessPointNotFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_wiseFyFailure_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForAccessPoint(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, true)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoint(true)

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionErrorDialog_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionError_once_accessFineLocation_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForAccessPoint(false)
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, false)

            // Then
            verifySearchedForAccessPoint(false)
            verifyAccessPointIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionError_emptyList_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_permissionError_permissionDenied_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoint_success_afterPermissionGranted_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoint(false)
            launchSearchScreen()
            fillOutForAccessPoint(TEST_SSID_1, false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoint(false)
            verifyAccessPointIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_success_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoint(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, false)

            // Then
            verifySearchedForAccessPoint(false)
            verifyAccessPointIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_accessPointNotFound_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withAccessPointNotFound(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, false)

            // Then
            verifySearchedForAccessPoint(false)
            verifyAccessPointNotFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoint_wiseFyFailure_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForAccessPoint(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(TEST_SSID_1, false)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoint(false)

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionErrorDialog_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionError_once_accessFineLocation_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForAccessPoints(true)
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, true)

            // Then
            verifySearchedForAccessPoints(true)
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionError_emptyList_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionError_permissionDenied_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_success_afterPermissionGranted_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoints(true)
            launchSearchScreen()
            fillOutForAccessPoints(TEST_SSID_1, true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoints(true)
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_success_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoints(true)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, true)

            // Then
            verifySearchedForAccessPoints(true)
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_noAccessPointsFound_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNoAccessPointsFound(true)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, true)

            // Then
            verifySearchedForAccessPoints(true)
            verifyNoAccessPointsFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_wiseFyFailure_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForAccessPoints(true)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, true)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoints(true)

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionErrorDialog_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionError_once_accessFineLocation_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForAccessPoints(false)
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, false)

            // Then
            verifySearchedForAccessPoints(false)
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionError_emptyList_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_permissionError_permissionDenied_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForAccessPoints_success_afterPermissionGranted_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoints(false)
            launchSearchScreen()
            fillOutForAccessPoints(TEST_SSID_1, false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoints(false)
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_success_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForAccessPoints(false)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, false)

            // Then
            verifySearchedForAccessPoints(false)
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_noAccessPointsFound_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNoAccessPointsFound(false)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, false)

            // Then
            verifySearchedForAccessPoints(false)
            verifyNoAccessPointsFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForAccessPoints_wiseFyFailure_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForAccessPoints(false)
            launchSearchScreen()

            // When
            searchForAccessPoints(TEST_SSID_1, false)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoints(false)

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSID_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSID_permissionError_once_accessFineLocation() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForSSID()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSID(TEST_SSID_1)

            // Then
            verifySearchedForSSID()
            verifySSIDIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSID_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSID_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSID_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSSID()
            launchSearchScreen()
            fillOutForSSID(TEST_SSID_1)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSSID()
            verifySSIDIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSID_success() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSSID()
            launchSearchScreen()

            // When
            searchForSSID(TEST_SSID_1)

            // Then
            verifySearchedForSSID()
            verifySSIDIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSID_SSIDNotFound() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSSIDNotFound()
            launchSearchScreen()

            // When
            searchForSSID(TEST_SSID_1)

            // Then
            verifySearchedForSSID()
            verifySSIDNotFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSID_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForSSID()
            launchSearchScreen()

            // When
            searchForSSID(TEST_SSID_1)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSSID()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSIDs_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSIDs_permissionError_once_accessFineLocation() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForSSIDs()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSIDs(TEST_SSID_1)

            // Then
            verifySearchedForSSIDs()
            verifySSIDsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSIDs_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSIDs_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSSIDs_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSSIDs()
            launchSearchScreen()
            fillOutForSSIDs(TEST_SSID_1)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSSIDs()
            verifySSIDsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSIDs_success() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSSIDs()
            launchSearchScreen()

            // When
            searchForSSIDs(TEST_SSID_1)

            // Then
            verifySearchedForSSIDs()
            verifySSIDsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSIDs_noSSIDsFound() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNoSSIDsFound()
            launchSearchScreen()

            // When
            searchForSSIDs(TEST_SSID_1)

            // Then
            verifySearchedForSSIDs()
            verifyNoSSIDsFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSSIDs_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForSSIDs()
            launchSearchScreen()

            // When
            searchForSSIDs(TEST_SSID_1)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSSIDs()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_permissionError_once() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForSavedNetwork()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForSavedNetwork(TEST_SSID_1)

            // Then
            verifySearchedForSavedNetwork()
            verifySavedNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSavedNetwork()
            launchSearchScreen()
            fillOutForSavedNetwork(TEST_SSID_1)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSavedNetwork()
            verifySavedNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_success() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSavedNetwork()
            launchSearchScreen()

            // When
            searchForSavedNetwork(TEST_SSID_1)

            // Then
            verifySearchedForSavedNetwork()
            verifySavedNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_savedNetworkNotFound() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSavedNetworkNotFound()
            launchSearchScreen()

            // When
            searchForSavedNetwork(TEST_SSID_1)

            // Then
            verifySearchedForSavedNetwork()
            verifySavedNetworkNotFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetwork_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForSavedNetwork()
            launchSearchScreen()

            // When
            searchForSavedNetwork(TEST_SSID_1)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSavedNetwork()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_permissionError_once() {
        with(searchRobot) {
            // Given
            withSuccessSearchingForSavedNetworks()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchSearchScreen()

            // When
            searchForSavedNetworks(TEST_SSID_1)

            // Then
            verifySearchedForSavedNetworks()
            verifySavedNetworksAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSavedNetworks()
            launchSearchScreen()
            fillOutForSavedNetworks(TEST_SSID_1)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSavedNetworks()
            verifySavedNetworksAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_success() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessSearchingForSavedNetworks()
            launchSearchScreen()

            // When
            searchForSavedNetworks(TEST_SSID_1)

            // Then
            verifySearchedForSavedNetworks()
            verifySavedNetworksAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_noSavedNetworksFound() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNotSavedNetworksFound()
            launchSearchScreen()

            // When
            searchForSavedNetworks(TEST_SSID_1)

            // Then
            verifySearchedForSavedNetworks()
            verifyNoSavedNetworksFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun searchForSavedNetworks_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureSearchingForSavedNetworks()
            launchSearchScreen()

            // When
            searchForSavedNetworks(TEST_SSID_1)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSavedNetworks()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun ridiculousPermissionRequested() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(RANDO_PERMISSION_REQUEST_CODE)

            // Then
            verifyPermissionErrorShown()
            dismissErrorDialog()
        }
    }
}
