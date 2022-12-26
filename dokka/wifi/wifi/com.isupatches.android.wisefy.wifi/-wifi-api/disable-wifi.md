//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiApi](index.md)/[disableWifi](disable-wifi.md)

# disableWifi

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

abstract fun [disableWifi](disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)): [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)

A synchronous API to disable wifi.

#### Return

DisableWifiResult - The result of disabling wifi

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md) |  |
| [com.isupatches.android.wisefy.wifi.entities.DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to disable wifi<br>*NOTES*<br>-     Will open the Wifi settings screen on Android Q / SDK 29 or higher |
