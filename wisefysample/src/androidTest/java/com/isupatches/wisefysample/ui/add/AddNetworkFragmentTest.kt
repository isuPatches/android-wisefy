package com.isupatches.wisefysample.ui.add

import androidx.test.espresso.intent.rule.IntentsTestRule

import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AddNetworkFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    private lateinit var addNetworkRobot: AddNetworkRobot

    @Before override fun setUp() {
        super.setUp()
        addNetworkRobot = AddNetworkRobot(activityTestRule, wiseFy)
    }

    @Test fun addOpenNetwork_success() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingOpenNetwork()
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
        }
    }

    @Test fun addOpenNetwork_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withWifiManagerFailureAddingOpenNetwork()
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
        }
    }

    @Test fun addOpenNetwork_wiseFyFailure() {
        with(addNetworkRobot) {
            // Given
            withWiseFyFailureAddingOpenNetwork()
            launchAddNetworkScreen()

            // When
            addOpenNetwork()

            // Then
            verifyTriedToAddOpenNetwork()
        }
    }

    @Test fun addWEPNetwork_success() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingWEPNetwork()
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
        }
    }

    @Test fun addWEPNetwork_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withWifiManagerFailureAddingWEPNetwork()
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
        }
    }

    @Test fun addWEPNetwork_wiseFyManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withWiseFyFailureAddingWEPNetwork()
            launchAddNetworkScreen()

            // When
            addWEPNetwork()

            // Then
            verifyTriedToAddWEPNetwork()
        }
    }

    @Test fun addWPA2Network_success() {
        with(addNetworkRobot) {
            // Given
            withSuccessAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
        }
    }

    @Test fun addWPA2Network_wifiManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withWifiManagerFailureAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
        }
    }

    @Test fun addWPA2Network_wiseFyManagerFailure() {
        with(addNetworkRobot) {
            // Given
            withWiseFyFailureAddingWPA2Network()
            launchAddNetworkScreen()

            // When
            addWPA2Network()

            // Then
            verifyTriedToAddWPA2Network()
        }
    }
}
