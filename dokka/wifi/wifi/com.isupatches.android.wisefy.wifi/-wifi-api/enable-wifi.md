//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiApi](index.md)/[enableWifi](enable-wifi.md)

# enableWifi

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

abstract fun [enableWifi](enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)

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

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) |  |
| [com.isupatches.android.wisefy.wifi.entities.EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to enable wifi |
