//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[SearchForAccessPointsCallbacks](index.md)

# SearchForAccessPointsCallbacks

[androidJvm]\
interface [SearchForAccessPointsCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving multiple access points.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onAccessPointsFound](on-access-points-found.md) | [androidJvm]<br>abstract fun [onAccessPointsFound](on-access-points-found.md)(accessPoints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)&gt;)<br>A callback triggered when there are matching access points. |
| [onNoAccessPointsFound](on-no-access-points-found.md) | [androidJvm]<br>abstract fun [onNoAccessPointsFound](on-no-access-points-found.md)()<br>A callback triggered when there are no matching matching access points. |
| [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
