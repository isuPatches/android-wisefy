package com.isupatches.wisefysample.ui.misc

import androidx.test.espresso.intent.rule.IntentsTestRule

import com.isupatches.wisefysample.internal.base.AbstractEspressoTestClass
import com.isupatches.wisefysample.ui.main.MainActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MiscFragmentTest : AbstractEspressoTestClass() {

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    private lateinit var miscRobot: MiscRobot

    @Before override fun setUp() {
        super.setUp()
        miscRobot = MiscRobot(activityTestRule, wiseFy)
    }

    @Test fun disableWifi() {
        with(miscRobot) {
            // Given
            launchMiscScreen()

            // When
        }
    }
}
