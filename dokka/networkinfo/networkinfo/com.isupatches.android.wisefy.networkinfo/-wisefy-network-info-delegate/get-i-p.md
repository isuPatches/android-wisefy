//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)/[getIP](get-i-p.md)

# getIP

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

open override fun [getIP](get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md)): [GetIPResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-result/index.md)

A synchronous API to get the device's IP.

#### Return

GetIPResult - The result of getting the device's IP

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.entities.GetIPResult](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the device's IP |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

open override fun [getIP](get-i-p.md)(request: [GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md), callbacks: [GetIPCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-i-p-callbacks/index.md)?)

An asynchronous API to get the device's IP.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest](../../com.isupatches.android.wisefy.networkinfo.entities/-get-i-p-request/index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks](../../com.isupatches.android.wisefy.networkinfo.callbacks/-get-i-p-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the device's IP |
| callbacks | The callbacks for retrieving the device's IP |
