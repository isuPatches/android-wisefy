//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApiAsync](index.md)/[searchForSSIDs](search-for-s-s-i-ds.md)

# searchForSSIDs

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [searchForSSIDs](search-for-s-s-i-ds.md)(request: [SearchForMultipleSSIDsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-s-s-i-ds-request/index.md), callbacks: [SearchForSSIDsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-s-s-i-ds-callbacks/index.md)?)

An asynchronous API to search for a list of nearby SSIDs.

#### Return

List<SSIDData> - List of SSID data or empty list

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-multiple-s-s-i-ds-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-s-s-i-ds-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a list of nearby SSIDs |
| callbacks | The callbacks for when the list of SSIDs is returned |
