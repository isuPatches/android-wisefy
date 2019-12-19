/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample

import android.app.Application
import android.content.Context
import com.isupatches.wisefysample.internal.di.PermissionsModule
import com.isupatches.wisefysample.internal.di.PreferencesModule
import com.isupatches.wisefysample.internal.di.ScreenBindingsModule
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("Registered", "UndocumentedPublicClass", "UndocumentedPublicFunction")
internal open class MainApplication : Application(), HasAndroidInjector {

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjection()
    }

    private fun initializeDependencyInjection() {
        mainApplicationComponent = DaggerMainApplication_MainApplicationComponent.builder()
                .application(this)
                .rxSchedulersProvider(RxSchedulersProvider())
                .build()
        mainApplicationComponent.inject(this)
    }

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector() = androidInjector

    protected lateinit var mainApplicationComponent: MainApplicationComponent

    @Singleton
    @Component(modules = [
        AndroidSupportInjectionModule::class,
        ScreenBindingsModule::class,
        PermissionsModule::class,
        PreferencesModule::class
    ])
    internal interface MainApplicationComponent {

        fun inject(mainApplication: MainApplication)

        @Component.Builder interface Builder {
            fun build(): MainApplicationComponent

            @BindsInstance fun application(application: Context): Builder
            @BindsInstance fun rxSchedulersProvider(prov: RxSchedulersProvider): Builder
        }
    }
}
