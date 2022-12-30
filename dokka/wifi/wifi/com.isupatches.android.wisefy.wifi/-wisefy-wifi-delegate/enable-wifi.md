//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WisefyWifiDelegate](index.md)/[enableWifi](enable-wifi.md)

# enableWifi

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

open override fun [enableWifi](enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)

A synchronous API to enable wifi.

*NOTES*

- 
   Will open the Wifi settings screen on Android Q / SDK 29 or higher

#### Return

EnableWifiResult - The result of enabling wifi

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) |
| [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to enable wifi |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

open override fun [enableWifi](enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md), callbacks: [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md)?)

An asynchronous API to enable Wifi.

*NOTES*

- 
   Locked by the wifiMutex along with functions for disabling wifi and checking if wifi is enabled
- 
   Will open the wifi settings screen on Android Q / SDK 29 or higher

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) |
| [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to enable wifi |
| callbacks | The callbacks for results when enabling wifi |
