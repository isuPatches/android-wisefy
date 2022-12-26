/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.sample.features.remove

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.isupatches.android.wisefy.sample.entities.SSIDType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREF_SSID_TYPE = "ssid_type"
private const val PREF_LAST_USED_NETWORK_INPUT = "last_used_network_input"

internal interface RemoveNetworkStore {
    suspend fun clear()

    fun getLastUsedNetworkInput(): Flow<String>
    fun getSSIDType(): Flow<SSIDType>

    suspend fun setLastUsedNetworkInput(lastUsedNetworkInput: String)
    suspend fun setSSIDType(ssidType: SSIDType)
}

private val Context.removeNetworkDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "removeNetworkDataStore"
)

internal class DefaultRemoveNetworkStore(
    private val context: Context
) : RemoveNetworkStore {

    private val ssidTypeKey = intPreferencesKey(PREF_SSID_TYPE)
    private val lastUsedNetworkInputKey = stringPreferencesKey(PREF_LAST_USED_NETWORK_INPUT)

    override suspend fun clear() {
        context.removeNetworkDataStore.edit {
            clear()
        }
    }

    /*
     * Last used network input
     */

    override fun getLastUsedNetworkInput(): Flow<String> {
        return context.removeNetworkDataStore.data.map { preferences ->
            preferences[lastUsedNetworkInputKey] ?: ""
        }
    }

    override suspend fun setLastUsedNetworkInput(lastUsedNetworkInput: String) {
        context.removeNetworkDataStore.edit { preferences ->
            preferences[lastUsedNetworkInputKey] = lastUsedNetworkInput
        }
    }

    /*
     * SSID Type
     */

    override fun getSSIDType(): Flow<SSIDType> {
        return context.removeNetworkDataStore.data.map { preferences ->
            SSIDType.of(preferences[ssidTypeKey] ?: SSIDType.SSID.intVal)
        }
    }

    override suspend fun setSSIDType(ssidType: SSIDType) {
        context.removeNetworkDataStore.edit { preferences ->
            preferences[ssidTypeKey] = ssidType.intVal
        }
    }
}
