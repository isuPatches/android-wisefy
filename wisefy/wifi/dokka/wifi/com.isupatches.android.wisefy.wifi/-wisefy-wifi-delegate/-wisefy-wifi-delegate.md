//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WisefyWifiDelegate](index.md)/[WisefyWifiDelegate](-wisefy-wifi-delegate.md)

# WisefyWifiDelegate

[androidJvm]\
constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, wifiMutex: Mutex, adapter: [WifiApi](../-wifi-api/index.md) = if (sdkUtil.isAtLeastQ()) {
        Android29WifiAdapter(wifiManager, logger, assertions)
    } else {
        DefaultWifiAdapter(wifiManager, logger, assertions)
    })

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
