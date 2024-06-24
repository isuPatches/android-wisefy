//[addnetwork](../../index.md)/[com.isupatches.android.wisefy.addnetwork](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AddNetworkApi](-add-network-api/index.md) | [androidJvm]<br>interface [AddNetworkApi](-add-network-api/index.md)<br>A set of synchronous APIs related to adding networks. |
| [AddNetworkApiAsync](-add-network-api-async/index.md) | [androidJvm]<br>interface [AddNetworkApiAsync](-add-network-api-async/index.md)<br>A set of asynchronous APIs related to adding networks. |
| [AddNetworkDelegate](-add-network-delegate/index.md) | [androidJvm]<br>interface [AddNetworkDelegate](-add-network-delegate/index.md) : [AddNetworkApi](-add-network-api/index.md), [AddNetworkApiAsync](-add-network-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs to add networks. |
| [WisefyAddNetworkDelegate](-wisefy-add-network-delegate/index.md) | [androidJvm]<br>class [WisefyAddNetworkDelegate](-wisefy-add-network-delegate/index.md)(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [AddNetworkApi](-add-network-api/index.md) = when {         sdkUtil.isAtLeastR() -&gt; Android30AddNetworkAdapter(wifiManager, logger, assertions)         sdkUtil.isAtLeastQ() -&gt; Android29AddNetworkAdapter(assertions)         else -&gt; DefaultAddNetworkAdapter(wifiManager, logger, assertions)     }) : [AddNetworkDelegate](-add-network-delegate/index.md)<br>An internal Wisefy delegate for adding networks. |
