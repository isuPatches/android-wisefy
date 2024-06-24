//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[IsNetworkSavedQuery](index.md)

# IsNetworkSavedQuery

sealed class [IsNetworkSavedQuery](index.md)

A set of classes and objects that are used to represent requests to see if a network is saved.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [IsNetworkSavedQuery](index.md)<br>A data representation to check if a network is saved by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [IsNetworkSavedQuery](index.md)<br>A data representation to check if a network is saved by SSID. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
