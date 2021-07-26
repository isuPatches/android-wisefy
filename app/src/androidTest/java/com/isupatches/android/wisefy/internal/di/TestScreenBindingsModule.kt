package com.isupatches.android.wisefy.internal.di

import com.isupatches.android.wisefy.sample.ui.add.AddNetworkFragment
import com.isupatches.android.wisefy.sample.ui.main.MainActivity
import com.isupatches.android.wisefy.sample.ui.main.MainFragment
import com.isupatches.android.wisefy.sample.ui.misc.MiscFragment
import com.isupatches.android.wisefy.sample.ui.remove.RemoveNetworkFragment
import com.isupatches.android.wisefy.sample.ui.search.SearchFragment
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
