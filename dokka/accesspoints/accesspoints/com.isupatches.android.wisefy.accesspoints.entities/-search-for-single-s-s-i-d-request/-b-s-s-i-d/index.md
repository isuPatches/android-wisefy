//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[SearchForSingleSSIDRequest](../index.md)/[BSSID](index.md)

# BSSID

[androidJvm]\
data class [BSSID](index.md)(val regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [SearchForSingleSSIDRequest](../index.md)

A data representation of a request to search for a single BSSID by regex.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [BSSID](-b-s-s-i-d.md) | [androidJvm]<br>fun [BSSID](-b-s-s-i-d.md)(regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [regexForBSSID](regex-for-b-s-s-i-d.md) | [androidJvm]<br>val [regexForBSSID](regex-for-b-s-s-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The regex to use when matching the BSSID |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open override val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for an SSID to appear |
