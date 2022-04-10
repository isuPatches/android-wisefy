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
package com.isupatches.android.wisefy.sample.ui.main

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.ActivityMainBinding
import com.isupatches.android.wisefy.sample.internal.base.BaseActivity
import com.isupatches.android.wisefy.sample.internal.util.SdkUtil
import com.isupatches.android.wisefy.sample.internal.util.SdkUtilImpl
import com.isupatches.android.wisefy.sample.internal.util.createWiseFy
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkFragment
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkFragmentModule
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkScope
import com.isupatches.android.wisefy.sample.ui.misc.MiscFragment
import com.isupatches.android.wisefy.sample.ui.misc.MiscFragmentModule
import com.isupatches.android.wisefy.sample.ui.misc.MiscScope
import com.isupatches.android.wisefy.sample.ui.remove.RemoveNetworkFragment
import com.isupatches.android.wisefy.sample.ui.remove.RemoveNetworkFragmentModule
import com.isupatches.android.wisefy.sample.ui.remove.RemoveNetworkScope
import com.isupatches.android.wisefy.sample.ui.search.SearchFragment
import com.isupatches.android.wisefy.sample.ui.search.SearchFragmentModule
import com.isupatches.android.wisefy.sample.ui.search.SearchScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

internal class MainActivity : BaseActivity() {

    override val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)

    @Inject lateinit var wisefy: WisefyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        wisefy.init()
    }

    override fun onDestroy() {
        wisefy.dump()
        super.onDestroy()
    }
}

@Suppress("unused")
@Module internal interface MainActivityFragmentBindings {

    @AddNetworkScope
    @ContributesAndroidInjector(
        modules = [
            AddNetworkFragmentModule::class
        ]
    ) fun addNetworkFragment(): AddNetworkFragment

    @RemoveNetworkScope
    @ContributesAndroidInjector(
        modules = [
            RemoveNetworkFragmentModule::class
        ]
    ) fun removeNetworkFragment(): RemoveNetworkFragment

    @ContributesAndroidInjector
    fun mainFragment(): MainFragment

    @MiscScope
    @ContributesAndroidInjector(
        modules = [
            MiscFragmentModule::class
        ]
    ) fun miscFragment(): MiscFragment

    @SearchScope
    @ContributesAndroidInjector(
        modules = [
            SearchFragmentModule::class
        ]
    ) fun searchFragment(): SearchFragment
}

@Suppress("unused")
@Module internal abstract class MainActivityModule {

    @Binds abstract fun bindSdkUtil(impl: SdkUtilImpl): SdkUtil

    companion object {
        @Provides
        fun provideWiseFy(app: Context) = createWiseFy(app)
    }
}
