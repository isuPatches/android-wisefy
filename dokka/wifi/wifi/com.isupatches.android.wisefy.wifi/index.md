//[wifi](../../index.md)/[com.isupatches.android.wisefy.wifi](index.md)

# Package com.isupatches.android.wisefy.wifi

## Types

| Name | Summary |
|---|---|
| [WifiApi](-wifi-api/index.md) | [androidJvm]<br>interface [WifiApi](-wifi-api/index.md)<br>A set of synchronous APIs for enabling, disabling, and checking the state of wifi. |
| [WifiApiAsync](-wifi-api-async/index.md) | [androidJvm]<br>interface [WifiApiAsync](-wifi-api-async/index.md)<br>A set of asynchronous APIs for enabling and disabling wifi. |
| [WifiDelegate](-wifi-delegate/index.md) | [androidJvm]<br>interface [WifiDelegate](-wifi-delegate/index.md) : [WifiApi](-wifi-api/index.md), [WifiApiAsync](-wifi-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs to enable, disable and check the state of wifi. |
| [WisefyWifiDelegate](-wisefy-wifi-delegate/index.md) | [androidJvm]<br>class [WisefyWifiDelegate](-wisefy-wifi-delegate/index.md)(assertions: [WisefyAssertions](../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, wifiMutex: Mutex, adapter: [WifiApi](-wifi-api/index.md) = if (sdkUtil.isAtLeastQ()) {         Android29WifiAdapter(wifiManager, logger, assertions)     } else {         DefaultWifiAdapter(wifiManager, logger, assertions)     }) : [WifiDelegate](-wifi-delegate/index.md)<br>An internal Wisefy delegate for enabling, disabling, and checking the state of wifi. |
