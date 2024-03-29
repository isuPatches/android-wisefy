//[removenetwork](../../index.md)/[com.isupatches.android.wisefy.removenetwork](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [RemoveNetworkApi](-remove-network-api/index.md) | [androidJvm]<br>interface [RemoveNetworkApi](-remove-network-api/index.md)<br>A set of synchronous APIs for removing a network. |
| [RemoveNetworkApiAsync](-remove-network-api-async/index.md) | [androidJvm]<br>interface [RemoveNetworkApiAsync](-remove-network-api-async/index.md)<br>A set of asynchronous APIs for removing a network. |
| [RemoveNetworkDelegate](-remove-network-delegate/index.md) | [androidJvm]<br>interface [RemoveNetworkDelegate](-remove-network-delegate/index.md) : [RemoveNetworkApi](-remove-network-api/index.md), [RemoveNetworkApiAsync](-remove-network-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs for removing a network. |
| [WisefyRemoveNetworkDelegate](-wisefy-remove-network-delegate/index.md) | [androidJvm]<br>class [WisefyRemoveNetworkDelegate](-wisefy-remove-network-delegate/index.md)(assertions: [WisefyAssertions](../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [RemoveNetworkApi](-remove-network-api/index.md) = when {         sdkUtil.isAtLeastR() -&gt; Android30RemoveNetworkAdapter(logger, wifiManager)         sdkUtil.isAtLeastQ() -&gt; Android29RemoveNetworkAdapter(assertions)         else -&gt; DefaultRemoveNetworkAdapter(logger, wifiManager)     }) : [RemoveNetworkDelegate](-remove-network-delegate/index.md)<br>An internal Wisefy delegate for removing a network through the Android OS. |
