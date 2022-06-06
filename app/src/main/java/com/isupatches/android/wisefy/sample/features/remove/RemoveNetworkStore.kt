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
package com.isupatches.android.wisefy.sample.features.remove

import android.content.Context
import androidx.core.content.edit
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.scaffolding.BaseSharedPreferenceStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal interface RemoveNetworkStore {
    fun clear()

    fun getLastUsedRegex(): String

    fun setLastUsedRegex(lastUsedRegex: String)
}

internal class SharedPreferencesRemoveNetworkStore @Inject constructor(
    @ApplicationContext context: Context
) : BaseSharedPreferenceStore(), RemoveNetworkStore {

    private val sharedPreferences = getSharedPreferences(
        context,
        R.string.preferences_remove_network_data
    )

    override fun clear() {
        sharedPreferences.edit { clear() }
    }

    /*
     * Last used Regex
     */

    override fun getLastUsedRegex() = sharedPreferences.getLastUsedRegex()

    override fun setLastUsedRegex(lastUsedRegex: String) {
        sharedPreferences.setLastUsedRegex(lastUsedRegex)
    }
}
