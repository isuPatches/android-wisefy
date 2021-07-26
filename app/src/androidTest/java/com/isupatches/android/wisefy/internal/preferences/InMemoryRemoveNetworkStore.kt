package com.isupatches.android.wisefy.internal.preferences

import com.isupatches.android.wisefy.sample.ui.remove.RemoveNetworkStore

internal class InMemoryRemoveNetworkStore : RemoveNetworkStore {

    private var lastUsedRegex: String = ""

    override fun clear() {
        lastUsedRegex = ""
    }

    override fun getLastUsedRegex() = lastUsedRegex
    override fun setLastUsedRegex(lastUsedRegex: String) {
        this.lastUsedRegex = lastUsedRegex
    }
}
