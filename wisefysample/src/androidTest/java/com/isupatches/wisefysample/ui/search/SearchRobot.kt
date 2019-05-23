package com.isupatches.wisefysample.ui.search

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.ui.main.MainActivity

internal class SearchRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi
) {

    fun launchSearchScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_search)).performClick()
    }
}
