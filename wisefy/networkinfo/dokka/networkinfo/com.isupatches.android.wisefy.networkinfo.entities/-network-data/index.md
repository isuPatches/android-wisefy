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
| [NetworkData](-network-data.md) | [androidJvm]<br>constructor(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?, connectionInfo: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?, capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?) |

## Properties

| Name | Summary |
|---|---|
| [capabilities](capabilities.md) | [androidJvm]<br>val [capabilities](capabilities.md): [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?<br>The raw value of he network capabilities from the Android OS |
| [connectionInfo](connection-info.md) | [androidJvm]<br>val [connectionInfo](connection-info.md): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?<br>The raw value of the current connection info from the Android OS |
| [linkProperties](link-properties.md) | [androidJvm]<br>val [linkProperties](link-properties.md): [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?<br>The raw value of the link properties from the Android OS |
| [network](network.md) | [androidJvm]<br>val [network](network.md): [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?<br>The raw value of the current network from the Android OS |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)? |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)? |
| [component4](component4.md) | [androidJvm]<br>operator fun [component4](component4.md)(): [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)? |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?, connectionInfo: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?, capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?): [NetworkData](index.md) |
| [equals](index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator override fun [equals](index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [hashCode](index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [toString](index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
