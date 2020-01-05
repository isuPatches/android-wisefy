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
package com.isupatches.wisefysample.ui.main

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.nav.openFragment
import com.isupatches.wisefysample.internal.nav.selectItem
import com.isupatches.wisefysample.internal.util.SdkUtil
import com.isupatches.wisefysample.internal.util.SdkUtilImpl
import com.isupatches.wisefysample.internal.util.createWiseFy
import com.isupatches.wisefysample.ui.add.AddNetworkFragment
import com.isupatches.wisefysample.ui.misc.MiscFragment
import com.isupatches.wisefysample.ui.remove.RemoveNetworkFragment
import com.isupatches.wisefysample.ui.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView

internal class MainActivity : DaggerAppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var wiseFy: WiseFyPublicApi

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            bottomNavigationView.selectItem(R.id.menu_home)
            openFragment(this, MainFragment.newInstance(), MainFragment.TAG)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wiseFy.dump()
    }

    @Suppress("ReturnCount")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> {
                openFragment(this, AddNetworkFragment.newInstance(), AddNetworkFragment.TAG)
                return true
            }
            R.id.menu_remove -> {
                openFragment(this, RemoveNetworkFragment.newInstance(), RemoveNetworkFragment.TAG)
                return true
            }
            R.id.menu_home -> {
                openFragment(this, MainFragment.newInstance(), MainFragment.TAG)
                return true
            }
            R.id.menu_search -> {
                openFragment(this, SearchFragment.newInstance(), SearchFragment.TAG)
                return true
            }
            R.id.menu_misc -> {
                openFragment(this, MiscFragment.newInstance(), MiscFragment.TAG)
                return true
            }
        }
        bottomNavigationView.selectItem(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    @Suppress("unused")
    @Module internal interface FragmentBindings {
        @ContributesAndroidInjector(modules = [
            AddNetworkFragment.AddNetworkFragmentModule::class
        ]) fun addNetworkFragment(): AddNetworkFragment

        @ContributesAndroidInjector(modules = [
            RemoveNetworkFragment.RemoveNetworkFragmentModule::class
        ]) fun removeNetworkFragment(): RemoveNetworkFragment

        @ContributesAndroidInjector fun mainFragment(): MainFragment

        @ContributesAndroidInjector(modules = [
            MiscFragment.MiscFragmentModule::class
        ]) fun miscFragment(): MiscFragment

        @ContributesAndroidInjector(modules = [
            SearchFragment.SearchFragmentModule::class
        ]) fun searchFragment(): SearchFragment
    }

    @Suppress("unused")
    @Module internal abstract class MainActivityModule {

        @Binds abstract fun bindSdkUtil(impl: SdkUtilImpl): SdkUtil

        @Module companion object {
            @Provides @JvmStatic
            fun provideWiseFy(activity: MainActivity) = createWiseFy(activity)
        }
    }
}
