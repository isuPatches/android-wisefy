//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApiAsync](index.md)/[searchForAccessPoint](search-for-access-point.md)

# searchForAccessPoint

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)

abstract fun [searchForAccessPoint](search-for-access-point.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), callbacks: [SearchForAccessPointCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-access-point-callbacks/index.md)?)