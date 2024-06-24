//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetAccessPointsResult](index.md)

# GetAccessPointsResult

sealed class [GetAccessPointsResult](index.md)

A set of classes and objects that represent a result while querying for access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [Empty](-empty/index.md) |
| [AccessPoints](-access-points/index.md) |

## Types

| Name | Summary |
|---|---|
| [AccessPoints](-access-points/index.md) | [androidJvm]<br>data class [AccessPoints](-access-points/index.md)(val value: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../-access-point-data/index.md)&gt;) : [GetAccessPointsResult](index.md)<br>A representation for when there are one or more access points matching the [GetAccessPointsQuery](../-get-access-points-query/index.md). |
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetAccessPointsResult](index.md)<br>A representation for when there are no access points matching the [GetAccessPointsQuery](../-get-access-points-query/index.md). |

## Functions

| Name | Summary |
|---|---|
| [equals](-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
