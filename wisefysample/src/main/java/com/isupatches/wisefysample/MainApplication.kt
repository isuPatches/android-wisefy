package com.isupatches.wisefysample

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

import com.isupatches.wisefysample.internal.di.PermissionsModule
import com.isupatches.wisefysample.internal.di.PreferencesModule
import com.isupatches.wisefysample.internal.di.ScreenBindingsModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.HasSupportFragmentInjector

import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("Registered")
internal open class MainApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjection()
    }

    private fun initializeDependencyInjection() {
        mainApplicationComponent = DaggerMainApplication_MainApplicationComponent.builder()
                .application(this)
                .build()
        mainApplicationComponent.inject(this)
    }

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector() = activityInjector

    @Inject lateinit var supportFragmentInject: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = supportFragmentInject

    private lateinit var mainApplicationComponent: MainApplicationComponent

    @Singleton
    @Component(modules = [
        AndroidSupportInjectionModule::class,
        ScreenBindingsModule::class,
        PermissionsModule::class,
        PreferencesModule::class
    ])
    interface MainApplicationComponent {

        fun inject(mainApplication: MainApplication)

        @Component.Builder interface Builder {
            fun build(): MainApplicationComponent

            @BindsInstance fun application(application: Context): Builder
        }
    }
}
