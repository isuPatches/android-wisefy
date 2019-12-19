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
package com.isupatches.wisefysample.internal.di

import android.content.Context
import androidx.annotation.StringRes
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.preferences.AddNetworkStore
import com.isupatches.wisefysample.internal.preferences.RemoveNetworkStore
import com.isupatches.wisefysample.internal.preferences.SearchStore
import com.isupatches.wisefysample.internal.preferences.SharedPreferencesAddNetworkStore
import com.isupatches.wisefysample.internal.preferences.SharedPreferencesRemoveNetworkStore
import com.isupatches.wisefysample.internal.preferences.SharedPreferencesSearchStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused", "UndocumentedPublicClass", "UndocumentedPublicFunction")
@Module internal object PreferencesModule {

    @JvmStatic @Singleton @Provides
    fun provideAddNetworkStore(context: Context): AddNetworkStore = SharedPreferencesAddNetworkStore(
        sharedPreferences = getSharedPreferences(context, R.string.preferences_add_network_data)
    )

    @JvmStatic @Singleton @Provides
    fun provideRemoveNetworkStore(context: Context): RemoveNetworkStore = SharedPreferencesRemoveNetworkStore(
        sharedPreferences = getSharedPreferences(context, R.string.preferences_remove_network_data)
    )

    @JvmStatic @Singleton @Provides
    fun provideSearchStore(context: Context): SearchStore = SharedPreferencesSearchStore(
        sharedPreferences = getSharedPreferences(context, R.string.preferences_search_data)
    )

    private fun getSharedPreferences(context: Context, @StringRes prefKey: Int) =
        context.getSharedPreferences(context.getString(prefKey), Context.MODE_PRIVATE)
}
