//[savednetworks](../../index.md)/[com.isupatches.android.wisefy.savednetworks](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [SavedNetworkApi](-saved-network-api/index.md) | [androidJvm]<br>interface [SavedNetworkApi](-saved-network-api/index.md)<br>A set of synchronous APIs for getting and searching for saved networks. |
| [SavedNetworkApiAsync](-saved-network-api-async/index.md) | [androidJvm]<br>interface [SavedNetworkApiAsync](-saved-network-api-async/index.md)<br>A set of asynchronous APIs for getting and searching for saved networks. |
| [SavedNetworkDelegate](-saved-network-delegate/index.md) | [androidJvm]<br>interface [SavedNetworkDelegate](-saved-network-delegate/index.md) : [SavedNetworkApi](-saved-network-api/index.md), [SavedNetworkApiAsync](-saved-network-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs for getting and searching for saved networks. |
| [WisefySavedNetworkDelegate](-wisefy-saved-network-delegate/index.md) | [androidJvm]<br>class [WisefySavedNetworkDelegate](-wisefy-saved-network-delegate/index.md)(assertions: [WisefyAssertions](../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [SavedNetworkApi](-saved-network-api/index.md) = when {         sdkUtil.isAtLeastR() -&gt; Android30SavedNetworkAdapter(wifiManager, logger)         sdkUtil.isAtLeastQ() -&gt; Android29SavedNetworkAdapter(assertions)         else -&gt; DefaultSavedNetworkAdapter(wifiManager, logger)     }) : [SavedNetworkDelegate](-saved-network-delegate/index.md)<br>An internal Wisefy delegate for getting and searching for saved networks. |
