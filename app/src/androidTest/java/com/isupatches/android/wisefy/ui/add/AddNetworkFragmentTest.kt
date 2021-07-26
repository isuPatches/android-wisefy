package com.isupatches.android.wisefy.ui.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule
import com.isupatches.android.wisefy.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.android.wisefy.internal.base.AbstractEspressoTestClass
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkFragment
import com.isupatches.android.wisefy.sample.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AddNetworkFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_FINE_LOCATION)

    private lateinit var addNetworkRobot: AddNetworkRobot

    @Before
    override fun setUp() {
        super.setUp()
        addNetworkRobot = AddNetworkRobot(
            activityTestRule,
            wiseFy,
            addNetworkStore,
            permissionUtil
        )
    }

    @Test
    fun networkTypeSelector() {
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

    @Test
    fun loadsOpenNetworkFromStore() {
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

    @Test
    fun loadsWEPNetworkFromStore() {
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

    @Test
    fun loadsWPA2NetworkFromStore() {
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

    @Test
    fun addOpenNetwork_permissionErrorDialog() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addOpenNetwork_permissionError_once() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingOpenNetwork()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun addOpenNetwork_permissionError_emptyList() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addOpenNetwork_permissionError_permissionDenied() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addOpenNetwork_success_afterPermissionGranted() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessAddingOpenNetwork()
            launchAddNetworkScreen()
            enterOpenNetworkDetails()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToAddOpenNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun addOpenNetwork_success() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addOpenNetwork_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addOpenNetwork_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addWEPNetwork_permissionErrorDialog() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addWEPNetwork_permissionError_once() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingWEPNetwork()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun addWEPNetwork_permissionError_emptyList() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addWEPNetwork_permissionError_permissionDenied() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addWEPNetwork_success_afterPermissionGranted() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()
            enterWEPNetworkDetails()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WEP_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToAddWEPNetwork()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun addWEPNetwork_success() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addWEPNetwork_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addWEPNetwork_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addWPA2Network_permissionErrorDialog() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addWPA2Network_permissionError_once() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingWPA2Network()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun addWPA2Network_permissionError_emptyList() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addWPA2Network_permissionError_permissionDenied() {
        with(addNetworkRobot) {
            // Given
            launchAddNetworkScreen()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun addWPA2Network_success_afterPermissionGranted() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessAddingWPA2Network()
            launchAddNetworkScreen()
            enterWPA2NetworkDetails()

            // When
            permissionCallbackTriggered(
                AddNetworkFragment.WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToAddWPA2Network()
            verifyNewNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun addWPA2Network_success() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
            dismissResultsDialog()
        }
    }

    @Test
    fun addWPA2Network_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun addWPA2Network_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
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

    @Test
    fun ridiculousPermissionRequested() {
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
