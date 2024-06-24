//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[GetAccessPointsCallbacks](index.md)

# GetAccessPointsCallbacks

interface [GetAccessPointsCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for querying access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| BaseWisefyCallbacks |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md) | [androidJvm]<br>abstract fun [onNearbyAccessPointsRetrieved](on-nearby-access-points-retrieved.md)(accessPoints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)&gt;)<br>A callback triggered when there are access points matching the [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md). |
| [onNoNearbyAccessPoints](on-no-nearby-access-points.md) | [androidJvm]<br>abstract fun [onNoNearbyAccessPoints](on-no-nearby-access-points.md)()<br>A callback triggered when there are no access points found for the [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md). |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F974708819) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F974708819)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
