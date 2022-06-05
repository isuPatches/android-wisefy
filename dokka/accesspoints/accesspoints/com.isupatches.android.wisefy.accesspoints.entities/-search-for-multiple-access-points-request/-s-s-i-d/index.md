//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[SearchForMultipleAccessPointsRequest](../index.md)/[SSID](index.md)

# SSID

[androidJvm]\
data class [SSID](index.md)(val regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [SearchForMultipleAccessPointsRequest](../index.md)

A data representation of a request to search for multiple access points by SSID.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [SSID](-s-s-i-d.md) | [androidJvm]<br>fun [SSID](-s-s-i-d.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>open override val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |
| [regexForSSID](regex-for-s-s-i-d.md) | [androidJvm]<br>val [regexForSSID](regex-for-s-s-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The regex to use when matching the SSID |
