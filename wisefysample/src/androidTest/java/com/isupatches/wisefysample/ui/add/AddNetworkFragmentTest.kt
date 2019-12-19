package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AddNetworkFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    private lateinit var addNetworkRobot: AddNetworkRobot

    @Before override fun setUp() {
        super.setUp()
        addNetworkRobot = AddNetworkRobot(
            activityTestRule,
            wiseFy,
            addNetworkStore,
            permissionUtil
        )
    }

    @Test fun networkTypeSelector() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // Then
            checkOpenNetwork()
            verifyOpenNetworkIsChecked()
            verifyOnlyNetworkNameIsVisible()

            // And
            checkWEPNetwork()
            verifyWEPNetworkIsChecked()
            verifyNetworkNameAndPasswordAreBothVisible()

            // And
            checkWPA2Network()
            verifyWPA2NetworkIsChecked()
            verifyNetworkNameAndPasswordAreBothVisible()

            // And
            checkOpenNetwork()
            verifyOpenNetworkIsChecked()
            verifyOnlyNetworkNameIsVisible()
        }
    }

    @Test fun loadsOpenNetworkFromStore() {
        with(addNetworkRobot) {
            // Given
            withOpenNetworkInStore()

            // When
            launchAddNetworkScreen()

            // Then
            verifyOpenNetworkIsChecked()
            verifyNetworkNameIsPopulated()
        }
    }

    @Test fun loadsWEPNetworkFromStore() {
        with(addNetworkRobot) {
            // Given
            withWEPNetworkInStore()

            // When
            launchAddNetworkScreen()

            // Then
            verifyWEPNetworkIsChecked()
            verifyNetworkNameAndPasswordIsPopulated()
        }
    }

    @Test fun loadsWPA2NetworkFromStore() {
        with(addNetworkRobot) {
            // Given
            withWPA2NetworkInStore()

            // When
            launchAddNetworkScreen()

            // Then
            verifyWPA2NetworkIsChecked()
            verifyNetworkNameAndPasswordIsPopulated()
        }
    }

    @Test fun addOpenNetwork_permissionErrorDialog() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            launchAddNetworkScreen()
            addOpenNetwork()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun addOpenNetwork_permissionError_once() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingOpenNetwork()
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addOpenNetwork_permissionError_emptyList() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingOpenNetwork()
            launchAddNetworkScreen()
            addOpenNetwork()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToAddOpenNetwork()
        }
    }

    @Test fun addOpenNetwork_permissionError_permissionDenied() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingOpenNetwork()
            launchAddNetworkScreen()
            addOpenNetwork()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToAddOpenNetwork()
        }
    }

    @Test fun addOpenNetwork_success_afterPermissionGranted() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingOpenNetwork()
            launchAddNetworkScreen()
            addOpenNetwork()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToAddOpenNetwork(times = 2)
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addOpenNetwork_success() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingOpenNetwork()
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addOpenNetwork_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withWifiManagerFailureAddingOpenNetwork()
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
            verifyFailureAddingNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addOpenNetwork_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withWiseFyFailureAddingOpenNetwork()
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToAddOpenNetwork()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun addWEPNetwork_permissionErrorDialog() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            launchAddNetworkScreen()
            addWEPNetwork()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test fun addWEPNetwork_permissionError_once() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingWEPNetwork()
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWEPNetwork_permissionError_emptyList() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()
            addWEPNetwork()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToAddWEPNetwork()
        }
    }

    @Test fun addWEPNetwork_permissionError_permissionDenied() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()
            addWEPNetwork()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToAddWEPNetwork()
        }
    }

    @Test fun addWEPNetwork_success_afterPermissionGranted() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()
            addWEPNetwork()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToAddWEPNetwork(times = 2)
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWEPNetwork_success() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWEPNetwork_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withWifiManagerFailureAddingWEPNetwork()
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
            verifyFailureAddingNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWEPNetwork_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withWiseFyFailureAddingWEPNetwork()
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToAddWEPNetwork()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun addWPA2Network_permissionErrorDialog() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            launchAddNetworkScreen()
            addWPA2Network()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }
    @Test fun addWPA2Network_permissionError_once() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingWPA2Network()
            withPermissionDeniedOnce(ACCESS_WIFI_STATE)
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWPA2Network_permissionError_emptyList() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWPA2Network()
            launchAddNetworkScreen()
            addWPA2Network()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
            verifyTriedToAddWPA2Network()
        }
    }

    @Test fun addWPA2Network_permissionError_permissionDenied() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()
            addWPA2Network()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
            verifyTriedToAddWPA2Network()
        }
    }

    @Test fun addWPA2Network_success_afterPermissionGranted() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWPA2Network()
            launchAddNetworkScreen()
            addWPA2Network()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToAddWPA2Network(times = 2)
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWPA2Network_success() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withSuccessAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
            dismissResultsDialog()
        }
    }

    @Test fun addWPA2Network_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withWifiManagerFailureAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
            verifyFailureAddingNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test fun addWPA2Network_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_WIFI_STATE)
            withWiseFyFailureAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToAddWPA2Network()

            // And
            dismissErrorDialog()
        }
    }

    @Test fun ridiculousPermissionRequested() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(RANDO_PERMISSION_REQUEST_CODE)

            // Then
            verifyPermissionErrorShown()
            dismissErrorDialog()
        }
    }
}
