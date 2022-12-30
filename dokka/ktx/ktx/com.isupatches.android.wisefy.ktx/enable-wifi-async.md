//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[enableWifiAsync](enable-wifi-async.md)

# enableWifiAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)

suspend fun WisefyApi.[enableWifiAsync](enable-wifi-async.md)(request: [EnableWifiRequest](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)

A coroutine extension for enabling wifi.

*NOTES*

- 
   Locked by the wifiMutex along with functions for disabling wifi and checking if wifi is enabled
- 
   Will open the wifi settings screen on Android Q / SDK 29 or higher

#### Receiver

WisefyApi

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
| [EnableWifiRequest](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) |
| [EnableWifiResult](../../../wifi/wifi/com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to enable wifi |

#### Throws

| |
|---|
| [WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |
