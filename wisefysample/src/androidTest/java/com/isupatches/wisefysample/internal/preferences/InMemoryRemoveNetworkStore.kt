package com.isupatches.wisefysample.internal.preferences

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
