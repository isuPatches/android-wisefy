//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[SavedNetworkData](index.md)

# SavedNetworkData

sealed class [SavedNetworkData](index.md)

A set of classes and objects that are used to represent a saved network on the device.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [Configuration](-configuration/index.md) |
| [Suggestion](-suggestion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Configuration](-configuration/index.md) | [androidJvm]<br>data class [Configuration](-configuration/index.md)(val rawValue: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)) : [SavedNetworkData](index.md)<br>A data representation of a saved network configuration prior to Android Q. |
| [Suggestion](-suggestion/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>data class [Suggestion](-suggestion/index.md)(val rawValue: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)) : [SavedNetworkData](index.md)<br>A data representation of a saved network suggestion starting at Android Q. |

## Functions

| Name | Summary |
|---|---|
| [equals](-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
