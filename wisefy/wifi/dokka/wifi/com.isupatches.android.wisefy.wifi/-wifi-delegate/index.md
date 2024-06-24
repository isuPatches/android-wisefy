//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiDelegate](index.md)

# WifiDelegate

interface [WifiDelegate](index.md) : [WifiApi](../-wifi-api/index.md), [WifiApiAsync](../-wifi-api-async/index.md)

A delegate for synchronous and asynchronous APIs to enable, disable and check the state of wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [WifiApi](../-wifi-api/index.md) |
| [WifiApiAsync](../-wifi-api-async/index.md) |

#### Inheritors

| |
|---|
| [WisefyWifiDelegate](../-wisefy-wifi-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [disableWifi](../-wifi-api/disable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [disableWifi](../-wifi-api/disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)): [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable wifi.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [disableWifi](../-wifi-api-async/disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md), callbacks: [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md)?)<br>An asynchronous API to disable wifi. |
| [enableWifi](../-wifi-api/enable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [enableWifi](../-wifi-api/enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable wifi.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>abstract fun [enableWifi](../-wifi-api-async/enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md), callbacks: [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md)?)<br>An asynchronous API to enable Wifi. |
| [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isWifiEnabled](../-wifi-api/is-wifi-enabled.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>abstract fun [isWifiEnabled](../-wifi-api/is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) = IsWifiEnabledQuery()): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if wifi is enabled.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>abstract fun [isWifiEnabled](../-wifi-api-async/is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md) = IsWifiEnabledQuery(), callbacks: [IsWifiEnabledCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-is-wifi-enabled-callbacks/index.md)?)<br>An asynchronous API to check the current state of wifi (f.e. enabled or disabled). |
| [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
