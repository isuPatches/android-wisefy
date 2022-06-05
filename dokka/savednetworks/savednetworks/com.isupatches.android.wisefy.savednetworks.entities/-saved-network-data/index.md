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

## Inheritors

| Name |
|---|
| [Configuration](-configuration/index.md) |
| [Suggestion](-suggestion/index.md) |
