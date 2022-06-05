//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[SearchForSSIDResult](index.md)

# SearchForSSIDResult

[androidJvm]\
sealed class [SearchForSSIDResult](index.md)

A set of classes and objects that are used to represent a result when searching for a nearby SSID.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [SearchForSSIDResult](index.md)<br>A data representation for when there is not a nearby access points matching the SSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val data: [SSIDData](../-s-s-i-d-data/index.md)) : [SearchForSSIDResult](index.md)<br>A data representation for when there is a nearby access point matching the SSID. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [SSID](-s-s-i-d/index.md) |
