//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)

# AccessPointsApi

[androidJvm]\
interface [AccessPointsApi](index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getNearbyAccessPoints](get-nearby-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [getNearbyAccessPoints](get-nearby-access-points.md)(filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)> |
| [getRSSI](get-r-s-s-i.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [getRSSI](get-r-s-s-i.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [searchForAccessPoint](search-for-access-point.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForAccessPoint](search-for-access-point.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)? |
| [searchForAccessPoints](search-for-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForAccessPoints](search-for-access-points.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)> |
| [searchForSSID](search-for-s-s-i-d.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForSSID](search-for-s-s-i-d.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [searchForSSIDs](search-for-s-s-i-ds.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForSSIDs](search-for-s-s-i-ds.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)> |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyApi](../../com.isupatches.android.wisefy/-wisefy-api/index.md) |
| [AccessPointsUtil](../-access-points-util/index.md) |
| [LegacyAccessPointsDelegate](../../com.isupatches.android.wisefy.accesspoints.delegates/-legacy-access-points-delegate/index.md) |
