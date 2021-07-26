package com.isupatches.android.wisefy.internal.preferences

import android.content.SharedPreferences
import com.isupatches.android.wisefy.TEST_SSID_1
import com.isupatches.android.wisefy.sample.internal.preferences.PREF_LAST_USED_REGEX
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito

abstract class BaseSharedPreferencesStoreTest {

    protected val editor: SharedPreferences.Editor = mock()
    protected val sharedPreferences: SharedPreferences = mock<SharedPreferences>().also {
        Mockito.`when`(it.edit()).thenReturn(editor)
    }

    fun verifyUpdatedLastUsedRegex() {
        verify(editor).putString(PREF_LAST_USED_REGEX, TEST_SSID_1)
    }

    fun verifyRetrievedLastUsedRegex() {
        verify(sharedPreferences).getString(PREF_LAST_USED_REGEX, "")
    }
}
