//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[GetRSSIResult](../index.md)/[RSSI](index.md)

# RSSI

[androidJvm]\
data class [RSSI](index.md)(val data: [RSSIData](../../-r-s-s-i-data/index.md)) : [GetRSSIResult](../index.md)

A data representation for when there is a matching nearby access point to retrieve the RSSI.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetRSSIResult](../index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.RSSIData](../../-r-s-s-i-data/index.md) |  |

## Constructors

| | |
|---|---|
| [RSSI](-r-s-s-i.md) | [androidJvm]<br>fun [RSSI](-r-s-s-i.md)(data: [RSSIData](../../-r-s-s-i-data/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [RSSIData](../../-r-s-s-i-data/index.md) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(data: [RSSIData](../../-r-s-s-i-data/index.md)): [GetRSSIResult.RSSI](index.md) |
| [equals](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator override fun [equals](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [hashCode](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [toString](../../-search-for-single-s-s-i-d-request/-b-s-s-i-d/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): [RSSIData](../../-r-s-s-i-data/index.md)<br>The RSSI value for the nearby access point |
