package com.isupatches.wisefy.connection

/**
 * An interface with methods that relate to checking device connectivity.
 *
 * @see [WiseFyConnectionLegacy]
 * @see [WiseFyConnectionSDK23]
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFyConnection {

    fun init()

    fun destroy()

    fun isCurrentNetworkConnectedToSSID(ssid: String?): Boolean

    fun isDeviceConnectedToWifiNetwork(): Boolean

    fun isDeviceConnectedToMobileNetwork(): Boolean

    fun isDeviceRoaming(): Boolean

    fun isNetworkConnected(): Boolean

    fun waitToConnectToSSID(ssid: String?, timeoutInMillis: Int): Boolean
}
