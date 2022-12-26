//[accesspoints](../../index.md)/[com.isupatches.android.wisefy.accesspoints](index.md)

# Package com.isupatches.android.wisefy.accesspoints

## Types

| Name | Summary |
|---|---|
| [AccessPointsApi](-access-points-api/index.md) | [androidJvm]<br>interface [AccessPointsApi](-access-points-api/index.md)<br>A set of synchronous APIs for getting and searching for nearby access points. |
| [AccessPointsApiAsync](-access-points-api-async/index.md) | [androidJvm]<br>interface [AccessPointsApiAsync](-access-points-api-async/index.md)<br>A set of asynchronous APIs for getting and searching for nearby access points. |
| [AccessPointsDelegate](-access-points-delegate/index.md) | [androidJvm]<br>interface [AccessPointsDelegate](-access-points-delegate/index.md) : [AccessPointsApi](-access-points-api/index.md), [AccessPointsApiAsync](-access-points-api-async/index.md)<br>A delegate for synchronous and asynchronous access point APIs. |
| [WisefyAccessPointsDelegate](-wisefy-access-points-delegate/index.md) | [androidJvm]<br>class [WisefyAccessPointsDelegate](-wisefy-access-points-delegate/index.md)(logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, adapter: [AccessPointsApi](-access-points-api/index.md) = DefaultAccessPointsAdapter(wifiManager, logger)) : [AccessPointsDelegate](-access-points-delegate/index.md)<br>An internal Wisefy delegate for getting and searching for nearby access points. |
