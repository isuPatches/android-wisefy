//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[SearchForAccessPointResult](index.md)

# SearchForAccessPointResult

[androidJvm]\
sealed class [SearchForAccessPointResult](index.md)

A set of classes and objects that are used to represent a result when searching for a nearby access point.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [AccessPoint](-access-point/index.md) | [androidJvm]<br>data class [AccessPoint](-access-point/index.md)(val data: [AccessPointData](../-access-point-data/index.md)) : [SearchForAccessPointResult](index.md)<br>A data representation for when there is a matching nearby access point. |
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [SearchForAccessPointResult](index.md)<br>A data representation for when there is no matching nearby access point. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [AccessPoint](-access-point/index.md) |
