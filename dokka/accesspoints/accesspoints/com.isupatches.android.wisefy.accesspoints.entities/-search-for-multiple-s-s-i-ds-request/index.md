//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[SearchForMultipleSSIDsRequest](index.md)

# SearchForMultipleSSIDsRequest

[androidJvm]\
sealed class [SearchForMultipleSSIDsRequest](index.md)

A set of classes and objects that are used to represent requests to search for multiple SSIDs.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [SearchForMultipleSSIDsRequest](index.md)<br>A data representation of a request to search for multiple BSSIDs by regex. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [SearchForMultipleSSIDsRequest](index.md)<br>A data representation of a request to search for multiple SSIDs by regex. |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
