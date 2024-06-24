//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[GetAccessPointsQuery](../index.md)/[ByBSSID](index.md)

# ByBSSID

data class [ByBSSID](index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetAccessPointsQuery](../index.md)

A representation of a query to get access points filtered by a regex for their BSSID.

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
| [ByBSSID](-by-b-s-s-i-d.md) | [androidJvm]<br>constructor(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>open override val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |
| [regex](regex.md) | [androidJvm]<br>val [regex](regex.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The regex to use when matching the BSSID |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null<br>The optional timeout in milliseconds to wait for matching access points |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [GetAccessPointsQuery.ByBSSID](index.md) |
| [equals](../../-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator override fun [equals](../../-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [hashCode](../../-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [toString](../../-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
