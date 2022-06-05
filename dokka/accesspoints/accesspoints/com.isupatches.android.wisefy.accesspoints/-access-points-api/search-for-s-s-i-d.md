//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[AccessPointsApi](index.md)/[searchForSSID](search-for-s-s-i-d.md)

# searchForSSID

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

abstract fun [searchForSSID](search-for-s-s-i-d.md)(request: [SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md)): [SearchForSSIDResult](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-s-s-i-d-result/index.md)

A synchronous API to search for a nearby SSID.

#### Return

SSIDData - SSID data if network found, otherwise null

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-search-for-single-s-s-i-d-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.entities.SSIDData](../../com.isupatches.android.wisefy.accesspoints.entities/-s-s-i-d-data/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a list of a nearby SSID. |
