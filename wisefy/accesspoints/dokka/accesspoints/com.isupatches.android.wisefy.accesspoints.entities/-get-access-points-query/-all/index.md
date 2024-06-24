//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[GetAccessPointsQuery](../index.md)/[All](index.md)

# All

data class [All](index.md)(val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetAccessPointsQuery](../index.md)

A representation of a query to get all nearby access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [GetAccessPointsQuery](../index.md) |

## Constructors

| | |
|---|---|
| [All](-all.md) | [androidJvm]<br>constructor(filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>open override val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [GetAccessPointsQuery.All](index.md) |
| [equals](../../-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator override fun [equals](../../-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [hashCode](../../-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [toString](../../-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
