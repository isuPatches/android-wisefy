//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[WisefyAddNetworkDelegate](index.md)

# WisefyAddNetworkDelegate

class [WisefyAddNetworkDelegate](index.md)(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [AddNetworkApi](../-add-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30AddNetworkAdapter(wifiManager, logger, assertions)
        sdkUtil.isAtLeastQ() -&gt; Android29AddNetworkAdapter(assertions)
        else -&gt; DefaultAddNetworkAdapter(wifiManager, logger, assertions)
    }) : [AddNetworkDelegate](../-add-network-delegate/index.md)

An internal Wisefy delegate for adding networks.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

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

#### See also

| |
|---|
| [AddNetworkApi](../-add-network-api/index.md) |
| [AddNetworkDelegate](../-add-network-delegate/index.md) |
| Android30AddNetworkAdapter |
| Android29AddNetworkAdapter |
| CoroutineDispatcherProvider |
| DefaultAddNetworkAdapter |
| SdkUtil |
| WisefyAssertions |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [WisefyAddNetworkDelegate](-wisefy-add-network-delegate.md) | [androidJvm]<br>constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [AddNetworkApi](../-add-network-api/index.md) = when {         sdkUtil.isAtLeastR() -&gt; Android30AddNetworkAdapter(wifiManager, logger, assertions)         sdkUtil.isAtLeastQ() -&gt; Android29AddNetworkAdapter(assertions)         else -&gt; DefaultAddNetworkAdapter(wifiManager, logger, assertions)     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addNetwork](add-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [addNetwork](add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>A synchronous API for adding a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [addNetwork](add-network.md)(request: [AddNetworkRequest](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-request/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.addnetwork.callbacks/-add-network-callbacks/index.md)?)<br>An asynchronous API for adding a network. |
| [equals](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
