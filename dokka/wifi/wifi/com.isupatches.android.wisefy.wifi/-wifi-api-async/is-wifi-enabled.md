//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiApiAsync](index.md)/[isWifiEnabled](is-wifi-enabled.md)

# isWifiEnabled

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)

abstract fun [isWifiEnabled](is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) = IsWifiEnabledQuery(), callbacks: [IsWifiEnabledCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-is-wifi-enabled-callbacks/index.md)?)

An asynchronous API to check the current state of wifi (f.e. enabled or disabled).

*NOTES*

- 
   Locked by the wifiMutex along with functions for enabling and disabling wifi

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) |  |
| [com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-is-wifi-enabled-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to check the current state of wifi |
| callbacks | The callbacks for results when checking the current state of wifi |
