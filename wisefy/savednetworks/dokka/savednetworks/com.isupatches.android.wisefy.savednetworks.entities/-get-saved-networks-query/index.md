//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[GetSavedNetworksQuery](index.md)

# GetSavedNetworksQuery

sealed class [GetSavedNetworksQuery](index.md)

A data representation of a request to get all of the saved networks on the device.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [All](-all/index.md) |
| [BySSID](-by-s-s-i-d/index.md) |
| [ByBSSID](-by-b-s-s-i-d/index.md) |

## Types

| Name | Summary |
|---|---|
| [All](-all/index.md) | [androidJvm]<br>object [All](-all/index.md) : [GetSavedNetworksQuery](index.md)<br>A data representation of a query to get all saved networks. |
| [ByBSSID](-by-b-s-s-i-d/index.md) | [androidJvm]<br>data class [ByBSSID](-by-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [GetSavedNetworksQuery](index.md)<br>A data representation of a query to get saved networks matching a given BSSID. |
| [BySSID](-by-s-s-i-d/index.md) | [androidJvm]<br>data class [BySSID](-by-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [GetSavedNetworksQuery](index.md)<br>A data representation of a query to get saved networks matching a given SSID. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
