package com.isupatches.wisefysample.ui.misc

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.ui.main.MainActivity

internal class MiscRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi
) {

    fun launchMiscScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_misc)).performClick()
    }
}
