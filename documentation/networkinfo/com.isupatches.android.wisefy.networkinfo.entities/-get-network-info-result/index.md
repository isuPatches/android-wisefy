//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[GetNetworkInfoResult](index.md)

# GetNetworkInfoResult

[androidJvm]\
sealed class [GetNetworkInfoResult](index.md)

A set of classes and objects that are used to represent results for getting information for a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetNetworkInfoResult](index.md)<br>A data representation for when there is no information for a network. |
| [NetworkInfo](-network-info/index.md) | [androidJvm]<br>data class [NetworkInfo](-network-info/index.md)(val data: [NetworkInfoData](../-network-info-data/index.md)) : [GetNetworkInfoResult](index.md)<br>A data representation for when there is a success retrieving the device's current IP address. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-network-info-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator fun [equals](../-network-info-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-network-info-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open fun [hashCode](../-network-info-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-network-info-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../-network-info-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [NetworkInfo](-network-info/index.md) |
