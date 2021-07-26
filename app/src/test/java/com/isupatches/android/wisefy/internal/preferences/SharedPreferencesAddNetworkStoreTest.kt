package com.isupatches.android.wisefy.internal.preferences

import com.isupatches.android.wisefy.TEST_PASSWORD_1
import com.isupatches.android.wisefy.TEST_SSID_1
import com.isupatches.android.wisefy.sample.internal.entities.NetworkType
import com.isupatches.android.wisefy.sample.ui.add.PREF_LAST_USED_NETWORK_NAME
import com.isupatches.android.wisefy.sample.ui.add.PREF_LAST_USED_NETWORK_PASSWORD
import com.isupatches.android.wisefy.sample.ui.add.PREF_NETWORK_TYPE
import com.isupatches.android.wisefy.sample.ui.add.SharedPreferencesAddNetworkStore
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

internal class SharedPreferencesAddNetworkStoreTest : BaseSharedPreferencesStoreTest() {

    private val store by lazy {
        SharedPreferencesAddNetworkStore(sharedPreferences)
    }

    @Test
    fun clearsAddNetworkStoreData() {
        store.clear()
        verify(editor).clear()
    }

    /*
     * Network Type
     */

    @Test
    fun setNetworkType() {
        store.setNetworkType(NetworkType.WPA2)
        verify(editor).putInt(PREF_NETWORK_TYPE, NetworkType.WPA2.intVal)
    }

    @Test
    fun getNetworkType() {
        store.getNetworkType()
        verify(sharedPreferences).getInt(PREF_NETWORK_TYPE, NetworkType.WPA2.intVal)
    }

    /*
     * Last Used Network Name
     */

    @Test
    fun setLastUsedNetworkName() {
        store.setLastUsedNetworkName(TEST_SSID_1)
        verify(editor).putString(PREF_LAST_USED_NETWORK_NAME, TEST_SSID_1)
    }

    @Test
    fun getLastUsedNetworkName() {
        store.getLastUsedNetworkName()
        verify(sharedPreferences).getString(PREF_LAST_USED_NETWORK_NAME, "")
    }

    /*
     * Last Used Network Password
     */

    @Test
    fun setLastUsedNetworkPassword() {
        store.setLastUsedNetworkPassword(TEST_PASSWORD_1)
        verify(editor).putString(PREF_LAST_USED_NETWORK_PASSWORD, TEST_PASSWORD_1)
    }

    @Test
    fun getLastUsedNetworkPassword() {
        store.getLastUsedNetworkPassword()
        verify(sharedPreferences).getString(PREF_LAST_USED_NETWORK_PASSWORD, "")
    }
}
