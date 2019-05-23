package com.isupatches.wisefysample.ui.search

import androidx.test.espresso.intent.rule.IntentsTestRule

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
        searchRobot = SearchRobot(activityTestRule, wiseFy)
    }

    @Test fun searchForAccessPoint() {
        with(searchRobot) {
            // Given
            launchSearchScreen()

            // When
        }
    }
}
