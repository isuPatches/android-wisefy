package com.isupatches.wisefysample.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.rule.GrantPermissionRule

import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MainFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(ACCESS_COARSE_LOCATION)

    private lateinit var mainRobot: MainRobot

    @Before override fun setUp() {
        super.setUp()
        mainRobot = MainRobot(activityTestRule, permissionUtil)
    }

    @Test fun verifyOpensAddNetwork() {
        with(mainRobot) {
            // Given
            launchActivity()

            // When
            clickAddNetwork()

            // Then
            verifyAddNetworkWasOpened()

            // And When
            clickHome()

            // Then
            verifyHomeWasOpened()
        }
    }

    @Test fun verifyOpensRemoveNetwork() {
        with(mainRobot) {
            // Given
            launchActivity()

            // When
            clickRemoveNetwork()

            // Then
            verifyRemoveNetworkWasOpened()

            // And When
            clickHome()

            // Then
            verifyHomeWasOpened()
        }
    }

    @Test fun verifyOpensMisc() {
        with(mainRobot) {
            // Given
            launchActivity()

            // When
            clickMisc()

            // Then
            verifyMiscWasOpened()

            // And When
            clickHome()

            // Then
            verifyHomeWasOpened()
        }
    }

    @Test fun verifyOpensSearch() {
        with(mainRobot) {
            // Given
            launchActivity()

            // When
            clickSearch()

            // Then
            verifySearchWasOpened()

            // And When
            clickHome()

            // Then
            verifyHomeWasOpened()
        }
    }
}
