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
        searchRobot = SearchRobot(activityTestRule, wiseFy, permissionUtil)
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
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun searchForAccessPoint_failureToRemoveNetwork_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForAccessPoint(true)
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_networkNotFoundToRemove_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoAccessPointsFound(true)
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
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun searchForAccessPoint_failureToRemoveNetwork_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureSearchingForAccessPoint(false)
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
        }
    }

    @Test fun searchForAccessPoint_networkNotFoundToRemove_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoAccessPointsFound(false)
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

    @Test fun searchForAccessPoints_success_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints()
            launchSearchScreen()

            // When
            searchForAccessPoints(true)

            // Then
            verifySearchedForAccessPoints(true)
        }
    }

    @Test fun searchForAccessPoints_success_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoints()
            launchSearchScreen()

            // When
            searchForAccessPoints(false)

            // Then
            verifySearchedForAccessPoints(false)
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

    @Test fun searchForSSIDs_success() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchForSSIDs()
            launchSearchScreen()

            // When
            searchForSSIDs()

            // Then
            verifySearchedForSSIDs()
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
