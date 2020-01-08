/*
 * Copyright 2019 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import androidx.core.content.edit
import com.isupatches.wisefysample.internal.models.NetworkType

@VisibleForTesting internal const val PREF_NETWORK_TYPE = "network type"
@VisibleForTesting internal const val PREF_LAST_USED_NETWORK_NAME = "last used network name"
@VisibleForTesting internal const val PREF_LAST_USED_NETWORK_PASSWORD = "last used network password"

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

    override fun getLastUsedNetworkName() = sharedPreferences.getNonNullString(
        PREF_LAST_USED_NETWORK_NAME
    )

    override fun setLastUsedNetworkName(lastUsedNetworkName: String) {
        sharedPreferences.edit {
            putString(PREF_LAST_USED_NETWORK_NAME, lastUsedNetworkName)
        }
    }

    /*
     * Last used network password
     */

    override fun getLastUsedNetworkPassword() = sharedPreferences.getNonNullString(
        PREF_LAST_USED_NETWORK_PASSWORD
    )

    override fun setLastUsedNetworkPassword(lastUsedNetworkPassword: String) {
        sharedPreferences.edit {
            putString(PREF_LAST_USED_NETWORK_PASSWORD, lastUsedNetworkPassword)
        }
    }
}
