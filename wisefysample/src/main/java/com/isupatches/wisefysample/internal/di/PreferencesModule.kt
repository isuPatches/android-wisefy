package com.isupatches.wisefysample.internal.di

import android.content.Context
import androidx.annotation.StringRes

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.preferences.SearchStore
import com.isupatches.wisefysample.internal.preferences.SharedPreferencesSearchStore

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Suppress("unused")
@Module internal object PreferencesModule {

    @JvmStatic @Singleton @Provides
    fun provideSearchStore(context: Context): SearchStore = SharedPreferencesSearchStore(
        sharedPreferences = getSharedPreferences(context, R.string.preferences_search_data)
    )

    private fun getSharedPreferences(context: Context, @StringRes prefKey: Int) =
        context.getSharedPreferences(context.getString(prefKey), Context.MODE_PRIVATE)
}
