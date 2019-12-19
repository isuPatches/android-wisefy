package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences
import com.isupatches.wisefysample.TEST_SSID_1
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito

abstract class BaseSharedPreferencesStoreTest {

    protected val editor = mock<SharedPreferences.Editor>()
    protected val sharedPreferences = mock<SharedPreferences>().also {
        Mockito.`when`(it.edit()).thenReturn(editor)
    }

    fun verifyUpdatedLastUsedRegex() {
        verify(editor).putString(PREF_LAST_USED_REGEX, TEST_SSID_1)
    }

    fun verifyRetrievedLastUsedRegex() {
        verify(sharedPreferences).getString(PREF_LAST_USED_REGEX, "")
    }
}
