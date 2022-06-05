//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[SearchForSavedNetworksRequest](index.md)

# SearchForSavedNetworksRequest

[androidJvm]\
sealed class [SearchForSavedNetworksRequest](index.md)

A set of classes and objects that are used to represent requests to search for saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [SearchForSavedNetworksRequest](index.md)<br>A data representation of a request to search for saved networks by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [SearchForSavedNetworksRequest](index.md)<br>A data representation of a request to search for saved networks by SSID. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
