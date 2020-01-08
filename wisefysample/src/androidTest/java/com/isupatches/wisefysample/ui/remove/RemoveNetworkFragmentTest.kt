package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule
import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RemoveNetworkFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_FINE_LOCATION)

    private lateinit var removeNetworkRobot: RemoveNetworkRobot

    @Before
    override fun setUp() {
        super.setUp()
        removeNetworkRobot = RemoveNetworkRobot(
            activityTestRule,
            wiseFy,
            removeNetworkStore,
            permissionUtil
        )
    }

    @Test
    fun loadsFromStore() {
        with(removeNetworkRobot) {
            // Given
            withDetailsInStore()

            // When
            launchRemoveNetworkScreen()

            // Then
            verifyNetworkNameIsPopulated()
        }
    }

    @Test
    fun removeNetwork_permissionErrorDialog() {
        with(removeNetworkRobot) {
            // Given
            launchRemoveNetworkScreen()

            // When
            permissionCallbackTriggered(
                RemoveNetworkFragment.WISEFY_REMOVE_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun removeNetwork_permissionError_once() {
        with(removeNetworkRobot) {
            // Given
            withSuccessRemovingNetwork()
            withPermissionDeniedOnce(ACCESS_FINE_LOCATION)
            launchRemoveNetworkScreen()

            // When
            removeNetwork()

            // Then
            verifyTriedToRemoveNetwork()
            verifyNetworkRemovedIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun removeNetwork_permissionError_emptyList() {
        with(removeNetworkRobot) {
            // Given
            launchRemoveNetworkScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(
                RemoveNetworkFragment.WISEFY_REMOVE_NETWORK_REQUEST_CODE
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun removeNetwork_permissionError_permissionDenied() {
        with(removeNetworkRobot) {
            // Given
            launchRemoveNetworkScreen()

            // When
            permissionCallbackTriggered(
                RemoveNetworkFragment.WISEFY_REMOVE_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_DENIED
            )

            // Then
            dismissErrorDialog()
        }
    }

    @Test
    fun removeNetwork_success_afterPermissionGranted() {
        with(removeNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessRemovingNetwork()
            launchRemoveNetworkScreen()
            enterNetworkDetails()

            // When
            permissionCallbackTriggered(
                RemoveNetworkFragment.WISEFY_REMOVE_NETWORK_REQUEST_CODE,
                PackageManager.PERMISSION_GRANTED
            )

            // Then
            verifyTriedToRemoveNetwork()
            verifyNetworkRemovedIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun removeNetwork_success() {
        with(removeNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withSuccessRemovingNetwork()
            launchRemoveNetworkScreen()

            // When
            removeNetwork()

            // Then
            verifyTriedToRemoveNetwork()
            verifyNetworkRemovedIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun removeNetwork_failureToRemoveNetwork() {
        with(removeNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withFailureRemovingNetwork()
            launchRemoveNetworkScreen()

            // When
            removeNetwork()

            // Then
            verifyTriedToRemoveNetwork()
            verifyFailureToRemovingNetworkIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun removeNetwork_networkNotFoundToRemove() {
        with(removeNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withNetworkNotFoundToRemove()
            launchRemoveNetworkScreen()

            // When
            removeNetwork()

            // Then
            verifyTriedToRemoveNetwork()
            verifyNetworkNotFoundToRemoveIsDisplayed()
            dismissResultsDialog()
        }
    }

    @Test
    fun removeNetwork_wiseFyFailure() {
        with(removeNetworkRobot) {
            // Given
            withPermission(ACCESS_FINE_LOCATION)
            withWiseFyFailureRemovingNetwork()
            launchRemoveNetworkScreen()

            // When
            removeNetwork()

            // Then
            verifyWiseFyFailureIsDisplayed()
            verifyTriedToRemoveNetwork()

            // And
            dismissErrorDialog()
        }
    }

    @Test
    fun ridiculousPermissionRequested() {
        with(removeNetworkRobot) {
            // Given
            launchRemoveNetworkScreen()

            // When
            permissionCallbackTriggeredWithEmptyArray(RANDO_PERMISSION_REQUEST_CODE)

            // Then
            verifyPermissionErrorShown()
            dismissErrorDialog()
        }
    }
}
