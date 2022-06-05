//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[GetRSSIRequest](../index.md)/[SSID](index.md)

# SSID

[androidJvm]\
data class [SSID](index.md)(val regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [GetRSSIRequest](../index.md)

A data representation to retrieve the RRSI level of a nearby access point by SSID.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [SSID](-s-s-i-d.md) | [androidJvm]<br>fun [SSID](-s-s-i-d.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [regexForSSID](regex-for-s-s-i-d.md) | [androidJvm]<br>val [regexForSSID](regex-for-s-s-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The regex to match for the access point's SSID |
| [takeHighest](take-highest.md) | [androidJvm]<br>open override val [takeHighest](take-highest.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Whether the request returns the first RSSI value or the highest RSSI value for the network in the case of one that has duplicate access points |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open override val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>How long the request should wait to find the network |
