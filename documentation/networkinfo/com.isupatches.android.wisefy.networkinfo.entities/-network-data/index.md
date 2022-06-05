//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[NetworkData](index.md)

# NetworkData

[androidJvm]\
data class [NetworkData](index.md)(val value: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html))

A data representation of the current network from Android OS level returns.

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [NetworkData](-network-data.md) | [androidJvm]<br>fun [NetworkData](-network-data.md)(value: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)): [NetworkData](index.md) |
| [equals](../-network-info-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator override fun [equals](../-network-info-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-network-info-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [hashCode](../-network-info-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-network-info-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [toString](../-network-info-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)<br>The raw value of the current network from the Android OS |
