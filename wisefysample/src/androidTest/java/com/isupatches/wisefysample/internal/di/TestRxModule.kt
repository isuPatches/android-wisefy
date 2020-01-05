package com.isupatches.wisefysample.internal.di

import com.isupatches.wisefysample.TestRxSchedulersProvider
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module object TestRxModule {
    @Provides @JvmStatic fun rxSchedulersProvider(): RxSchedulersProvider = TestRxSchedulersProvider()
}
