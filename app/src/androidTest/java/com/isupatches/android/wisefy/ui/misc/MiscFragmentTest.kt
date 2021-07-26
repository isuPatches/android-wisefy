package com.isupatches.android.wisefy.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule
import com.isupatches.android.wisefy.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.android.wisefy.internal.base.AbstractEspressoTestClass
import com.isupatches.android.wisefy.sample.ui.main.MainActivity
import com.isupatches.android.wisefy.sample.ui.misc.MiscFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MiscFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_FINE_LOCATION)

    private lateinit var miscRobot: MiscRobot

    @Before
    override fun setUp() {
        super.setUp()
        miscRobot = MiscRobot(activityTestRule, wiseFy, sdkUtil, permissionUtil)
    }

    @Test
    fun disableWifi_success() {
        with(miscRobot) {
            // Given
            withSuccessDisablingWifi()
            launchMiscScreen()

            // When
            disableWifi()

            // Then
            verifyTriedToDisableWifi()
            verifyWifiDisabledIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun disableWifi_failureDisablingWifi() {
        with(miscRobot) {
            // Given
            withFailureDisablingWifi()
            launchMiscScreen()

            // When
            disableWifi()

            // Then
            verifyTriedToDisableWifi()
            verifyFailureDisablingWifiIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun disableWifi_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withWiseFyFailureDisablingWifi()
            launchMiscScreen()

            // When
            disableWifi()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToDisableWifi()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun enableWifi_success() {
        with(miscRobot) {
            // Given
            withSuccessEnablingWifi()
            launchMiscScreen()

            // When
            enableWifi()

            // Then
            verifyTriedToEnableWifi()
            verifyWifiEnabledIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun enableWifi_failureEnablingWifi() {
        with(miscRobot) {
            // Given
            withFailureEnablingWifi()
            launchMiscScreen()

            // When
            enableWifi()

            // Then
            verifyTriedToEnableWifi()
            verifyFailureEnablingWifiIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun enableWifi_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withWiseFyFailureEnablingWifi()
            launchMiscScreen()

            // When
            enableWifi()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToEnableWifi()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun getCurrentNetwork_success() {
        with(miscRobot) {
            // Given
            withSuccessGettingCurrentNetwork()
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
            verifyCurrentNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getCurrentNetwork_noCurrentNetwork() {
        with(miscRobot) {
            // Given
            withNoCurrentNetwork()
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
            verifyNoCurrentNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getCurrentNetwork_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withWiseFyFailureGettingCurrentNetwork()
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetCurrentNetwork()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun getCurrentNetworkInfo_success() {
        with(miscRobot) {
            // Given
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()

            // When
            getCurrentNetworkInfo()

            // Then
            verifyTriedToGetCurrentNetworkInfo()
            verifyCurrentNetworkInfoIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getCurrentNetworkInfo_noCurrentNetworkInfo() {
        with(miscRobot) {
            // Given
            withNoCurrentNetworkInfo()
            launchMiscScreen()

            // When
            getCurrentNetworkInfo()

            // Then
            verifyTriedToGetCurrentNetworkInfo()
            verifyNoCurrentNetworkInfoIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getCurrentNetworkInfo_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withWiseFyFailureGettingCurrentNetworkInfo()
            launchMiscScreen()

            // When
            getCurrentNetworkInfo()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetCurrentNetworkInfo()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun getFrequency_belowLollipop() {
        with(miscRobot) {
            // Given
            withDeviceBelowLollipop()
            withPermission(ACCESS_FINE_LOCATION)
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyFrequencyLollipopNoticeIsDisplayed()
            dismissErrorDialog()
        }
    }

    @Test
    fun getFrequency_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getFrequency_permissionError_once_accessFineLocation() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withSuccessGettingFrequency()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
            verifyFrequencyIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getFrequency_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getFrequency_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getFrequency_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingFrequency()
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetFrequency()
            dismissResultsDialog()
        }
    }

    @Test
    fun getFrequency_success() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingFrequency()
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
            verifyFrequencyIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getFrequency_failureGettingFrequency() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermission(ACCESS_FINE_LOCATION)
            withFailureGettingFrequency()
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
            verifyFailureRetrievingFrequencyIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getFrequency_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureGettingFrequency()
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetFrequency()
            dismissResultsDialog()
        }
    }

    @Test
    fun getIP_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getIP_permissionError_once_accessFineLocation() {
        with(miscRobot) {
            // Given
            withSuccessGettingIP()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
            verifyIPIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getIP_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getIP_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getIP_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingIP()
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetIP()
            verifyIPIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getIP_success() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingIP()
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
            verifyIPIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getIP_failureGettingIp() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withFailureGettingIP()
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
            verifyFailureRetrievingIPIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getIP_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureGettingIP()
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetIP()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_permissionError_once_accessFineLocation() {
        with(miscRobot) {
            // Given
            withSuccessGettingNearbyAccessPoints()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingNearbyAccessPoints()
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetNearbyAccessPoints()
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_success() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingNearbyAccessPoints()
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
            verifyAccessPointsAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_noNearbyAccessPoints() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNoNearbyAccessPoints()
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
            verifyNoAccessPointsFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getNearbyAccessPoints_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureGettingNearbyAccessPoints()
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetNearbyAccessPoints()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun getSavedNetworks_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getSavedNetworks_permissionError_once() {
        with(miscRobot) {
            // Given
            withSuccessGettingSavedNetworks()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyTriedToGetSavedNetworks()
            verifySavedNetworksAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getSavedNetworks_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getSavedNetworks_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun getSavedNetworks_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingSavedNetworks()
            launchMiscScreen()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetSavedNetworks()
            verifySavedNetworksAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getSavedNetworks_success() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessGettingSavedNetworks()
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyTriedToGetSavedNetworks()
            verifySavedNetworksAreDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getSavedNetworks_noSavedNetworks() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNoSavedNetworks()
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyTriedToGetSavedNetworks()
            verifyNoSavedNetworksFoundIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun getSavedNetworks_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureGettingSavedNetworks()
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetSavedNetworks()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun ridiculousPermissionRequested() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(RANDO_PERMISSION_REQUEST_CODE)

            // Then
            verifyPermissionErrorShown()
            dismissErrorDialog()
        }
    }
}
