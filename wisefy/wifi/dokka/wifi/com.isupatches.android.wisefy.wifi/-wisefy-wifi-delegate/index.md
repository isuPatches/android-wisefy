//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WisefyWifiDelegate](index.md)

# WisefyWifiDelegate

class [WisefyWifiDelegate](index.md)(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, wifiMutex: Mutex, adapter: [WifiApi](../-wifi-api/index.md) = if (sdkUtil.isAtLeastQ()) {
        Android29WifiAdapter(wifiManager, logger, assertions)
    } else {
        DefaultWifiAdapter(wifiManager, logger, assertions)
    }) : [WifiDelegate](../-wifi-delegate/index.md)

An internal Wisefy delegate for enabling, disabling, and checking the state of wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| assertions | The WisefyAssertions instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| coroutineDispatcherProvider | The CoroutineDispatcherProvider instance to use |
| scope | The coroutine scope to use |
| wifiMutex | The mutex for all read/write operations involving wifi |
| adapter | The adapter instance to use for wifi operations (determined based on the Android OS level) |

#### See also

| |
|---|
| Android29WifiAdapter |
| CoroutineDispatcherProvider |
| DefaultWifiAdapter |
| SdkUtil |
| [WifiDelegate](../-wifi-delegate/index.md) |
| WisefyAssertions |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [WisefyWifiDelegate](-wisefy-wifi-delegate.md) | [androidJvm]<br>constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, wifiMutex: Mutex, adapter: [WifiApi](../-wifi-api/index.md) = if (sdkUtil.isAtLeastQ()) {         Android29WifiAdapter(wifiManager, logger, assertions)     } else {         DefaultWifiAdapter(wifiManager, logger, assertions)     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [disableWifi](disable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [disableWifi](disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md)): [DisableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/index.md)<br>A synchronous API to disable wifi.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [disableWifi](disable-wifi.md)(request: [DisableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-request/index.md), callbacks: [DisableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-disable-wifi-callbacks/index.md)?)<br>An asynchronous API to disable wifi. |
| [enableWifi](enable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [enableWifi](enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md)): [EnableWifiResult](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/index.md)<br>A synchronous API to enable wifi.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [enableWifi](enable-wifi.md)(request: [EnableWifiRequest](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-request/index.md), callbacks: [EnableWifiCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-enable-wifi-callbacks/index.md)?)<br>An asynchronous API to enable Wifi. |
| [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isWifiEnabled](is-wifi-enabled.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>open override fun [isWifiEnabled](is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md)): [IsWifiEnabledResult](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/index.md)<br>A synchronous API to check if wifi is enabled.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>open override fun [isWifiEnabled](is-wifi-enabled.md)(query: [IsWifiEnabledQuery](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-query/index.md), callbacks: [IsWifiEnabledCallbacks](../../com.isupatches.android.wisefy.wifi.callbacks/-is-wifi-enabled-callbacks/index.md)?)<br>An asynchronous API to check the current state of wifi (f.e. enabled or disabled). |
| [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
