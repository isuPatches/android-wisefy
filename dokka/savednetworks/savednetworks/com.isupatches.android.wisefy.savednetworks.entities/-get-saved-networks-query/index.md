//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../index.md)/[GetSavedNetworksQuery](index.md)

# GetSavedNetworksQuery

[androidJvm]\
sealed class [GetSavedNetworksQuery](index.md)

A data representation of a request to get all of the saved networks on the device.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [All](-all/index.md) | [androidJvm]<br>object [All](-all/index.md) : [GetSavedNetworksQuery](index.md)<br>A data representation of a query to get all saved networks. |
| [ByBSSID](-by-b-s-s-i-d/index.md) | [androidJvm]<br>data class [ByBSSID](-by-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [GetSavedNetworksQuery](index.md)<br>A data representation of a query to get saved networks matching a given BSSID. |
| [BySSID](-by-s-s-i-d/index.md) | [androidJvm]<br>data class [BySSID](-by-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [GetSavedNetworksQuery](index.md)<br>A data representation of a query to get saved networks matching a given SSID. |

## Inheritors

| Name |
|---|
| [All](-all/index.md) |
| [BySSID](-by-s-s-i-d/index.md) |
| [ByBSSID](-by-b-s-s-i-d/index.md) |
