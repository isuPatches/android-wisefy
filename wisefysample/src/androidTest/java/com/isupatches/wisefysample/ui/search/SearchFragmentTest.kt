package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.test.espresso.intent.rule.IntentsTestRule

import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SearchFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    private lateinit var searchRobot: SearchRobot

    @Before override fun setUp() {
        super.setUp()
        searchRobot = SearchRobot(activityTestRule, wiseFy, permissionUtil)
    }

    @Test fun searchForAccessPoint_success_filterDuplicates_true() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint()
            launchSearchScreen()

            // When
            searchForAccessPoint(true)

            // Then
            verifySearchedForAccessPoint(true)
        }
    }

    @Test fun searchForAccessPoint_success_filterDuplicates_false() {
        with(searchRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessSearchingForAccessPoint()
            launchSearchScreen()

            // When
            searchForAccessPoint(false)

            // Then
            verifySearchedForAccessPoint(false)
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
