//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[SearchForSavedNetworksResult](index.md)

# SearchForSavedNetworksResult

[androidJvm]\
sealed class [SearchForSavedNetworksResult](index.md)

A set of classes and objects that are used to represent a result when searching for saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [SearchForSavedNetworksResult](index.md)<br>A data representation for when there are no matching saved networks. |
| [SavedNetworks](-saved-networks/index.md) | [androidJvm]<br>data class [SavedNetworks](-saved-networks/index.md)(val data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../-saved-network-data/index.md)&gt;) : [SearchForSavedNetworksResult](index.md)<br>A data representation for when there are matching saved networks. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [SavedNetworks](-saved-networks/index.md) |
