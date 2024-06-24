//[savednetworks](../../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../../index.md)/[GetSavedNetworksQuery](../index.md)/[BySSID](index.md)

# BySSID

data class [BySSID](index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [GetSavedNetworksQuery](../index.md)

A data representation of a query to get saved networks matching a given SSID.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [GetSavedNetworksQuery](../index.md) |

## Constructors

| | |
|---|---|
| [BySSID](-by-s-s-i-d.md) | [androidJvm]<br>constructor(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [regex](regex.md) | [androidJvm]<br>val [regex](regex.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The regex to use when finding the network by SSID |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [GetSavedNetworksQuery.BySSID](index.md) |
| [equals](../../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator override fun [equals](../../-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [hashCode](../../-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [toString](../../-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
