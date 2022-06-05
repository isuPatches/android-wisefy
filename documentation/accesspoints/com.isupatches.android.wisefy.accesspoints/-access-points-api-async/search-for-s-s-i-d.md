//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApiAsync](index.md)/[searchForSSID](search-for-s-s-i-d.md)

# searchForSSID

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [searchForSSID](search-for-s-s-i-d.md)(request: [SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md), callbacks: [SearchForSSIDCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-s-s-i-d-callbacks/index.md)?)

An asynchronous API to search for a nearby SSID.

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
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-search-for-s-s-i-d-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a list of nearby SSIDs |
| callbacks | The callbacks for when the SSID is returned |
