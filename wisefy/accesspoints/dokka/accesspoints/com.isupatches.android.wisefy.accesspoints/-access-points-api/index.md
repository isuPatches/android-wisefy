//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)

# AccessPointsApi

interface [AccessPointsApi](index.md)

A set of synchronous APIs for getting and searching for nearby access points.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [AccessPointsDelegate](../-access-points-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAccessPoints](get-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>abstract fun [getAccessPoints](get-access-points.md)(query: [GetAccessPointsQuery](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-query/index.md)): [GetAccessPointsResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/index.md)<br>A synchronous API to query for a list of nearby access points. |
| [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.accesspoints.entities/-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
