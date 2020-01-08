package com.isupatches.wisefysample.internal.base

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.RANDO_PERMISSION_REQUEST_CODE
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.internal.espresso.checkIsDisplayed
import com.isupatches.wisefysample.internal.espresso.getString
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndCheckIsDisplayed
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.mockito.Mockito.`when`

internal abstract class BaseRobot(
    private val activityTestRule: ActivityTestRule<*>,
    private val permissionUtil: PermissionUtil
) {

    fun dismissResultsDialog() {
        onView(withText(R.string.ok)).performClick()
    }

    fun dismissErrorDialog() {
        onView(withText(R.string.ok)).performClick()
    }

    fun withPermission(permission: String) {
        `when`(permissionUtil.isPermissionGranted(any(), eq(permission)))
            .thenReturn(true)
    }

    fun withPermissionDeniedOnce(permission: String) {
        `when`(permissionUtil.isPermissionGranted(any(), eq(permission)))
            .thenReturn(false)
            .thenReturn(true)
    }

    fun verifyNetworkNameIsPopulated() {
        onView(allOf(withId(R.id.networkNameEdt), withText(TEST_SSID_1))).performScrollToAndCheckIsDisplayed()
    }

    fun verifyPermissionErrorShown() {
        onView(withText(
            activityTestRule.getString(
                R.string.permission_error_unhandled_request_code_args,
                RANDO_PERMISSION_REQUEST_CODE
            )
        )).checkIsDisplayed()
    }

    fun verifyWiseFyFailureIsDisplayed() {
        onView(withText(R.string.wisefy_error)).checkIsDisplayed()
        onView(withText(
            activityTestRule.getString(R.string.wisefy_error_descriptions_args, MISSING_PARAMETER)
        )).checkIsDisplayed()
    }

    fun verifyAccessPointsAreDisplayed() {
        onView(withText(containsString("Access points"))).checkIsDisplayed()
    }

    fun verifyNoAccessPointsFoundIsDisplayed() {
        onView(withText(R.string.no_access_points_found)).checkIsDisplayed()
    }

    fun verifySavedNetworksAreDisplayed() {
        onView(withText(containsString("Saved networks"))).checkIsDisplayed()
    }

    fun verifyNoSavedNetworksFoundIsDisplayed() {
        onView(withText(R.string.no_saved_networks_found)).checkIsDisplayed()
    }
}
