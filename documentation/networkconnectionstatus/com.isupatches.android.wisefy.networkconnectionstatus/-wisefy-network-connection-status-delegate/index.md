//[networkconnectionstatus](../../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus](../index.md)/[WisefyNetworkConnectionStatusDelegate](index.md)

# WisefyNetworkConnectionStatusDelegate

[androidJvm]\
class [WisefyNetworkConnectionStatusDelegate](index.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [NetworkConnectionStatusDelegate](../-network-connection-status-delegate/index.md)

An internal Wisefy delegate for checking the device's connection status and if it meets certain criteria.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate](../-network-connection-status-delegate/index.md) |  |
| com.isupatches.android.wisefy.core.util.SdkUtil |  |
| com.isupatches.android.wisefy.core.logging.WisefyLogger |  |

## Parameters

androidJvm

| | |
|---|---|
| connectivityManager | The ConnectivityManager instance to use |
| logger | The logger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefyNetworkConnectionStatusDelegate](-wisefy-network-connection-status-delegate.md) | [androidJvm]<br>fun [WisefyNetworkConnectionStatusDelegate](-wisefy-network-connection-status-delegate.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [attachNetworkWatcher](attach-network-watcher.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [attachNetworkWatcher](attach-network-watcher.md)()<br>A synchronous internal API that is used to add a network watcher. |
| [detachNetworkWatcher](detach-network-watcher.md) | [androidJvm]<br>open override fun [detachNetworkWatcher](detach-network-watcher.md)()<br>A synchronous internal API that is used to remove a network watcher. |
| [equals](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#585090901%2FFunctions%2F1246821712) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#585090901%2FFunctions%2F1246821712)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1794629105%2FFunctions%2F1246821712) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1794629105%2FFunctions%2F1246821712)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>An internal API that is used to check if the device is currently connected to a mobile network. |
| [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md) | [androidJvm]<br>open override fun [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a Wifi or mobile network. |
| [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md)(request: [IsDeviceConnectedToSSIDRequest](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-to-s-s-i-d-request/index.md)): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a given SSID network. |
| [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a Wifi network. |
| [isDeviceRoaming](is-device-roaming.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [isDeviceRoaming](is-device-roaming.md)(): [IsDeviceRoamingResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/index.md)<br>A synchronous API that is used to check if the device is currently roaming. |
| [toString](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1616463040%2FFunctions%2F1246821712) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1616463040%2FFunctions%2F1246821712)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
