//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[GetSavedNetworksResult](index.md)

# GetSavedNetworksResult

[androidJvm]\
sealed class [GetSavedNetworksResult](index.md)

A set of classes and objects that are used to represent a result while getting saved networks on the device.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetSavedNetworksResult](index.md)<br>A data representation of no networks being saved on the device. |
| [SavedNetworks](-saved-networks/index.md) | [androidJvm]<br>data class [SavedNetworks](-saved-networks/index.md)(val data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../-saved-network-data/index.md)&gt;) : [GetSavedNetworksResult](index.md)<br>A data representation of a success retrieving saved networks on the device based on Android OS level returns. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [SavedNetworks](-saved-networks/index.md) |
