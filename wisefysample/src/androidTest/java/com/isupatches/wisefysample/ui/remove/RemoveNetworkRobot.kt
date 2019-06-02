package com.isupatches.wisefysample.ui.remove

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.internal.base.BaseRobot
import com.isupatches.wisefysample.internal.espresso.performClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndClick
import com.isupatches.wisefysample.internal.espresso.performScrollToAndReplaceText
import com.isupatches.wisefysample.internal.preferences.RemoveNetworkStore
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.ui.main.MainActivity

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

internal class RemoveNetworkRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>,
    private val wiseFyPublicApi: WiseFyPublicApi,
    private val removeNetworkStore: RemoveNetworkStore,
    permissionUtil: PermissionUtil
) : BaseRobot(activityTestRule, permissionUtil) {

    /*
     * Preconditions
     */

    fun withDetailsInStore() {
        with(removeNetworkStore) {
            setLastUsedRegex(TEST_SSID_1)
        }
    }

    fun withSuccessRemovingNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.networkRemoved()
            null
        }.whenever(wiseFyPublicApi).removeNetwork(
            eq(TEST_SSID_1),
            any()
        )
    }

    fun withNetworkNotFoundToRemove() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.networkNotFoundToRemove()
            null
        }.whenever(wiseFyPublicApi).removeNetwork(
            eq(TEST_SSID_1),
            any()
        )
    }

    fun withFailureRemovingNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.failureRemovingNetwork()
            null
        }.whenever(wiseFyPublicApi).removeNetwork(
            eq(TEST_SSID_1),
            any()
        )
    }

    fun withWiseFyFailureRemovingNetwork() {
        doAnswer { invocationOnMock ->
            val callback = invocationOnMock.arguments[1] as RemoveNetworkCallbacks
            callback.wisefyFailure(MISSING_PARAMETER)
            null
        }.whenever(wiseFyPublicApi).removeNetwork(
            eq(TEST_SSID_1),
            any()
        )
    }

    /*
     * Actions
     */

    fun launchRemoveNetworkScreen() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.menu_remove)).performClick()
    }

    fun removeNetwork() {
        onView(withId(R.id.networkNameEdt)).performScrollToAndReplaceText(TEST_SSID_1)
        onView(withId(R.id.removeNetworkBtn)).performScrollToAndClick()
    }

    /*
     * Permission Helpers
     */

    fun permissionCallbackTriggered(requestCode: Int, permissionResult: Int) {
        val activity = activityTestRule.activity
        val fragment: RemoveNetworkFragment? = activity
            .supportFragmentManager
            .findFragmentByTag(
                RemoveNetworkFragment.TAG
            ) as? RemoveNetworkFragment

        activity.runOnUiThread {
            fragment?.onRequestPermissionsResult(
                requestCode,
                emptyArray(),
                intArrayOf(permissionResult)
            )
        }
    }

    fun permissionCallbackTriggeredWithEmptyArray(requestCode: Int) {
        val activity = activityTestRule.activity
        val fragment: RemoveNetworkFragment? = activity
            .supportFragmentManager
            .findFragmentByTag(
                RemoveNetworkFragment.TAG
            ) as? RemoveNetworkFragment

        activity.runOnUiThread {
            fragment?.onRequestPermissionsResult(
                requestCode,
                emptyArray(),
                intArrayOf()
            )
        }
    }

    /*
     * Verification
     */

    fun verifyTriedToRemoveNetwork(times: Int = 1) {
        verify(wiseFyPublicApi, timeout(3000).times(times)).removeNetwork(eq(TEST_SSID_1), any())
    }
}
