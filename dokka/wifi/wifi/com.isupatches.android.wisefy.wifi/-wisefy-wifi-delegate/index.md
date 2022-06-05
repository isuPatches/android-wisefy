//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WisefyWifiDelegate](index.md)

# WisefyWifiDelegate

[androidJvm]\
class [WisefyWifiDelegate](index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [WifiDelegate](../-wifi-delegate/index.md)

An internal Wisefy delegate for enabling, disabling, and checking the state of Wifi.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.WifiDelegate](../-wifi-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| logger | The logger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefyWifiDelegate](-wisefy-wifi-delegate.md) | [androidJvm]<br>fun [WisefyWifiDelegate](-wisefy-wifi-delegate.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [disableWifi](disable-wifi.md) | [androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~disableWifi~~](disable-wifi.md)~~(~~request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)~~)~~~~:~~ [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable Wifi.<br>[androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~disableWifi~~](disable-wifi.md)~~(~~request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md), callbacks: [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md)?~~)~~<br>An asynchronous API to disable Wifi. |
| [enableWifi](enable-wifi.md) | [androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~enableWifi~~](enable-wifi.md)~~(~~request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)~~)~~~~:~~ [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable Wifi.<br>[androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~enableWifi~~](enable-wifi.md)~~(~~request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md), callbacks: [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md)?~~)~~<br>An asynchronous API to enable Wifi. |
| [isWifiEnabled](is-wifi-enabled.md) | [androidJvm]<br>open override fun [isWifiEnabled](is-wifi-enabled.md)(request: [IsWifiEnabledRequest](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-request/index.md)): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if Wifi is enabled. |
