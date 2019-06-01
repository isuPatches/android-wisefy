package com.isupatches.wisefysample.ui.misc

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule

import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MiscFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_COARSE_LOCATION)

    private lateinit var miscRobot: MiscRobot

    @Before override fun setUp() {
        super.setUp()
        miscRobot = MiscRobot(activityTestRule, wiseFy, sdkUtil, permissionUtil)
    }

    @Test fun disableWifi_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            launchMiscScreen()
            disableWifi()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_DISABLE_WIFI_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun disableWifi_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(CHANGE_WIFI_STATE)
            launchMiscScreen()

            // When
            disableWifi()

            // Then
            verifyTriedToDisableWifi()
        }
    }

    @Test fun disableWifi_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessDisablingWifi()
            launchMiscScreen()
            disableWifi()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_DISABLE_WIFI_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToDisableWifi()
        }
    }

    @Test fun disableWifi_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessDisablingWifi()
            launchMiscScreen()
            disableWifi()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_DISABLE_WIFI_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToDisableWifi()
        }
    }

    @Test fun disableWifi_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessDisablingWifi()
            launchMiscScreen()
            disableWifi()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_DISABLE_WIFI_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToDisableWifi(times = 2)
        }
    }

    @Test fun disableWifi_success() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(CHANGE_WIFI_STATE)
            withSuccessDisablingWifi()
            launchMiscScreen()

            // When
            disableWifi()

            // Then
            verifyTriedToDisableWifi()
        }
    }

    @Test fun disableWifi_failureDisablingWifi() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withFailureDisablingWifi()
            launchMiscScreen()

            // When
            disableWifi()

            // Then
            verifyTriedToDisableWifi()
        }
    }

    @Test fun disableWifi_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
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

    @Test fun enableWifi_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            launchMiscScreen()
            enableWifi()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_ENABLE_WIFI_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun enableWifi_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(CHANGE_WIFI_STATE)
            launchMiscScreen()

            // When
            enableWifi()

            // Then
            verifyTriedToEnableWifi()
        }
    }

    @Test fun enableWifi_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessEnablingWifi()
            launchMiscScreen()
            enableWifi()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_ENABLE_WIFI_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToEnableWifi()
        }
    }

    @Test fun enableWifi_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessEnablingWifi()
            launchMiscScreen()
            enableWifi()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_ENABLE_WIFI_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToEnableWifi()
        }
    }

    @Test fun enableWifi_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessEnablingWifi()
            launchMiscScreen()
            enableWifi()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_ENABLE_WIFI_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToEnableWifi(times = 2)
        }
    }

    @Test fun enableWifi_success() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withSuccessEnablingWifi()
            launchMiscScreen()

            // When
            enableWifi()

            // Then
            verifyTriedToEnableWifi()
        }
    }

    @Test fun enableWifi_failureEnablingWifi() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
            withFailureEnablingWifi()
            launchMiscScreen()

            // When
            enableWifi()

            // Then
            verifyTriedToEnableWifi()
        }
    }

    @Test fun enableWifi_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(CHANGE_WIFI_STATE)
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

    @Test fun getCurrentNetwork_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchMiscScreen()
            getCurrentNetwork()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun getCurrentNetwork_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_permissionError_once_accessWifiState() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_permissionError_once_accessCoarseLocation() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingCurrentNetwork()
            launchMiscScreen()
            getCurrentNetwork()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingCurrentNetwork()
            launchMiscScreen()
            getCurrentNetwork()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingCurrentNetwork()
            launchMiscScreen()
            getCurrentNetwork()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetCurrentNetwork(times = 2)
        }
    }

    @Test fun getCurrentNetwork_success() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingCurrentNetwork()
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_noCurrentNetwork() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoCurrentNetwork()
            launchMiscScreen()

            // When
            getCurrentNetwork()

            // Then
            verifyTriedToGetCurrentNetwork()
        }
    }

    @Test fun getCurrentNetwork_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun getCurrentNetworkInfo_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
            launchMiscScreen()
            getCurrentNetworkInfo()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun getCurrentNetworkInfo_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_NETWORK_STATE)
            launchMiscScreen()

            // When
            getCurrentNetworkInfo()

            // Then
            verifyTriedToGetCurrentNetworkInfo()
        }
    }

    @Test fun getCurrentNetworkInfo_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()
            getCurrentNetworkInfo()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetCurrentNetworkInfo()
        }
    }

    @Test fun getCurrentNetworkInfo_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()
            getCurrentNetworkInfo()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetCurrentNetworkInfo()
        }
    }

    @Test fun getCurrentNetworkInfo_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()
            getCurrentNetworkInfo()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetCurrentNetworkInfo(times = 2)
        }
    }

    @Test fun getCurrentNetworkInfo_success() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()

            // When
            getCurrentNetworkInfo()

            // Then
            verifyTriedToGetCurrentNetworkInfo()
        }
    }

    @Test fun getCurrentNetworkInfo_noCurrentNetworkInfo() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
            withNoCurrentNetworkInfo()
            launchMiscScreen()

            // When
            getCurrentNetworkInfo()

            // Then
            verifyTriedToGetCurrentNetworkInfo()
        }
    }

    @Test fun getCurrentNetworkInfo_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_NETWORK_STATE)
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

    @Test fun getFrequency_belowLollipop() {
        with(miscRobot) {
            // Given
            withDeviceBelowLollipop()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyFrequencyLollipopNoticeIsDisplayed()
            dismissErrorDialog()
        }
    }

    @Test fun getFrequency_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchMiscScreen()
            getFrequency()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun getFrequency_permissionError_once() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_permissionError_once_accessWifiState() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_permissionError_once_accessCoarseLocation() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingFrequency()
            launchMiscScreen()
            getFrequency()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingFrequency()
            launchMiscScreen()
            getFrequency()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingFrequency()
            launchMiscScreen()
            getFrequency()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_FREQUENCY_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetFrequency(times = 2)
        }
    }

    @Test fun getFrequency_success() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingFrequency()
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_failureGettingFrequency() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withFailureGettingFrequency()
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyTriedToGetFrequency()
        }
    }

    @Test fun getFrequency_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withDeviceLollipopOrHigher()
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withWiseFyFailureGettingFrequency()
            launchMiscScreen()

            // When
            getFrequency()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToGetFrequency()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun getIP_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchMiscScreen()
            getIP()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun getIP_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_permissionError_once_accessWifiState() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_permissionError_once_accessCoarseLocation() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingIP()
            launchMiscScreen()
            getIP()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingIP()
            launchMiscScreen()
            getIP()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingIP()
            launchMiscScreen()
            getIP()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_IP_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetIP(times = 2)
        }
    }

    @Test fun getIP_success() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingIP()
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_noSavedNetworks() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withFailureGettingIP()
            launchMiscScreen()

            // When
            getIP()

            // Then
            verifyTriedToGetIP()
        }
    }

    @Test fun getIP_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun getNearbyAccessPoints_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            launchMiscScreen()
            getNearbyAccessPoints()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun getNearbyAccessPoints_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_permissionError_once_accessWifiState() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            withPermission(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_permissionError_once_accessCoarseLocation() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withPermissionDeniedOnce(ACCESS_COARSE_LOCATION)
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingNearbyAccessPoints()
            launchMiscScreen()
            getNearbyAccessPoints()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingNearbyAccessPoints()
            launchMiscScreen()
            getNearbyAccessPoints()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingNearbyAccessPoints()
            launchMiscScreen()
            getNearbyAccessPoints()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetNearbyAccessPoints(times = 2)
        }
    }

    @Test fun getNearbyAccessPoints_success() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withSuccessGettingNearbyAccessPoints()
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_noNearbyAccessPoints() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
            withNoNearbyAccessPoints()
            launchMiscScreen()

            // When
            getNearbyAccessPoints()

            // Then
            verifyTriedToGetNearbyAccessPoints()
        }
    }

    @Test fun getNearbyAccessPoints_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermissions(ACCESS_WIFI_STATE, ACCESS_COARSE_LOCATION)
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

    @Test fun getSavedNetworks_permissionErrorDialog() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            launchMiscScreen()
            getSavedNetworks()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun getSavedNetworks_permissionError_once() {
        with(miscRobot) {
            // Given
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyTriedToGetSavedNetworks()
        }
    }

    @Test fun getSavedNetworks_permissionError_emptyList() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()
            getSavedNetworks()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetSavedNetworks()
        }
    }

    @Test fun getSavedNetworks_permissionError_permissionDenied() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessGettingCurrentNetworkInfo()
            launchMiscScreen()
            getSavedNetworks()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToGetSavedNetworks()
        }
    }

    @Test fun getSavedNetworks_success_afterPermissionGranted() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessGettingSavedNetworks()
            launchMiscScreen()
            getSavedNetworks()

            // When
            permissionCallbackTriggered(
                MiscFragment.WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToGetSavedNetworks(times = 2)
        }
    }

    @Test fun getSavedNetworks_success() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessGettingSavedNetworks()
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyTriedToGetSavedNetworks()
        }
    }

    @Test fun getSavedNetworks_noSavedNetworks() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withNoSavedNetworks()
            launchMiscScreen()

            // When
            getSavedNetworks()

            // Then
            verifyTriedToGetSavedNetworks()
        }
    }

    @Test fun getSavedNetworks_wiseFyFailure() {
        with(miscRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
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

    @Test fun ridiculousPermissionRequested() {
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
