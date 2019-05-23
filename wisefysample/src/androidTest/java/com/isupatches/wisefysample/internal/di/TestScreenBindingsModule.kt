package com.isupatches.wisefysample.internal.di

import com.isupatches.wisefysample.ui.add.AddNetworkFragment
import com.isupatches.wisefysample.ui.main.MainActivity
import com.isupatches.wisefysample.ui.main.MainFragment
import com.isupatches.wisefysample.ui.misc.MiscFragment
import com.isupatches.wisefysample.ui.remove.RemoveNetworkFragment
import com.isupatches.wisefysample.ui.search.SearchFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module internal interface TestScreenBindingsModule {
    @ContributesAndroidInjector fun mainActivity(): MainActivity
    @ContributesAndroidInjector fun addNetworkFragment(): AddNetworkFragment
    @ContributesAndroidInjector fun removeNetworkFragment(): RemoveNetworkFragment
    @ContributesAndroidInjector fun mainFragment(): MainFragment
    @ContributesAndroidInjector fun miscFragment(): MiscFragment
    @ContributesAndroidInjector fun searchFragment(): SearchFragment
}