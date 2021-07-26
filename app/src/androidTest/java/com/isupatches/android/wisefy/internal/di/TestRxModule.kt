package com.isupatches.android.wisefy.internal.di

import com.isupatches.android.wisefy.TestRxSchedulersProvider
import com.isupatches.android.wisefy.sample.internal.util.RxSchedulersProvider
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module object TestRxModule {
    @Provides @JvmStatic fun rxSchedulersProvider(): RxSchedulersProvider = TestRxSchedulersProvider()
}
