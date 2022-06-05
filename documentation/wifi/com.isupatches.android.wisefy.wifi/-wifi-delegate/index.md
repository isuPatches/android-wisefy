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
| [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isWifiEnabled](../-wifi-api/is-wifi-enabled.md) | [androidJvm]<br>abstract fun [isWifiEnabled](../-wifi-api/is-wifi-enabled.md)(request: [IsWifiEnabledRequest](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-request/index.md) = IsWifiEnabledRequest()): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if Wifi is enabled. |
| [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyWifiDelegate](../-wisefy-wifi-delegate/index.md) |
