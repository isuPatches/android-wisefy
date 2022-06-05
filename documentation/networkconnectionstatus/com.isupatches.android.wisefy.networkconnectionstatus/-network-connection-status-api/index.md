//[networkconnectionstatus](../../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus](../index.md)/[NetworkConnectionStatusApi](index.md)

# NetworkConnectionStatusApi

[androidJvm]\
interface [NetworkConnectionStatusApi](index.md)

A set of synchronous APIs for checking the device's connection status.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#585090901%2FFunctions%2F1246821712) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#585090901%2FFunctions%2F1246821712)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1794629105%2FFunctions%2F1246821712) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1794629105%2FFunctions%2F1246821712)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>An internal API that is used to check if the device is currently connected to a mobile network. |
| [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a Wifi or mobile network. |
| [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md)(request: [IsDeviceConnectedToSSIDRequest](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-to-s-s-i-d-request/index.md)): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a given SSID network. |
| [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a Wifi network. |
| [isDeviceRoaming](is-device-roaming.md) | [androidJvm]<br>abstract fun [isDeviceRoaming](is-device-roaming.md)(): [IsDeviceRoamingResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/index.md)<br>A synchronous API that is used to check if the device is currently roaming. |
| [toString](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1616463040%2FFunctions%2F1246821712) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/-false/index.md#1616463040%2FFunctions%2F1246821712)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [NetworkConnectionStatusApiInternal](../-network-connection-status-api-internal/index.md) |
