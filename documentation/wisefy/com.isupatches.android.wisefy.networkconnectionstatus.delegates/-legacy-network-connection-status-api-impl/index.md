//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus.delegates](../index.md)/[LegacyNetworkConnectionStatusApiImpl](index.md)

# LegacyNetworkConnectionStatusApiImpl

[androidJvm]\
internal class [LegacyNetworkConnectionStatusApiImpl](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **sdkUtil**: [SdkUtil](../../com.isupatches.android.wisefy.util/-sdk-util/index.md), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?) : [ConnectivityManager.NetworkCallback](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.NetworkCallback.html), [LegacyNetworkConnectionStatusApi](../-legacy-network-connection-status-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [attachNetworkWatcher](attach-network-watcher.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [attachNetworkWatcher](attach-network-watcher.md)() |
| [detachNetworkWatcher](detach-network-watcher.md) | [androidJvm]<br>open override fun [detachNetworkWatcher](detach-network-watcher.md)() |
| [doesNetworkHaveCapability](does-network-have-capability.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>private fun [doesNetworkHaveCapability](does-network-have-capability.md)(capability: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [doesNetworkHaveTransportTypeAndInternetCapability](does-network-have-transport-type-and-internet-capability.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>private fun [doesNetworkHaveTransportTypeAndInternetCapability](does-network-have-transport-type-and-internet-capability.md)(transportType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getActiveNetworkCapabilities](get-active-network-capabilities.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>private fun [getActiveNetworkCapabilities](get-active-network-capabilities.md)(): [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)? |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md) | [androidJvm]<br>open override fun [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md) | [androidJvm]<br>open override fun [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceRoaming](is-device-roaming.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [isDeviceRoaming](is-device-roaming.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkConnected](is-network-connected.md) | [androidJvm]<br>private fun [isNetworkConnected](is-network-connected.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onAvailable](on-available.md) | [androidJvm]<br>open override fun [onAvailable](on-available.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)) |
| [onBlockedStatusChanged](index.md#1004516195%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onBlockedStatusChanged](index.md#1004516195%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onCapabilitiesChanged](on-capabilities-changed.md) | [androidJvm]<br>open override fun [onCapabilitiesChanged](on-capabilities-changed.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), networkCapabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)) |
| [onLinkPropertiesChanged](on-link-properties-changed.md) | [androidJvm]<br>open override fun [onLinkPropertiesChanged](on-link-properties-changed.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)) |
| [onLosing](on-losing.md) | [androidJvm]<br>open override fun [onLosing](on-losing.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), maxMsToLive: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onLost](on-lost.md) | [androidJvm]<br>open override fun [onLost](on-lost.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)) |
| [onUnavailable](on-unavailable.md) | [androidJvm]<br>open override fun [onUnavailable](on-unavailable.md)() |
| [startListeningForNetworkChanges](start-listening-for-network-changes.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>private fun [startListeningForNetworkChanges](start-listening-for-network-changes.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html)) |
| [stopListeningForNetworkChanges](stop-listening-for-network-changes.md) | [androidJvm]<br>private fun [stopListeningForNetworkChanges](stop-listening-for-network-changes.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html)) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [connectionStatus](connection-status.md) | [androidJvm]<br>private var [connectionStatus](connection-status.md): [NetworkConnectionStatus](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-network-connection-status/index.md)? = null |
| [connectivityManager](connectivity-manager.md) | [androidJvm]<br>private val [connectivityManager](connectivity-manager.md): [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html) |
| [logger](logger.md) | [androidJvm]<br>private val [logger](logger.md): [WisefyLogger](../../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)? |
| [sdkUtil](sdk-util.md) | [androidJvm]<br>private val [sdkUtil](sdk-util.md): [SdkUtil](../../com.isupatches.android.wisefy.util/-sdk-util/index.md) |
| [wifiManager](wifi-manager.md) | [androidJvm]<br>private val [wifiManager](wifi-manager.md): [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html) |