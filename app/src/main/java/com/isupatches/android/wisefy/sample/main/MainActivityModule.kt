/*
 * Copyright 2022 Patches Klinefelter
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
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil
import com.isupatches.android.wisefy.sample.util.createWisefy
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Suppress("unused")
@Module
@InstallIn(ActivityComponent::class)
internal abstract class MainActivityModule {

    @Binds
    abstract fun bindSdkUtil(impl: DefaultSdkUtil): SdkUtil

    companion object {
        @Provides
        fun provideWiseFy(@ApplicationContext app: Context) = createWisefy(app)
    }
}
