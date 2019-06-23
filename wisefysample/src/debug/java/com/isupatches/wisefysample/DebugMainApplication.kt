package com.isupatches.wisefysample

internal open class DebugMainApplication : MainApplication() {

    fun setTestComponent(component: MainApplicationComponent) {
        mainApplicationComponent = component
        mainApplicationComponent.inject(this)
    }
}
