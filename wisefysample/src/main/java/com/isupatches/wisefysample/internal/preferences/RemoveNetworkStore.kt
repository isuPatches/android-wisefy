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
import androidx.core.content.edit

internal interface RemoveNetworkStore {
    fun clear()

    fun getLastUsedRegex(): String

    fun setLastUsedRegex(lastUsedRegex: String)
}

internal class SharedPreferencesRemoveNetworkStore(
    private val sharedPreferences: SharedPreferences
) : RemoveNetworkStore {

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
