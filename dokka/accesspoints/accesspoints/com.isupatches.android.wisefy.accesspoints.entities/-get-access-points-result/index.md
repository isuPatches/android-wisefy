//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetAccessPointsResult](index.md)

# GetAccessPointsResult

[androidJvm]\
sealed class [GetAccessPointsResult](index.md)

A set of classes and objects that represent a result while querying for access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [AccessPoints](-access-points/index.md) | [androidJvm]<br>data class [AccessPoints](-access-points/index.md)(val value: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../-access-point-data/index.md)&gt;) : [GetAccessPointsResult](index.md)<br>A representation for when there are one or more access points matching the [GetAccessPointsQuery](../-get-access-points-query/index.md). |
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetAccessPointsResult](index.md)<br>A representation for when there are no access points matching the [GetAccessPointsQuery](../-get-access-points-query/index.md). |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [AccessPoints](-access-points/index.md) |
