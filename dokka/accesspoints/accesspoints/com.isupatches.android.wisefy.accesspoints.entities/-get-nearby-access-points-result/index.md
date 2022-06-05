//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetNearbyAccessPointsResult](index.md)

# GetNearbyAccessPointsResult

[androidJvm]\
sealed class [GetNearbyAccessPointsResult](index.md)

A set of classes and objects that are used to represent a result when getting nearby access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [AccessPoints](-access-points/index.md) | [androidJvm]<br>data class [AccessPoints](-access-points/index.md)(val data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../-access-point-data/index.md)&gt;) : [GetNearbyAccessPointsResult](index.md)<br>A data representation for when there is one or more nearby access points. |
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetNearbyAccessPointsResult](index.md)<br>A data representation for when there are no nearby access points. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [AccessPoints](-access-points/index.md) |
