package com.isupatches.android.wisefy.sample

internal open class DebugMainApplication : MainApplication() {

    fun setTestComponent(component: MainApplicationComponent) {
        mainApplicationComponent = component
        mainApplicationComponent.inject(this)
    }
}
