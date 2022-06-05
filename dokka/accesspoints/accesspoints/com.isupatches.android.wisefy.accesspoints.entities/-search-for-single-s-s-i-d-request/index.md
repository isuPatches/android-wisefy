//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[SearchForSingleSSIDRequest](index.md)

# SearchForSingleSSIDRequest

[androidJvm]\
sealed class [SearchForSingleSSIDRequest](index.md)

A set of classes and objects that are used to represent requests to search for a single SSID.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [SearchForSingleSSIDRequest](index.md)<br>A data representation of a request to search for a single BSSID by regex. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [SearchForSingleSSIDRequest](index.md)<br>A data representation of a request to search for a single SSID by regex. |

## Properties

| Name | Summary |
|---|---|
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for an SSID to appear |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
