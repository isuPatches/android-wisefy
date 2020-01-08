package com.isupatches.wisefysample.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseRobot
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndCheckIsDisplayed
import com.isupatches.wisefysample.internal.util.PermissionUtil

internal class MainRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    permissionUtil: PermissionUtil
) : BaseRobot(activityTestRule, permissionUtil) {

    /*
     * Actions
     */

    fun clickAddNetwork() {
        onView(withId(R.id.menu_add)).performClick()
    }

    fun clickRemoveNetwork() {
        onView(withId(R.id.menu_remove)).performClick()
    }

    fun clickHome() {
        onView(withId(R.id.menu_home)).performClick()
    }

    fun clickMisc() {
        onView(withId(R.id.menu_misc)).performClick()
    }

    fun clickSearch() {
        onView(withId(R.id.menu_search)).performClick()
    }

    fun launchActivity() {
        activityTestRule.launchActivity(Intent())
    }

    /*
     * Verification
     */

    fun verifyAddNetworkWasOpened() {
        onView(withId(R.id.addNetworkBtn)).performScrollToAndCheckIsDisplayed()
    }

    fun verifyRemoveNetworkWasOpened() {
        onView(withId(R.id.removeNetworkBtn)).performScrollToAndCheckIsDisplayed()
    }

    fun verifyHomeWasOpened() {
        onView(withId(R.id.wisefyImg)).performScrollToAndCheckIsDisplayed()
    }

    fun verifyMiscWasOpened() {
        onView(withId(R.id.disableWifiBtn)).performScrollToAndCheckIsDisplayed()
    }

    fun verifySearchWasOpened() {
        onView(withId(R.id.searchBtn)).performScrollToAndCheckIsDisplayed()
    }
}
