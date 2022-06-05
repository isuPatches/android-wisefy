//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WifiApi](index.md)

# WifiApi

[androidJvm]\
interface [WifiApi](index.md)

A set of synchronous APIs for enabling, disabling, and checking the state of Wifi.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [disableWifi](disable-wifi.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](disable-wifi.md)~~(~~request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md) = DisableWifiRequest()~~)~~~~:~~ [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable Wifi. |
| [enableWifi](enable-wifi.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](enable-wifi.md)~~(~~request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md) = EnableWifiRequest()~~)~~~~:~~ [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable Wifi. |
| [isWifiEnabled](is-wifi-enabled.md) | [androidJvm]<br>abstract fun [isWifiEnabled](is-wifi-enabled.md)(request: [IsWifiEnabledRequest](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-request/index.md) = IsWifiEnabledRequest()): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if Wifi is enabled. |

## Inheritors

| Name |
|---|
| [WifiDelegate](../-wifi-delegate/index.md) |
