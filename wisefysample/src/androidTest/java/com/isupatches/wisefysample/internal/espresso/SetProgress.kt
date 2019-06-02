package com.isupatches.wisefysample.internal.espresso

import android.view.View
import android.widget.SeekBar
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.ViewAction

import org.hamcrest.Matcher

fun setProgress(progress: Int): ViewAction {
    return object : ViewAction {

        override fun perform(uiController: UiController, view: View) {
            val seekBar = view as SeekBar
            seekBar.progress = progress
        }

        override fun getDescription(): String {
            return "Set progress value on a SeekBar"
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isAssignableFrom(SeekBar::class.java)
        }
    }
}