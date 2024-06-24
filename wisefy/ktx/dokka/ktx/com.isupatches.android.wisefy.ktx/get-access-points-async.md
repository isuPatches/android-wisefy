//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[getAccessPointsAsync](get-access-points-async.md)

# getAccessPointsAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

suspend fun WisefyApi.[getAccessPointsAsync](get-access-points-async.md)(query: GetAccessPointsQuery = GetAccessPointsQuery.All()): GetAccessPointsResult

A coroutine extension for getting all nearby access points.

#### Receiver

WisefyApi

#### Return

GetNearbyAccessPointsResult - The result of getting all nearby access points

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get all nearby access points |

#### See also

| |
|---|
| GetAccessPointsQuery |
| GetAccessPointsResult |

#### Throws

| |
|---|
| WisefyException |
