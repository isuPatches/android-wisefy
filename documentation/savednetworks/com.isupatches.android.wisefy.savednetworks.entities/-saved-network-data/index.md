//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[SavedNetworkData](index.md)

# SavedNetworkData

[androidJvm]\
sealed class [SavedNetworkData](index.md)

A set of classes and objects that are used to represent a saved network on the device.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Configuration](-configuration/index.md) | [androidJvm]<br>data class [Configuration](-configuration/index.md)(val value: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)) : [SavedNetworkData](index.md)<br>A data representation of a network configuration from Android OS level returns. |
| [Suggestion](-suggestion/index.md) | [androidJvm]<br>data class [Suggestion](-suggestion/index.md)(val value: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)) : [SavedNetworkData](index.md)<br>A data representation of a network suggestion from Android OS level returns. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Configuration](-configuration/index.md) |
| [Suggestion](-suggestion/index.md) |
