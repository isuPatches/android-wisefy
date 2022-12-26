//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiDelegate](index.md)

# WifiDelegate

[androidJvm]\
interface [WifiDelegate](index.md) : [WifiApi](../-wifi-api/index.md), [WifiApiAsync](../-wifi-api-async/index.md)

A delegate for synchronous and asynchronous APIs to enable, disable and check the state of wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.WifiApi](../-wifi-api/index.md) |  |
| [com.isupatches.android.wisefy.wifi.WifiApiAsync](../-wifi-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [disableWifi](../-wifi-api/disable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [disableWifi](../-wifi-api/disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)): [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable wifi.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [disableWifi](../-wifi-api-async/disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md), callbacks: [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md)?)<br>An asynchronous API to disable wifi. |
| [enableWifi](../-wifi-api/enable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [enableWifi](../-wifi-api/enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable wifi.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [enableWifi](../-wifi-api-async/enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md), callbacks: [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md)?)<br>An asynchronous API to enable Wifi. |
| [isWifiEnabled](../-wifi-api/is-wifi-enabled.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>abstract fun [isWifiEnabled](../-wifi-api/is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) = IsWifiEnabledQuery()): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if wifi is enabled.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>abstract fun [isWifiEnabled](../-wifi-api-async/is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) = IsWifiEnabledQuery(), callbacks: [IsWifiEnabledCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-is-wifi-enabled-callbacks/index.md)?)<br>An asynchronous API to check the current state of wifi (f.e. enabled or disabled). |

## Inheritors

| Name |
|---|
| [WisefyWifiDelegate](../-wisefy-wifi-delegate/index.md) |
