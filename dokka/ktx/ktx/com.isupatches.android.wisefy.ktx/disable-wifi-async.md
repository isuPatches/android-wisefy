//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[disableWifiAsync](disable-wifi-async.md)

# disableWifiAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

suspend fun WisefyApi.[disableWifiAsync](disable-wifi-async.md)(request: [DisableWifiRequest](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)): [DisableWifiResult](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)

A coroutine extension for disabling wifi.

*NOTES*

- 
   Locked by the wifiMutex along with functions for enabling wifi and checking if wifi is enabled
- 
   Will open the wifi settings screen on Android Q / SDK 29 or higher

#### Receiver

WisefyApi

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
| [com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md) |  |
| [com.isupatches.android.wisefy.wifi.entities.DisableWifiResult](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to disable wifi |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
