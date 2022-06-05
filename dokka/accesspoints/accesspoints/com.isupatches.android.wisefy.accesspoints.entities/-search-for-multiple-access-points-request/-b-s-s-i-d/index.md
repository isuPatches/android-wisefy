//[accesspoints](../../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../../index.md)/[SearchForMultipleAccessPointsRequest](../index.md)/[BSSID](index.md)

# BSSID

[androidJvm]\
data class [BSSID](index.md)(val regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) : [SearchForMultipleAccessPointsRequest](../index.md)

A data representation of a request to search for multiple access points by BSSID.

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
| [BSSID](-b-s-s-i-d.md) | [androidJvm]<br>fun [BSSID](-b-s-s-i-d.md)(regexForBSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Properties

| Name | Summary |
|---|---|
| [filterDuplicates](filter-duplicates.md) | [androidJvm]<br>open override val [filterDuplicates](filter-duplicates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Whether nearby access points with the same SSID but lower RSSI levels should be excluded |
| [regexForBSSID](regex-for-b-s-s-i-d.md) | [androidJvm]<br>val [regexForBSSID](regex-for-b-s-s-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The regex to use when matching the BSSID |
