//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiApiAsync](index.md)/[disableWifi](disable-wifi.md)

# disableWifi

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

abstract fun [disableWifi](disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md), callbacks: [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md)?)

An asynchronous API to disable wifi.

*NOTES*

- 
   Locked by the wifiMutex along with functions for enabling wifi and checking if wifi is enabled
- 
   Will open the Wifi settings screen on Android Q / SDK 29 or higher

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md) |
| [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to disable wifi |
| callbacks | The callbacks for results when disabling wifi |
