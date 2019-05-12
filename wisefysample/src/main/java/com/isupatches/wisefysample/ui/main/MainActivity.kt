package com.isupatches.wisefysample.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.nav.openFragment
import com.isupatches.wisefysample.internal.nav.selectItem
import com.isupatches.wisefysample.ui.add.AddNetworkFragment
import com.isupatches.wisefysample.ui.misc.MiscFragment
import com.isupatches.wisefysample.ui.remove.RemoveNetworkFragment
import com.isupatches.wisefysample.ui.search.SearchFragment

import dagger.Module
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.bottomNavigationView

import javax.inject.Inject

internal class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

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
        @ContributesAndroidInjector fun addNetworkFragment(): AddNetworkFragment
        @ContributesAndroidInjector fun removeNetworkFragment(): RemoveNetworkFragment
        @ContributesAndroidInjector fun mainFragment(): MainFragment
        @ContributesAndroidInjector fun miscFragment(): MiscFragment
        @ContributesAndroidInjector fun searchFragment(): SearchFragment
    }
}
