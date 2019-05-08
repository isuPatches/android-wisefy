package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

import com.isupatches.wisefysample.internal.models.NetworkType

private const val PREF_NETWORK_TYPE = "network type"
private const val PREF_LAST_USED_NETWORK_NAME = "last used network name"
private const val PREF_LAST_USED_NETWORK_PASSWORD = "last used network password"

internal interface AddNetworkStore {
    fun clear()

    fun getNetworkType(): NetworkType
    fun getLastUsedNetworkName(): String
    fun getLastUsedNetworkPassword(): String

    fun setNetworkType(networkType: NetworkType)
    fun setLastUsedNetworkName(lastUsedNetworkName: String)
    fun setLastUsedNetworkPassword(lastUsedNetworkPassword: String)
}

internal class SharedPreferencesAddNetworkStore(
    private val sharedPreferences: SharedPreferences
) : AddNetworkStore {

    override fun clear() {
        sharedPreferences.edit { clear() }
    }

    /*
     * Network type
     */

    override fun getNetworkType() = NetworkType.of(
        sharedPreferences.getInt(PREF_NETWORK_TYPE, NetworkType.WPA2.intVal)
    )

    override fun setNetworkType(networkType: NetworkType) {
        sharedPreferences.edit {
            putInt(PREF_NETWORK_TYPE, networkType.intVal)
        }
    }

    /*
    * Last used network name
    */

    override fun getLastUsedNetworkName() = sharedPreferences.getString(
        PREF_LAST_USED_NETWORK_NAME,
        ""
    ) ?: ""
    override fun setLastUsedNetworkName(lastUsedNetworkName: String) {
        sharedPreferences.edit {
            putString(PREF_LAST_USED_NETWORK_NAME, lastUsedNetworkName)
        }
    }

    /*
     * Last used network password
     */

    override fun getLastUsedNetworkPassword() = sharedPreferences.getString(
        PREF_LAST_USED_NETWORK_PASSWORD,
        ""
    ) ?: ""

    override fun setLastUsedNetworkPassword(lastUsedNetworkPassword: String) {
        sharedPreferences.edit {
            putString(PREF_LAST_USED_NETWORK_PASSWORD, lastUsedNetworkPassword)
        }
    }
}
