//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[WisefyAccessPointsDelegate](index.md)/[getRSSI](get-r-s-s-i.md)

# getRSSI

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

open override fun [getRSSI](get-r-s-s-i.md)(request: [GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md)): [GetRSSIResult](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-result/index.md)

A synchronous API to get a network's RSSI level.

#### Return

RSSIData - RSSI data if network found, otherwise null

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md) |  |
| RSSIData |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get a networks RSSI level |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)

open override fun [getRSSI](get-r-s-s-i.md)(request: [GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md), callbacks: [GetRSSICallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-r-s-s-i-callbacks/index.md)?)

An asynchronous API to get a network's RSSI level.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest](../../com.isupatches.android.wisefy.accesspoints.entities/-get-r-s-s-i-request/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.callbacks.GetRSSICallbacks](../../com.isupatches.android.wisefy.accesspoints.callbacks/-get-r-s-s-i-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get a networks RSSI level |
| callbacks | The callbacks for when the RSSI data is returned |
