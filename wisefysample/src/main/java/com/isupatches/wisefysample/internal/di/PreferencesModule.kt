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
