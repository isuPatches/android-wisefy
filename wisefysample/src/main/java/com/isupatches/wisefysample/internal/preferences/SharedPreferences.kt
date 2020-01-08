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

internal fun SharedPreferences.getLastUsedRegex() = getNonNullString(PREF_LAST_USED_REGEX)

internal fun SharedPreferences.setLastUsedRegex(lastUsedRegex: String) {
    edit {
        putString(PREF_LAST_USED_REGEX, lastUsedRegex)
    }
}

internal fun SharedPreferences.getNonNullString(key: String) = getString(key, "") ?: ""
