package com.isupatches.wisefy.callbacks

import android.net.wifi.WifiConfiguration

/**
 * Callbacks for retrieving a list of saved networks on a device.
 *
 * @see [BaseCallback]
 * @see [com.isupatches.wisefy.WiseFy.searchForSavedNetworks]
 *
 * @author Patches
 * @since 4.0
 */
interface SearchForSavedNetworksCallbacks : BaseCallback {

    /**
     * Called when there are no saved network configuration on the device matching the
     * given search criteria.
     *
     * @author Patches
     * @since 4.0
     */
    fun noSavedNetworksFound()

    /**
     * Called when WiseFy has successfully retrieved a list of saved networks matching
     * the given search criteria.
     *
     * @see [WifiConfiguration]
     *
     * @author Patches
     * @since 3.0
     */
    fun retrievedSavedNetworks(savedNetworks: List<@JvmSuppressWildcards WifiConfiguration>)
}
