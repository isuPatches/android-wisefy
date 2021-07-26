package com.isupatches.android.wisefy.internal.di

import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    AndroidSupportInjectionModule::class,
    TestRxModule::class
]) interface TestCommonModule
