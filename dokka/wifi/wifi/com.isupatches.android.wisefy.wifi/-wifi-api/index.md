//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiApi](index.md)

# WifiApi

[androidJvm]\
interface [WifiApi](index.md)

A set of synchronous APIs for enabling, disabling, and checking the state of wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [disableWifi](disable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [disableWifi](disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)): [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable wifi. |
| [enableWifi](enable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [enableWifi](enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable wifi. |
| [isWifiEnabled](is-wifi-enabled.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>abstract fun [isWifiEnabled](is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) = IsWifiEnabledQuery()): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if wifi is enabled. |

## Inheritors

| Name |
|---|
| [WifiDelegate](../-wifi-delegate/index.md) |
