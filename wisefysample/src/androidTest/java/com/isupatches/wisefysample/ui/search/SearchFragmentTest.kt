package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule

import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SearchFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_COARSE_LOCATION)

    private lateinit var searchRobot: SearchRobot

    @Before override fun setUp() {
        super.setUp()
        searchRobot = SearchRobot(activityTestRule, wiseFy, searchStore, permissionUtil)
    }

    @Test fun adaptiveUI() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withAccessPointInStore(false)
            launchSearchScreen()

            // When
            selectAccessPoint()

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
            selectAccessPoints()

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
            selectSSID()

            // Then - SSID
            verifySSIDsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsDisplayed()

            // And When
            selectSSIDs()

            // Then - SSIDS
            verifySSIDsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()

            // And When
            selectSavedNetwork()

            // Then - Saved Network
            verifySavedNetworkIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()

            // And When
            selectSavedNetworks()

            // Then - Saved Networks
            verifySavedNetworkIsChecked()
            verifyReturnFullListIsSetToYes()
            verifyFilterDuplicatesIsNotDisplayed()
            verifyTimeoutIsNotDisplayed()

            // And When
            selectAccessPoint()

            // Then Finally - Access Point
            verifyAccessPointIsChecked()
            verifyReturnFullListIsSetToNo()
            verifyFilterDuplicatesIsDisplayed()
            verifyTimeoutIsDisplayed()
        }
    }

    @Test fun loadsAccessPointFromStore_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsAccessPointFromStore_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsAccessPointsFromStore_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsAccessPointsFromStore_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsSSIDFromStore() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsSSIDSFromStore() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsSavedNetworkFromStore() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun loadsSavedNetworksFromStore() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun searchForAccessPoint_permissionErrorDialog_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchSearchScreen()
            searchForAccessPoint(true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoint_permissionError_once_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_permissionError_once_accessWifiState_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_permissionError_once_accessCourseLocation_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_permissionError_emptyList_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(true)
            launchSearchScreen()
            searchForAccessPoint(true)

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_permissionError_permissionDenied_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(true)
            launchSearchScreen()
            searchForAccessPoint(true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_success_afterPermissionGranted_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(true)
            launchSearchScreen()
            searchForAccessPoint(true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoint(true, times = 2)
        }
    }

    @Test fun searchForAccessPoint_success_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_accessPointNotFound_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withAccessPointNotFound(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_wiseFyFailure_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForAccessPoint(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoint(true)

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoint_permissionErrorDialog_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchSearchScreen()
            searchForAccessPoint(false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoint_permissionError_once_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_permissionError_once_accessWifiState_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_permissionError_once_accessCourseLocation_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_permissionError_emptyList_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(false)
            launchSearchScreen()
            searchForAccessPoint(false)

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_permissionError_permissionDenied_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(false)
            launchSearchScreen()
            searchForAccessPoint(false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_success_afterPermissionGranted_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(false)
            launchSearchScreen()
            searchForAccessPoint(false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoint(false, times = 2)
        }
    }

    @Test fun searchForAccessPoint_success_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_accessPointNotFound_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withAccessPointNotFound(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_wiseFyFailure_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForAccessPoint(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoint(false)

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoints_permissionErrorDialog_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchSearchScreen()
            searchForAccessPoints(true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoints_permissionError_once_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_permissionError_once_accessWifiState_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_permissionError_once_accessCourseLocation_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_permissionError_emptyList_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(true)
            launchSearchScreen()
            searchForAccessPoints(true)

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_permissionError_permissionDenied_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(true)
            launchSearchScreen()
            searchForAccessPoints(true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_success_afterPermissionGranted_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(true)
            launchSearchScreen()
            searchForAccessPoints(true)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoints(true, times = 2)
        }
    }

    @Test fun searchForAccessPoints_success_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(true)
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_noAccessPointsFound_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoAccessPointsFound(true)
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_wiseFyFailure_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForAccessPoints(true)
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoints(true)

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoints_permissionErrorDialog_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchSearchScreen()
            searchForAccessPoints(false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForAccessPoints_permissionError_once_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_permissionError_once_accessWifiState_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_permissionError_once_accessCourseLocation_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_permissionError_emptyList_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(false)
            launchSearchScreen()
            searchForAccessPoints(false)

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_permissionError_permissionDenied_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(false)
            launchSearchScreen()
            searchForAccessPoints(false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_success_afterPermissionGranted_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(false)
            launchSearchScreen()
            searchForAccessPoints(false)

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForAccessPoints(false, times = 2)
        }
    }

    @Test fun searchForAccessPoints_success_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints(false)
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_noAccessPointsFound_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoAccessPointsFound(false)
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifySearchedForAccessPoints(false)
        }
    }

    @Test fun searchForAccessPoints_wiseFyFailure_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForAccessPoints(false)
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForAccessPoints(false)

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForSSID_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchSearchScreen()
            searchForSSID()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForSSID_permissionError_once() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSID()

            // Then
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_permissionError_once_accessWifiState() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSID()

            // Then
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_permissionError_once_accessCoarseLocation() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSID()

            // Then
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSID()
            launchSearchScreen()
            searchForSSID()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSID()
            launchSearchScreen()
            searchForSSID()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSID()
            launchSearchScreen()
            searchForSSID()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSID_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSSID(times = 2)
        }
    }

    @Test fun searchForSSID_success() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSID()
            launchSearchScreen()

            // When
            searchForSSID()

            // Then
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_SSIDNotFound() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSSIDNotFound()
            launchSearchScreen()

            // When
            searchForSSID()

            // Then
            verifySearchedForSSID()
        }
    }

    @Test fun searchForSSID_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForSSID()
            launchSearchScreen()

            // When
            searchForSSID()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSSID()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForSSIDs_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchSearchScreen()
            searchForSSIDs()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForSSIDs_permissionError_once() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_permissionError_once_accessWifiState() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_permissionError_once_accessCoarseLocation() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSIDs()
            launchSearchScreen()
            searchForSSIDs()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSIDs()
            launchSearchScreen()
            searchForSSIDs()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSIDs()
            launchSearchScreen()
            searchForSSIDs()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSSIDs(times = 2)
        }
    }

    @Test fun searchForSSIDs_success() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForSSIDs()
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_noSSIDsFound() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoSSIDsFound()
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifySearchedForSSIDs()
        }
    }

    @Test fun searchForSSIDs_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForSSIDs()
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSSIDs()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForSavedNetwork_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            launchSearchScreen()
            searchForSavedNetwork()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForSavedNetwork_permissionError_once() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            launchSearchScreen()

            // When
            searchForSavedNetwork()

            // Then
            verifySearchedForSavedNetwork()
        }
    }

    @Test fun searchForSavedNetwork_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetwork()
            launchSearchScreen()
            searchForSavedNetwork()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSavedNetwork()
        }
    }

    @Test fun searchForSavedNetwork_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetwork()
            launchSearchScreen()
            searchForSavedNetwork()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSavedNetwork()
        }
    }

    @Test fun searchForSavedNetwork_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetwork()
            launchSearchScreen()
            searchForSavedNetwork()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSavedNetwork(times = 2)
        }
    }

    @Test fun searchForSavedNetwork_success() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetwork()
            launchSearchScreen()

            // When
            searchForSavedNetwork()

            // Then
            verifySearchedForSavedNetwork()
        }
    }

    @Test fun searchForSavedNetwork_savedNetworkNotFound() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE)
            withSavedNetworkNotFound()
            launchSearchScreen()

            // When
            searchForSavedNetwork()

            // Then
            verifySearchedForSavedNetwork()
        }
    }

    @Test fun searchForSavedNetwork_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE)
            withWiseFyFailureSearchingForSavedNetwork()
            launchSearchScreen()

            // When
            searchForSavedNetwork()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSavedNetwork()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun searchForSavedNetworks_permissionErrorDialog() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            launchSearchScreen()
            searchForSavedNetworks()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun searchForSavedNetworks_permissionError_once() {
        with(searchRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            launchSearchScreen()

            // When
            searchForSavedNetworks()

            // Then
            verifySearchedForSavedNetworks()
        }
    }

    @Test fun searchForSavedNetworks_permissionError_emptyList() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetworks()
            launchSearchScreen()
            searchForSavedNetworks()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSavedNetworks()
        }
    }

    @Test fun searchForSavedNetworks_permissionError_permissionDenied() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetworks()
            launchSearchScreen()
            searchForSavedNetworks()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifySearchedForSavedNetworks()
        }
    }

    @Test fun searchForSavedNetworks_success_afterPermissionGranted() {
        with(searchRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetworks()
            launchSearchScreen()
            searchForSavedNetworks()

            // When
            permissionCallbackTriggered(
                SearchFragment.WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifySearchedForSavedNetworks(times = 2)
        }
    }

    @Test fun searchForSavedNetworks_success() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE)
            withSuccessSearchingForSavedNetworks()
            launchSearchScreen()

            // When
            searchForSavedNetworks()

            // Then
            verifySearchedForSavedNetworks()
        }
    }

    @Test fun searchForSavedNetworks_noSavedNetworksFound() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE)
            withNotSavedNetworksFound()
            launchSearchScreen()

            // When
            searchForSavedNetworks()

            // Then
            verifySearchedForSavedNetworks()
        }
    }

    @Test fun searchForSavedNetworks_wiseFyFailure() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE)
            withWiseFyFailureSearchingForSavedNetworks()
            launchSearchScreen()

            // When
            searchForSavedNetworks()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifySearchedForSavedNetworks()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun ridiculousPermissionRequested() {
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
