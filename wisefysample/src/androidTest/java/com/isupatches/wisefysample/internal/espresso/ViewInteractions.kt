package com.isupatches.wisefysample.internal.espresso

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility

fun ViewInteraction.checkDoesNotExist(): ViewInteraction =
        check(doesNotExist())

fun ViewInteraction.checkIsDisplayed(): ViewInteraction =
        check(matches(isDisplayed()))

fun ViewInteraction.checkIsGone(): ViewInteraction =
        check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

fun ViewInteraction.checkIsVisible(): ViewInteraction =
        check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

fun ViewInteraction.checkIsInvisible(): ViewInteraction =
        check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))

fun ViewInteraction.checkEditTextHasErrorText(errorText: String): ViewInteraction =
        checkIsDisplayed()
        .check(matches(hasErrorText(errorText)))

fun ViewInteraction.checkIsChecked(): ViewInteraction =
        check(matches(isChecked()))

fun ViewInteraction.scrollToAndCheckIsDisplayed(): ViewInteraction =
        perform(scrollTo())
        .check(matches(isDisplayed()))

fun ViewInteraction.performClick(): ViewInteraction =
        checkIsDisplayed()
        .perform(click())

fun ViewInteraction.performScrollToAndClick(): ViewInteraction =
        perform(scrollTo())
        .checkIsDisplayed()
        .perform(click())

fun ViewInteraction.performScrollToAndReplaceText(text: String): ViewInteraction =
        perform(scrollTo())
        .checkIsDisplayed()
        .perform(replaceText(text))
