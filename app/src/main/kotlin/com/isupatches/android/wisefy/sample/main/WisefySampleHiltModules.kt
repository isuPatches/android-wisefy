/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.sample.main

import android.content.Context
import com.isupatches.android.wisefy.sample.features.add.AddNetworkStore
import com.isupatches.android.wisefy.sample.features.add.AddNetworkStoreUsingDataStore
import com.isupatches.android.wisefy.sample.features.misc.signal.SignalStore
import com.isupatches.android.wisefy.sample.features.misc.signal.SignalStoreUsingDataStore
import com.isupatches.android.wisefy.sample.features.remove.RemoveNetworkStore
import com.isupatches.android.wisefy.sample.features.remove.RemoveNetworkStoreUsingDataStore
import com.isupatches.android.wisefy.sample.features.search.SearchStore
import com.isupatches.android.wisefy.sample.features.search.SearchStoreUsingDataStore
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.sample.util.WisefyFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Suppress("unused", "UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
internal abstract class WisefySampleSingletonModule {

    @Binds
    abstract fun bindSdkUtil(impl: DefaultSdkUtil): SdkUtil

    companion object {
        @Provides
        fun provideWiseFy(@ApplicationContext app: Context) = WisefyFactory.getInstance(context = app)
    }
}

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal interface WisefySampleViewModelModule {

    @Binds
    fun bindAddNetworkStore(impl: AddNetworkStoreUsingDataStore): AddNetworkStore

    @Binds
    fun bindRemoveNetworkStore(impl: RemoveNetworkStoreUsingDataStore): RemoveNetworkStore

    @Binds
    fun bindSearchStore(impl: SearchStoreUsingDataStore): SearchStore

    @Binds
    fun bindSignalStore(impl: SignalStoreUsingDataStore): SignalStore
}
