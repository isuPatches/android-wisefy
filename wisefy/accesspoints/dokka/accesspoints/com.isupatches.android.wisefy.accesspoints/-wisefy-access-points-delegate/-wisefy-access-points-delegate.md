//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints](../index.md)/[WisefyAccessPointsDelegate](index.md)/[WisefyAccessPointsDelegate](-wisefy-access-points-delegate.md)

# WisefyAccessPointsDelegate

[androidJvm]\
constructor(logger: WisefyLogger, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, adapter: [AccessPointsApi](../-access-points-api/index.md) = DefaultAccessPointsAdapter(wifiManager, logger))

#### Parameters

androidJvm

| | |
|---|---|
| logger | The WisefyLogger instance to use |
| wifiManager | The WifiManager instance to use |
| coroutineDispatcherProvider | The CoroutineDispatcherProvider instance to use |
| scope | The coroutine scope to use |
| adapter | The adapter instance to use for access point queries (determined based on the Android OS level) |
