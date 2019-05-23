package com.isupatches.wisefysample

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

import com.isupatches.wisefysample.internal.di.PermissionsModule
import com.isupatches.wisefysample.internal.di.PreferencesModule
import com.isupatches.wisefysample.internal.di.ScreenBindingsModule
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider

import dagger.BindsInstance
import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.HasSupportFragmentInjector

import javax.inject.Inject
import javax.inject.Singleton

@Suppress("Registered", "UndocumentedPublicClass", "UndocumentedPublicFunction")
internal open class MainApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

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

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector() = activityInjector

    @Inject lateinit var supportFragmentInject: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = supportFragmentInject

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
