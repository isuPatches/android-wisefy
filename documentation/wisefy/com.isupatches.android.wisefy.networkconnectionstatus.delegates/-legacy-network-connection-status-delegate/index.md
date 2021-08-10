//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus.delegates](../index.md)/[LegacyNetworkConnectionStatusDelegate](index.md)

# LegacyNetworkConnectionStatusDelegate

[androidJvm]\
internal class [LegacyNetworkConnectionStatusDelegate](index.md)(**connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **sdkUtil**: [SdkUtil](../../com.isupatches.android.wisefy.util/-sdk-util/index.md), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)?, **impl**: [LegacyNetworkConnectionStatusApi](../-legacy-network-connection-status-api/index.md)) : [NetworkConnectionStatusApi](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [attachNetworkWatcher](attach-network-watcher.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [attachNetworkWatcher](attach-network-watcher.md)() |
| [detachNetworkWatcher](detach-network-watcher.md) | [androidJvm]<br>open override fun [detachNetworkWatcher](detach-network-watcher.md)() |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md) | [androidJvm]<br>open override fun [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md) | [androidJvm]<br>open override fun [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceRoaming](is-device-roaming.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [isDeviceRoaming](is-device-roaming.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [impl](impl.md) | [androidJvm]<br>private val [impl](impl.md): [LegacyNetworkConnectionStatusApi](../-legacy-network-connection-status-api/index.md) |
