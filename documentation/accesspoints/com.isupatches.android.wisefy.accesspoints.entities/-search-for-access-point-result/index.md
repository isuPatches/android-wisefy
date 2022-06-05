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

## Functions

| Name | Summary |
|---|---|
| [equals](../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [AccessPoint](-access-point/index.md) |
