package com.isupatches.android.wisefy.internal.preferences

import com.isupatches.android.wisefy.TEST_SSID_1
import com.isupatches.android.wisefy.sample.ui.remove.SharedPreferencesRemoveNetworkStore
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

internal class SharedPreferencesRemoveNetworkStoreTest : BaseSharedPreferencesStoreTest() {

    private val store by lazy {
        SharedPreferencesRemoveNetworkStore(sharedPreferences)
    }

    @Test
    fun clearsRemoveNetworkStoreData() {
        store.clear()
        verify(editor).clear()
    }

    /*
     * Last Used Regex
     */

    @Test
    fun setLastUsedRegex_remove() {
        store.setLastUsedRegex(TEST_SSID_1)
        verifyUpdatedLastUsedRegex()
    }

    @Test
    fun getLastUsedRegex_remove() {
        store.getLastUsedRegex()
        verifyRetrievedLastUsedRegex()
    }
}
