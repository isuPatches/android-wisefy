package com.isupatches.android.wisefy.internal.di

import com.isupatches.android.wisefy.sample.MainApplication
import dagger.Component
import javax.inject.Singleton

@Suppress("unused")
@Singleton
@Component(modules = [
    TestCommonModule::class,
    TestScreenBindingsModule::class
])
internal interface TestMainApplicationComponent : MainApplication.MainApplicationComponent {

    @Component.Builder interface Builder : TestMainApplicationComponentBuilder<Builder> {
        fun build(): TestMainApplicationComponent
    }
}
