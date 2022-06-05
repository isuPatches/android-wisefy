//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiDelegate](index.md)

# WifiDelegate

[androidJvm]\
interface [WifiDelegate](index.md) : [WifiApi](../-wifi-api/index.md), [WifiApiAsync](../-wifi-api-async/index.md)

A delegate for synchronous and asynchronous APIs to enable, disable and check the state of Wifi.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.WifiApi](../-wifi-api/index.md) |  |
| [com.isupatches.android.wisefy.wifi.WifiApiAsync](../-wifi-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [disableWifi](../-wifi-api/disable-wifi.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](../-wifi-api/disable-wifi.md)~~(~~request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md) = DisableWifiRequest()~~)~~~~:~~ [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable Wifi.<br>[androidJvm]<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](../-wifi-api-async/disable-wifi.md)~~(~~request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md) = DisableWifiRequest(), callbacks: [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md)?~~)~~<br>An asynchronous API to disable Wifi. |
| [enableWifi](../-wifi-api/enable-wifi.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](../-wifi-api/enable-wifi.md)~~(~~request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) = EnableWifiRequest()~~)~~~~:~~ [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable Wifi.<br>[androidJvm]<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](../-wifi-api-async/enable-wifi.md)~~(~~request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) = EnableWifiRequest(), callbacks: [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md)?~~)~~<br>An asynchronous API to enable Wifi. |
| [isWifiEnabled](../-wifi-api/is-wifi-enabled.md) | [androidJvm]<br>abstract fun [isWifiEnabled](../-wifi-api/is-wifi-enabled.md)(request: [IsWifiEnabledRequest](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-request/index.md) = IsWifiEnabledRequest()): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if Wifi is enabled. |

## Inheritors

| Name |
|---|
| [WisefyWifiDelegate](../-wisefy-wifi-delegate/index.md) |
