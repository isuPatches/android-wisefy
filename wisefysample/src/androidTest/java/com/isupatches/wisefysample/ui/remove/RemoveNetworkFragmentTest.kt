package com.isupatches.wisefysample.ui.remove

import androidx.test.espresso.intent.rule.IntentsTestRule

import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RemoveNetworkFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    private lateinit var removeNetworkRobot: RemoveNetworkRobot

    @Before override fun setUp() {
        super.setUp()
        removeNetworkRobot = RemoveNetworkRobot(activityTestRule, wiseFy)
    }

    @Test fun removeNetwork() {
        with(removeNetworkRobot) {
            // Given
            launchRemoveNetworkScreen()

            // When
        }
    }
}
