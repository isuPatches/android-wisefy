//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[WisefyRemoveNetworkDelegate](index.md)/[WisefyRemoveNetworkDelegate](-wisefy-remove-network-delegate.md)

# WisefyRemoveNetworkDelegate

[androidJvm]\
constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [RemoveNetworkApi](../-remove-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30RemoveNetworkAdapter(logger, wifiManager)
        sdkUtil.isAtLeastQ() -&gt; Android29RemoveNetworkAdapter(assertions)
        else -&gt; DefaultRemoveNetworkAdapter(logger, wifiManager)
    })

#### Parameters

androidJvm

| | |
|---|---|
| assertions | The WisefyAssertions instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| savedNetworkMutex | A mutex shared with add/remove network to ensure synchronization between saved network reads and writes |
| adapter | The adapter instance to use for removing a network (determined based on the Android OS level) |
