//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[SearchForSavedNetworksRequest](index.md)

# SearchForSavedNetworksRequest

[androidJvm]\
sealed class [SearchForSavedNetworksRequest](index.md)

A set of classes and objects that are used to represent requests to search for saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [SearchForSavedNetworksRequest](index.md)<br>A data representation of a request to search for saved networks by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [SearchForSavedNetworksRequest](index.md)<br>A data representation of a request to search for saved networks by SSID. |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
