//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[GetRSSIRequest](index.md)

# GetRSSIRequest

[androidJvm]\
sealed class [GetRSSIRequest](index.md)

A set of classes and objects that are used to represent requests to retrieve the RRSI level of a nearby access point.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetRSSIRequest](index.md)<br>A data representation to retrieve the RRSI level of a nearby access point by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetRSSIRequest](index.md)<br>A data representation to retrieve the RRSI level of a nearby access point by SSID. |

## Properties

| Name | Summary |
|---|---|
| [takeHighest](take-highest.md) | [androidJvm]<br>open val [takeHighest](take-highest.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the request returns the first RSSI value or the highest RSSI value for the network in the case of one that has duplicate access points |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>How long the request should wait to find the network |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
