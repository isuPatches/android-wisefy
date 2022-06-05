//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[SearchForSSIDsResult](index.md)

# SearchForSSIDsResult

[androidJvm]\
sealed class [SearchForSSIDsResult](index.md)

A set of classes and objects that are used to represent a result when searching for a nearby SSIDs.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [SearchForSSIDsResult](index.md)<br>A data representation for when there are no nearby access points matching the SSID. |
| [SSIDs](-s-s-i-ds/index.md) | [androidJvm]<br>data class [SSIDs](-s-s-i-ds/index.md)(val data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SSIDData](../-s-s-i-d-data/index.md)&gt;) : [SearchForSSIDsResult](index.md)<br>A data representation for when there are nearby access points matching the SSID. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [SSIDs](-s-s-i-ds/index.md) |
