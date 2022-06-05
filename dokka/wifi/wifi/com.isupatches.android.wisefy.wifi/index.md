//[wifi](../../index.md)/[com.isupatches.android.wisefy.wifi](index.md)

# Package com.isupatches.android.wisefy.wifi

## Types

| Name | Summary |
|---|---|
| [WifiApi](-wifi-api/index.md) | [androidJvm]<br>interface [WifiApi](-wifi-api/index.md)<br>A set of synchronous APIs for enabling, disabling, and checking the state of Wifi. |
| [WifiApiAsync](-wifi-api-async/index.md) | [androidJvm]<br>interface [WifiApiAsync](-wifi-api-async/index.md)<br>A set of asynchronous APIs for enabling and disabling Wifi. |
| [WifiDelegate](-wifi-delegate/index.md) | [androidJvm]<br>interface [WifiDelegate](-wifi-delegate/index.md) : [WifiApi](-wifi-api/index.md), [WifiApiAsync](-wifi-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs to enable, disable and check the state of Wifi. |
| [WisefyWifiDelegate](-wisefy-wifi-delegate/index.md) | [androidJvm]<br>class [WisefyWifiDelegate](-wisefy-wifi-delegate/index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [WifiDelegate](-wifi-delegate/index.md)<br>An internal Wisefy delegate for enabling, disabling, and checking the state of Wifi. |
