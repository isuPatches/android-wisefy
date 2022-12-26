//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[NetworkData](index.md)

# NetworkData

[androidJvm]\
data class [NetworkData](index.md)(val network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?, val connectionInfo: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?, val capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, val linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?)

A data representation of the current network from Android OS level returns.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Constructors

| | |
|---|---|
| [NetworkData](-network-data.md) | [androidJvm]<br>fun [NetworkData](-network-data.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?, connectionInfo: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?, capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?) |

## Properties

| Name | Summary |
|---|---|
| [capabilities](capabilities.md) | [androidJvm]<br>val [capabilities](capabilities.md): [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?<br>The raw value of he network capabilities from the Android OS |
| [connectionInfo](connection-info.md) | [androidJvm]<br>val [connectionInfo](connection-info.md): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?<br>The raw value of the current connection info from the Android OS |
| [linkProperties](link-properties.md) | [androidJvm]<br>val [linkProperties](link-properties.md): [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?<br>The raw value of the link properties from the Android OS |
| [network](network.md) | [androidJvm]<br>val [network](network.md): [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?<br>The raw value of the current network from the Android OS |
