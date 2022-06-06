/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.sample.features.add

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import androidx.core.content.edit
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.scaffolding.BaseSharedPreferenceStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@VisibleForTesting
internal const val PREF_NETWORK_TYPE = "network_type"

@VisibleForTesting
internal const val PREF_LAST_USED_NETWORK_NAME = "last_used_network_name"

@VisibleForTesting
internal const val PREF_LAST_USED_NETWORK_PASSWORD = "last_used_network_password"

internal interface AddNetworkStore {
    fun clear()

    fun getNetworkType(): NetworkType
    fun getLastUsedNetworkName(): String
    fun getLastUsedNetworkPassword(): String

    fun setNetworkType(networkType: NetworkType)
    fun setLastUsedNetworkName(lastUsedNetworkName: String)
    fun setLastUsedNetworkPassword(lastUsedNetworkPassword: String)
}

internal class SharedPreferencesAddNetworkStore @Inject constructor(
    @ApplicationContext context: Context
) : BaseSharedPreferenceStore(), AddNetworkStore {

    private val sharedPreferences: SharedPreferences = getSharedPreferences(
        context,
        R.string.preferences_add_network_data
    )

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
