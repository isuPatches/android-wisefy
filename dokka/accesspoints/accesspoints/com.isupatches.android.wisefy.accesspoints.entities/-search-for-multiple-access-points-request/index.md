//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[SearchForMultipleAccessPointsRequest](index.md)

# SearchForMultipleAccessPointsRequest

[androidJvm]\
sealed class [SearchForMultipleAccessPointsRequest](index.md)

A set of classes and objects that are used to represent requests to search for multiple access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [SearchForMultipleAccessPointsRequest](index.md)<br>A data representation of a request to search for multiple access points by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [SearchForMultipleAccessPointsRequest](index.md)<br>A data representation of a request to search for multiple access points by SSID. |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>open val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
