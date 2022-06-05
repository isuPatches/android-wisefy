//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetNearbyAccessPointsRequest](index.md)

# GetNearbyAccessPointsRequest

[androidJvm]\
data class [GetNearbyAccessPointsRequest](index.md)(val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)

A data representation of a request to get all nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [GetNearbyAccessPointsRequest](-get-nearby-access-points-request.md) | [androidJvm]<br>fun [GetNearbyAccessPointsRequest](-get-nearby-access-points-request.md)(filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |
