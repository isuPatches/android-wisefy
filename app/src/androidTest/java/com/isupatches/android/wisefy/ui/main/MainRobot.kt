package com.isupatches.android.wisefy.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.isupatches.android.wisefy.R
import com.isupatches.android.wisefy.internal.base.BaseRobot
import com.isupatches.android.wisefy.internal.espresso.performClick
import com.isupatches.android.wisefy.internal.espresso.performScrollToAndCheckIsDisplayed
import com.isupatches.android.wisefy.sample.internal.util.PermissionUtil
import com.isupatches.android.wisefy.sample.ui.main.MainActivity

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
