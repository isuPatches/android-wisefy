//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[WisefySavedNetworkDelegate](index.md)/[WisefySavedNetworkDelegate](-wisefy-saved-network-delegate.md)

# WisefySavedNetworkDelegate

[androidJvm]\
constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [SavedNetworkApi](../-saved-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30SavedNetworkAdapter(wifiManager, logger)
        sdkUtil.isAtLeastQ() -&gt; Android29SavedNetworkAdapter(assertions)
        else -&gt; DefaultSavedNetworkAdapter(wifiManager, logger)
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
| scope | The CoroutineScope to use |
| savedNetworkMutex | A mutex shared with add/remove network to ensure synchronization between saved network reads and writes |
| adapter | The adapter instance to use for querying for saved networks and checking if a network is saved (determined based on the Android OS level) |
