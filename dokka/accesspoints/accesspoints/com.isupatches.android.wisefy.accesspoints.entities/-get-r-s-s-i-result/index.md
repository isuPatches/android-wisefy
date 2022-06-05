//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetRSSIResult](index.md)

# GetRSSIResult

[androidJvm]\
sealed class [GetRSSIResult](index.md)

A set of classes and objects that are used to represent a result when getting the RSSI of a nearby access point.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetRSSIResult](index.md)<br>A data representation for when there is no matching nearby access point to retrieve the RSSI. |
| [RSSI](-r-s-s-i/index.md) | [androidJvm]<br>data class [RSSI](-r-s-s-i/index.md)(val data: [RSSIData](../-r-s-s-i-data/index.md)) : [GetRSSIResult](index.md)<br>A data representation for when there is a matching nearby access point to retrieve the RSSI. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [RSSI](-r-s-s-i/index.md) |
