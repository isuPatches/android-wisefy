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

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [SavedNetwork](-saved-network/index.md) |
