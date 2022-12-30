//[savednetworks](../../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../../index.md)/[SavedNetworkData](../index.md)/[Suggestion](index.md)

# Suggestion

[androidJvm]\
@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

data class [Suggestion](index.md)(val rawValue: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)) : [SavedNetworkData](../index.md)

A data representation of a saved network suggestion starting at Android Q.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [SavedNetworkData](../index.md) |

## Constructors

| | |
|---|---|
| [Suggestion](-suggestion.md) | [androidJvm]<br>fun [Suggestion](-suggestion.md)(rawValue: [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)) |

## Properties

| Name | Summary |
|---|---|
| [rawValue](raw-value.md) | [androidJvm]<br>val [rawValue](raw-value.md): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)<br>The raw value of the saved network as a suggestion |
