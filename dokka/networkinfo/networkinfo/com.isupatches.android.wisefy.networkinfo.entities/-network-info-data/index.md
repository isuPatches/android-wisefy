//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[NetworkInfoData](index.md)

# NetworkInfoData

[androidJvm]\
data class [NetworkInfoData](index.md)(val capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, val linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?)

A data representation of the current network info from Android OS level returns.

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [NetworkInfoData](-network-info-data.md) | [androidJvm]<br>fun [NetworkInfoData](-network-info-data.md)(capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?) |

## Properties

| Name | Summary |
|---|---|
| [capabilities](capabilities.md) | [androidJvm]<br>val [capabilities](capabilities.md): [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?<br>The raw value of the network capabilities from the Android OS |
| [linkProperties](link-properties.md) | [androidJvm]<br>val [linkProperties](link-properties.md): [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?<br>The raw value of the network's link properties from the Android OS |
