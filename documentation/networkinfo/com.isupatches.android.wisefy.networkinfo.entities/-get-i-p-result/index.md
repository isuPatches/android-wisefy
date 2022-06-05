//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[GetIPResult](index.md)

# GetIPResult

[androidJvm]\
sealed class [GetIPResult](index.md)

A set of classes and objects that are used to represent results for getting the device's IP address.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetIPResult](index.md)<br>A data representation for when there is no current IP address for the device. |
| [IPAddress](-i-p-address/index.md) | [androidJvm]<br>data class [IPAddress](-i-p-address/index.md)(val data: [IPData](../-i-p-data/index.md)) : [GetIPResult](index.md)<br>A data representation for when there is a success retrieving the device's current IP address. |

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
| [IPAddress](-i-p-address/index.md) |
