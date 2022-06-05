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
| [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileNetwork](is-device-connected-to-mobile-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>An internal API that is used to check if the device is currently connected to a mobile network. |
| [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileOrWifiNetwork](is-device-connected-to-mobile-or-wifi-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a Wifi or mobile network. |
| [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToSSID](is-device-connected-to-s-s-i-d.md)(request: [IsDeviceConnectedToSSIDRequest](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-to-s-s-i-d-request/index.md)): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a given SSID network. |
| [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToWifiNetwork](is-device-connected-to-wifi-network.md)(): [IsDeviceConnectedResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-connected-result/index.md)<br>A synchronous API that is used to check if the device is currently connected to a Wifi network. |
| [isDeviceRoaming](is-device-roaming.md) | [androidJvm]<br>abstract fun [isDeviceRoaming](is-device-roaming.md)(): [IsDeviceRoamingResult](../../com.isupatches.android.wisefy.networkconnectionstatus.entities/-is-device-roaming-result/index.md)<br>A synchronous API that is used to check if the device is currently roaming. |

## Inheritors

| Name |
|---|
| [NetworkConnectionStatusApiInternal](../-network-connection-status-api-internal/index.md) |
