package com.isupatches.wisefysample.internal.di

import com.isupatches.wisefysample.ui.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module internal interface ScreenBindingsModule {

    @ContributesAndroidInjector(modules = [
        MainActivity.MainActivityModule::class,
        MainActivity.FragmentBindings::class
    ])
    fun mainActivity(): MainActivity
}
