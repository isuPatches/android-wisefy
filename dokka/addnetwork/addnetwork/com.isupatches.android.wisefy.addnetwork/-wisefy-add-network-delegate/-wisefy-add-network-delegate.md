//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[WisefyAddNetworkDelegate](index.md)/[WisefyAddNetworkDelegate](-wisefy-add-network-delegate.md)

# WisefyAddNetworkDelegate

[androidJvm]\
fun [WisefyAddNetworkDelegate](-wisefy-add-network-delegate.md)(assertions: [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [AddNetworkApi](../-add-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30AddNetworkAdapter(wifiManager, logger, assertions)
        sdkUtil.isAtLeastQ() -&gt; Android29AddNetworkAdapter(assertions)
        else -&gt; DefaultAddNetworkAdapter(wifiManager, logger, assertions)
    })

## Parameters

androidJvm

| | |
|---|---|
| assertions | The [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md) instance to use |
| logger | The [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) instance to use |
| sdkUtil | The [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) instance to use |
| wifiManager | The WifiManager instance to use |
