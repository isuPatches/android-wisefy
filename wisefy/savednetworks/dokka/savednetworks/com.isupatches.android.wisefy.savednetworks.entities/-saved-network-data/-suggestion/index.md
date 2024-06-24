//[savednetworks](../../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../../index.md)/[SavedNetworkData](../index.md)/[Suggestion](index.md)

# Suggestion

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

data class [Suggestion](index.md)(val rawValue: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)) : [SavedNetworkData](../index.md)

A data representation of a saved network suggestion starting at Android Q.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [SavedNetworkData](../index.md) |

## Constructors

| | |
|---|---|
| [Suggestion](-suggestion.md) | [androidJvm]<br>constructor(rawValue: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)) |

## Properties

| Name | Summary |
|---|---|
| [rawValue](raw-value.md) | [androidJvm]<br>val [rawValue](raw-value.md): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)<br>The raw value of the saved network as a suggestion |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(rawValue: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)): [SavedNetworkData.Suggestion](index.md) |
| [equals](index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator override fun [equals](index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [hashCode](index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [toString](index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
