//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsDelegate](index.md)

# AccessPointsDelegate

interface [AccessPointsDelegate](index.md) : [AccessPointsApi](../-access-points-api/index.md), [AccessPointsApiAsync](../-access-points-api-async/index.md)

A delegate for synchronous and asynchronous access point APIs.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AccessPointsApi](../-access-points-api/index.md) |
| [AccessPointsApiAsync](../-access-points-api-async/index.md) |

#### Inheritors

| |
|---|
| [WisefyAccessPointsDelegate](../-wisefy-access-points-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAccessPoints](../-access-points-api/get-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getAccessPoints](../-access-points-api/get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md)): [GetAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md)<br>A synchronous API to query for a list of nearby access points.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getAccessPoints](../-access-points-api-async/get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md), callbacks: [GetAccessPointsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-access-points-callbacks/index.md)?)<br>An asynchronous API to query for a list of nearby access points. |
| [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
