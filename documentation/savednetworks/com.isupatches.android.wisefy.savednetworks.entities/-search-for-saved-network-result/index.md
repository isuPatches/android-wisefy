//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[SearchForSavedNetworkResult](index.md)

# SearchForSavedNetworkResult

[androidJvm]\
sealed class [SearchForSavedNetworkResult](index.md)

A set of classes and objects that are used to represent a result when searching for a saved network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [SearchForSavedNetworkResult](index.md)<br>A data representation for when there is no matching saved network. |
| [SavedNetwork](-saved-network/index.md) | [androidJvm]<br>data class [SavedNetwork](-saved-network/index.md)(val data: [SavedNetworkData](../-saved-network-data/index.md)) : [SearchForSavedNetworkResult](index.md)<br>A data representation for when there is a matching saved network. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [SavedNetwork](-saved-network/index.md) |
