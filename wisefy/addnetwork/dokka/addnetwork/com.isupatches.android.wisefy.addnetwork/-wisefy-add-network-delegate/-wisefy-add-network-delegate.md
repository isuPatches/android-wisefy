//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[WisefyAddNetworkDelegate](index.md)/[WisefyAddNetworkDelegate](-wisefy-add-network-delegate.md)

# WisefyAddNetworkDelegate

[androidJvm]\
constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [AddNetworkApi](../-add-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30AddNetworkAdapter(wifiManager, logger, assertions)
        sdkUtil.isAtLeastQ() -&gt; Android29AddNetworkAdapter(assertions)
        else -&gt; DefaultAddNetworkAdapter(wifiManager, logger, assertions)
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
| savedNetworkMutex | The mutex for all read/write operations involving saved networks |
| adapter | The adapter instance to use for adding a network (determined based on the Android OS level) |
