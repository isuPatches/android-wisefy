package com.isupatches.wisefy.callbacks

import android.net.wifi.WifiConfiguration

/**
 * Callbacks for retrieving a saved network on a device.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.searchForSavedNetwork]
 *
 * @author Patches
 * @see 4.0
 */
interface SearchForSavedNetworkCallbacks : BaseCallback {

    /**
     * Called when there are no saved network configurations matching search criteria.
     *
     * @author Patches
     * @since 4.0
     */
    fun savedNetworkNotFound()

    /**
     * Called when WiseFy has successfully retrieved a matching saved network configuration.
     *
     * @see [WifiConfiguration]
     *
     * @author Patches
     * @since 4.0
     */
    fun retrievedSavedNetwork(savedNetwork: WifiConfiguration)
}
