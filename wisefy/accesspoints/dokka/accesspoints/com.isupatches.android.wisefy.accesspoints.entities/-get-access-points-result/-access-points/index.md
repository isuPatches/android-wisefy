//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[GetAccessPointsResult](../index.md)/[AccessPoints](index.md)

# AccessPoints

data class [AccessPoints](index.md)(val value: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../-access-point-data/index.md)&gt;) : [GetAccessPointsResult](../index.md)

A representation for when there are one or more access points matching the [GetAccessPointsQuery](../../-get-access-points-query/index.md).

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AccessPointData](../../-access-point-data/index.md) |
| [GetAccessPointsResult](../index.md) |

## Constructors

| | |
|---|---|
| [AccessPoints](-access-points.md) | [androidJvm]<br>constructor(value: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../-access-point-data/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../-access-point-data/index.md)&gt;<br>This list of matching access points |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../-access-point-data/index.md)&gt; |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../-access-point-data/index.md)&gt;): [GetAccessPointsResult.AccessPoints](index.md) |
| [equals](index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator override fun [equals](index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [hashCode](index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [toString](index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
