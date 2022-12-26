//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetAccessPointsQuery](index.md)

# GetAccessPointsQuery

[androidJvm]\
sealed class [GetAccessPointsQuery](index.md)

A set of classes or objects that represent a query for access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [All](-all/index.md) | [androidJvm]<br>data class [All](-all/index.md)(val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetAccessPointsQuery](index.md)<br>A representation of a query to get all nearby access points. |
| [ByBSSID](-by-b-s-s-i-d/index.md) | [androidJvm]<br>data class [ByBSSID](-by-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetAccessPointsQuery](index.md)<br>A representation of a query to get access points filtered by a regex for their BSSID. |
| [BySSID](-by-s-s-i-d/index.md) | [androidJvm]<br>data class [BySSID](-by-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetAccessPointsQuery](index.md)<br>A representation of a query to get access points filtered by a regex for their SSID. |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>open val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |

## Inheritors

| Name |
|---|
| [All](-all/index.md) |
| [BySSID](-by-s-s-i-d/index.md) |
| [ByBSSID](-by-b-s-s-i-d/index.md) |
