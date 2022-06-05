//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[SearchForAccessPointResult](../index.md)/[AccessPoint](index.md)

# AccessPoint

[androidJvm]\
data class [AccessPoint](index.md)(val data: [AccessPointData](../../-access-point-data/index.md)) : [SearchForAccessPointResult](../index.md)

A data representation for when there is a matching nearby access point.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.AccessPointData](../../-access-point-data/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointResult](../index.md) |  |

## Constructors

| | |
|---|---|
| [AccessPoint](-access-point.md) | [androidJvm]<br>fun [AccessPoint](-access-point.md)(data: [AccessPointData](../../-access-point-data/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [AccessPointData](../../-access-point-data/index.md) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(data: [AccessPointData](../../-access-point-data/index.md)): [SearchForAccessPointResult.AccessPoint](index.md) |
| [equals](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator override fun [equals](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [hashCode](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [toString](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): [AccessPointData](../../-access-point-data/index.md)<br>The matching nearby access point |
