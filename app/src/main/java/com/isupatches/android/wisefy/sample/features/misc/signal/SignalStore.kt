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
package com.isupatches.android.wisefy.sample.features.misc.signal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREF_CALCULATE_SIGNAL_LEVEL_RSSI = "calculate_signal_level_rssi"
private const val PREF_COMPARE_SIGNAL_LEVEL_RSSI_1 = "compare_signal_level_rssi_1"
private const val PREF_COMPARE_SIGNAL_LEVEL_RSSI_2 = "compare_signal_level_rssi_2"

internal interface SignalStore {
    suspend fun clear()

    fun getCalculateSignalLevelRSSI(): Flow<String>

    fun getCompareSignalLevelRSSI1(): Flow<String>
    fun getCompareSignalLevelRSSI2(): Flow<String>

    suspend fun setCalculateSignalLevelRSSI(rssiLevel: String)

    suspend fun setCompareSignalLevelRSSI1(rssiLevel: String)
    suspend fun setCompareSignalLevelRSSI2(rssiLevel: String)
}

private val Context.signalDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "signalDataStore"
)

internal class DefaultSignalStore(
    private val context: Context
) : SignalStore {

    private val calculateSignalLevelRSSIKey = stringPreferencesKey(PREF_CALCULATE_SIGNAL_LEVEL_RSSI)
    private val compareSignalLevelRSSI1Key = stringPreferencesKey(PREF_COMPARE_SIGNAL_LEVEL_RSSI_1)
    private val compareSignalLevelRSSI2Key = stringPreferencesKey(PREF_COMPARE_SIGNAL_LEVEL_RSSI_2)

    override suspend fun clear() {
        context.signalDataStore.edit {
            clear()
        }
    }

    override fun getCalculateSignalLevelRSSI(): Flow<String> {
        return context.signalDataStore.data.map { preferences ->
            preferences[calculateSignalLevelRSSIKey] ?: ""
        }
    }

    override suspend fun setCalculateSignalLevelRSSI(rssiLevel: String) {
        context.signalDataStore.edit { preferences ->
            preferences[calculateSignalLevelRSSIKey] = rssiLevel
        }
    }

    override fun getCompareSignalLevelRSSI1(): Flow<String> {
        return context.signalDataStore.data.map { preferences ->
            preferences[compareSignalLevelRSSI1Key] ?: ""
        }
    }

    override suspend fun setCompareSignalLevelRSSI1(rssiLevel: String) {
        context.signalDataStore.edit { preferences ->
            preferences[compareSignalLevelRSSI1Key] = rssiLevel
        }
    }

    override fun getCompareSignalLevelRSSI2(): Flow<String> {
        return context.signalDataStore.data.map { preferences ->
            preferences[compareSignalLevelRSSI2Key] ?: ""
        }
    }

    override suspend fun setCompareSignalLevelRSSI2(rssiLevel: String) {
        context.signalDataStore.edit { preferences ->
            preferences[compareSignalLevelRSSI2Key] = rssiLevel
        }
    }
}
