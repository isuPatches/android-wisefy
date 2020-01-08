package com.isupatches.wisefysample.internal.espresso

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility

fun ViewInteraction.checkIsDisplayed(): ViewInteraction =
    check(matches(isDisplayed()))

fun ViewInteraction.performScrollToAndCheckIsDisplayed(): ViewInteraction = perform(scrollTo())
    .check(matches(isDisplayed()))

fun ViewInteraction.checkIsVisible(): ViewInteraction =
    check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

fun ViewInteraction.checkIsInvisible(): ViewInteraction =
    check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))

fun ViewInteraction.checkIsChecked(): ViewInteraction =
    check(matches(isChecked()))

fun ViewInteraction.checkIsNotChecked(): ViewInteraction =
    check(matches(isNotChecked()))

fun ViewInteraction.performClick(): ViewInteraction = checkIsDisplayed()
    .perform(click())

fun ViewInteraction.performScrollToAndClick(): ViewInteraction = perform(scrollTo())
    .checkIsDisplayed()
    .perform(click())

fun ViewInteraction.performScrollToAndReplaceText(text: String): ViewInteraction = perform(scrollTo())
    .checkIsDisplayed()
    .perform(replaceText(text))
