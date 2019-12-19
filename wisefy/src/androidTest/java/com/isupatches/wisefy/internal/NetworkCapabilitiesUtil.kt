package com.isupatches.wisefy.internal

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

import com.isupatches.wisefy.connection.WiseFyConnectionSDK23

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * To create a NetworkCapabilities instance with a specified transport type.
 *
 * @param transportType The transport type for the NetworkCapabilities instance
 *
 * @return NetworkCapabilities - The network capabilities instance with a given transport type
 *
 * @see NetworkCapabilities
 *
 * @author Patches
 * @since 3.0
 */
internal fun getNetworkCapabilities(transportType: Int): NetworkCapabilities {
    val networkCapabilities = NetworkCapabilities(null)
    ReflectionHelpers.callInstanceMethod<Any>(
            networkCapabilities,
            "addTransportType",
            ReflectionHelpers.ClassParameter.from(Int::class.java, transportType)
    )
    return networkCapabilities
}

/**
 * To create a NetworkCapabilities instance with a specified set of capabilities.
 *
 * @return NetworkCapabilities - The network capabilities instance with a given transport type
 *
 * @see NetworkCapabilities
 *
 * @author Patches
 * @since 3.0
 */
internal fun getNetworkCapabilities(capabilities: Array<Int>): NetworkCapabilities {
    val networkCapabilities = NetworkCapabilities(null)
    for (capability in capabilities) {
        ReflectionHelpers.callInstanceMethod<Any>(
                networkCapabilities,
                "addCapability",
                ReflectionHelpers.ClassParameter.from(Int::class.java, capability)
        )
    }
    return networkCapabilities
}

/**
 * To create a NetworkCapabilities instance with a specified transport type and capabilities.
 *
 * @param transportType The transport type for the NetworkCapabilities instance
 * @param capabilities The int array of capabilities for the NetworkCapabilities instance
 *
 * @return NetworkCapabilities - The network capabilities instance with a given transport type and
 * set of capabilities
 *
 * @see NetworkCapabilities
 *
 * @author Patches
 * @since 3.0
 */
internal fun getNetworkCapabilities(transportType: Int, capabilities: Array<Int>): NetworkCapabilities {
    val networkCapabilities = NetworkCapabilities(null)
    ReflectionHelpers.callInstanceMethod<Any>(
            networkCapabilities,
            "addTransportType",
            ReflectionHelpers.ClassParameter.from(Int::class.java, transportType)
    )
    for (capability in capabilities) {
        ReflectionHelpers.callInstanceMethod<Any>(
            networkCapabilities,
            "addCapability",
            ReflectionHelpers.ClassParameter.from(Int::class.java, capability)
        )
    }
    return networkCapabilities
}

/**
 * To setup the network capabilities for WisefyConnectionSDK23Tests.
 *
 * @param wisefyConnection The wisefy connection SDK instance
 * @param mockConnectivityManager The mock connectivity manager instance
 * @param networkCapabilities The capabilities desired for test
 *
 * @see ConnectivityManager.getActiveNetwork
 * @see WiseFyConnectionSDK23.networkChangeCallbacks
 * @see NetworkCapabilities
 *
 * @author Patches
 * @since 4.0
 */
internal fun setUpNetworkCapabilities(
    wisefyConnection: WiseFyConnectionSDK23,
    mockConnectivityManager: ConnectivityManager,
    networkCapabilities: NetworkCapabilities
) {
    val network = mock(Network::class.java)
    `when`(mockConnectivityManager.activeNetwork).thenReturn(network)
    `when`(mockConnectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)
    wisefyConnection.networkChangeCallbacks.onCapabilitiesChanged(
        network,
        networkCapabilities
    )
}
