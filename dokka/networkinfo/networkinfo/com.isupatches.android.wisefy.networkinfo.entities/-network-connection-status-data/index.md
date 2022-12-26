//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[NetworkConnectionStatusData](index.md)

# NetworkConnectionStatusData

[androidJvm]\
data class [NetworkConnectionStatusData](index.md)(val isConnected: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val isConnectedToMobileNetwork: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val isConnectedToWifiNetwork: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val isRoaming: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val ssidOfNetworkConnectedTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val bssidOfNetworkConnectedTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val ip: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)

A data representation of the current network connection status from Android OS level returns.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Constructors

| | |
|---|---|
| [NetworkConnectionStatusData](-network-connection-status-data.md) | [androidJvm]<br>fun [NetworkConnectionStatusData](-network-connection-status-data.md)(isConnected: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), isConnectedToMobileNetwork: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), isConnectedToWifiNetwork: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), isRoaming: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), ssidOfNetworkConnectedTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, bssidOfNetworkConnectedTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, ip: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |

## Properties

| Name | Summary |
|---|---|
| [bssidOfNetworkConnectedTo](bssid-of-network-connected-to.md) | [androidJvm]<br>val [bssidOfNetworkConnectedTo](bssid-of-network-connected-to.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The current BSSID that the device is connected to, or null |
| [ip](ip.md) | [androidJvm]<br>val [ip](ip.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The IP address of the device from it's current network, or null |
| [isConnected](is-connected.md) | [androidJvm]<br>val [isConnected](is-connected.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If the device is connected to a mobile or wifi network |
| [isConnectedToMobileNetwork](is-connected-to-mobile-network.md) | [androidJvm]<br>val [isConnectedToMobileNetwork](is-connected-to-mobile-network.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If the device is connected to a mobile network |
| [isConnectedToWifiNetwork](is-connected-to-wifi-network.md) | [androidJvm]<br>val [isConnectedToWifiNetwork](is-connected-to-wifi-network.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If the device is connected to a wifi network |
| [isRoaming](is-roaming.md) | [androidJvm]<br>val [isRoaming](is-roaming.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If the device is roaming |
| [ssidOfNetworkConnectedTo](ssid-of-network-connected-to.md) | [androidJvm]<br>val [ssidOfNetworkConnectedTo](ssid-of-network-connected-to.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The current SSID that the device is connected to, or null |
