//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[GetSavedNetworksResult](index.md)

# GetSavedNetworksResult

sealed class [GetSavedNetworksResult](index.md)

A set of classes and objects that are used to represent a result while getting all of the saved networks on the device.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [Empty](-empty/index.md) |
| [SavedNetworks](-saved-networks/index.md) |

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetSavedNetworksResult](index.md)<br>A data representation of no networks being saved on the device. |
| [SavedNetworks](-saved-networks/index.md) | [androidJvm]<br>data class [SavedNetworks](-saved-networks/index.md)(val value: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../-saved-network-data/index.md)&gt;) : [GetSavedNetworksResult](index.md)<br>A data representation of a success retrieving saved networks on the device. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
