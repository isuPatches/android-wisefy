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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.isupatches.android.wisefy.sample.entities.NetworkType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREF_NETWORK_TYPE = "network_type"
private const val PREF_LAST_USED_NETWORK_INPUT = "last_used_network_input"
private const val PREF_LAST_USED_NETWORK_PASSPHRASE_INPUT = "last_used_network_passphrase_input"
private const val PREF_LAST_USED_NETWORK_BSSID_INPUT = "last_used_network_bssid_input"

internal interface AddNetworkStore {
    suspend fun clear()

    fun getNetworkType(): Flow<NetworkType>
    fun getLastUsedNetworkInput(): Flow<String>
    fun getLastUsedNetworkPassphraseInput(): Flow<String>
    fun getLastUsedNetworkBSSIDInput(): Flow<String>

    suspend fun setNetworkType(networkType: NetworkType)
    suspend fun setLastUsedNetworkInput(lastUsedNetworkInput: String)
    suspend fun setLastUsedNetworkPassphraseInput(lastUsedNetworkPassphraseInput: String)
    suspend fun setLastUsedNetworkBSSIDInput(lastUsedNetworkBSSIDInput: String)
}

private val Context.addNetworkDataStore: DataStore<Preferences> by preferencesDataStore(name = "addNetworkDataStore")

internal class DefaultAddNetworkStore(
    private val context: Context
) : AddNetworkStore {

    private val networkTypeKey = intPreferencesKey(PREF_NETWORK_TYPE)
    private val lastUsedNetworkInputKey = stringPreferencesKey(PREF_LAST_USED_NETWORK_INPUT)
    private val lastUsedNetworkPassphraseInputKey = stringPreferencesKey(PREF_LAST_USED_NETWORK_PASSPHRASE_INPUT)
    private val lastUsedNetworkBSSIDInputKey = stringPreferencesKey(PREF_LAST_USED_NETWORK_BSSID_INPUT)

    override suspend fun clear() {
        context.addNetworkDataStore.edit {
            clear()
        }
    }

    /*
     * Network type
     */

    override fun getNetworkType(): Flow<NetworkType> {
        return context.addNetworkDataStore.data.map { preferences ->
            NetworkType.of(preferences[networkTypeKey] ?: NetworkType.WPA2.intVal)
        }
    }

    override suspend fun setNetworkType(networkType: NetworkType) {
        context.addNetworkDataStore.edit { preferences ->
            preferences[networkTypeKey] = networkType.intVal
        }
    }

    /*
    * Last used network input
    */

    override fun getLastUsedNetworkInput(): Flow<String> {
        return context.addNetworkDataStore.data.map { preferences ->
            preferences[lastUsedNetworkInputKey] ?: ""
        }
    }

    override suspend fun setLastUsedNetworkInput(lastUsedNetworkInput: String) {
        context.addNetworkDataStore.edit { preferences ->
            preferences[lastUsedNetworkInputKey] = lastUsedNetworkInput
        }
    }

    /*
     * Last used network passphrase input
     */

    override fun getLastUsedNetworkPassphraseInput(): Flow<String> {
        return context.addNetworkDataStore.data.map { preferences ->
            preferences[lastUsedNetworkPassphraseInputKey] ?: ""
        }
    }

    override suspend fun setLastUsedNetworkPassphraseInput(lastUsedNetworkPassphraseInput: String) {
        context.addNetworkDataStore.edit { preferences ->
            preferences[lastUsedNetworkPassphraseInputKey] = lastUsedNetworkPassphraseInput
        }
    }

    /*
     * Last used network BSSID input
     */

    override fun getLastUsedNetworkBSSIDInput(): Flow<String> {
        return context.addNetworkDataStore.data.map { preferences ->
            preferences[lastUsedNetworkBSSIDInputKey] ?: ""
        }
    }

    override suspend fun setLastUsedNetworkBSSIDInput(lastUsedNetworkBSSIDInput: String) {
        context.addNetworkDataStore.edit { preferences ->
            preferences[lastUsedNetworkBSSIDInputKey] = lastUsedNetworkBSSIDInput
        }
    }
}
