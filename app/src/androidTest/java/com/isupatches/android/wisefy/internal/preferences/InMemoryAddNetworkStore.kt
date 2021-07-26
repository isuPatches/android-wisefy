package com.isupatches.android.wisefy.internal.preferences

import com.isupatches.android.wisefy.sample.internal.entities.NetworkType
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkStore

internal class InMemoryAddNetworkStore : AddNetworkStore {

    private var networkType: NetworkType? = null
    private var lastUsedNetworkName: String = ""
    private var lastUsedNetworkPassword: String = ""

    override fun clear() {
        networkType = null
        lastUsedNetworkName = ""
        lastUsedNetworkPassword = ""
    }

    /*
     * Network Type
     */

    override fun getNetworkType(): NetworkType = networkType ?: NetworkType.WPA2
    override fun setNetworkType(networkType: NetworkType) {
        this.networkType = networkType
    }

    /*
     * Last Used Network Name
     */

    override fun getLastUsedNetworkName() = lastUsedNetworkName
    override fun setLastUsedNetworkName(lastUsedNetworkName: String) {
        this.lastUsedNetworkName = lastUsedNetworkName
    }

    /*
     * Last Used Network Password
     */

    override fun getLastUsedNetworkPassword() = lastUsedNetworkPassword
    override fun setLastUsedNetworkPassword(lastUsedNetworkPassword: String) {
        this.lastUsedNetworkPassword = lastUsedNetworkPassword
    }
}
