//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[WisefyRemoveNetworkDelegate](index.md)

# WisefyRemoveNetworkDelegate

class [WisefyRemoveNetworkDelegate](index.md)(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [RemoveNetworkApi](../-remove-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30RemoveNetworkAdapter(logger, wifiManager)
        sdkUtil.isAtLeastQ() -&gt; Android29RemoveNetworkAdapter(assertions)
        else -&gt; DefaultRemoveNetworkAdapter(logger, wifiManager)
    }) : [RemoveNetworkDelegate](../-remove-network-delegate/index.md)

An internal Wisefy delegate for removing a network through the Android OS.

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
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| savedNetworkMutex | A mutex shared with add/remove network to ensure synchronization between saved network reads and writes |
| adapter | The adapter instance to use for removing a network (determined based on the Android OS level) |

#### See also

| |
|---|
| CoroutineDispatcherProvider |
| SdkUtil |
| WisefyAssertions |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [WisefyRemoveNetworkDelegate](-wisefy-remove-network-delegate.md) | [androidJvm]<br>constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [RemoveNetworkApi](../-remove-network-api/index.md) = when {         sdkUtil.isAtLeastR() -&gt; Android30RemoveNetworkAdapter(logger, wifiManager)         sdkUtil.isAtLeastQ() -&gt; Android29RemoveNetworkAdapter(assertions)         else -&gt; DefaultRemoveNetworkAdapter(logger, wifiManager)     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>An synchronous API to remove a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.removenetwork.callbacks/-remove-network-callbacks/index.md)?)<br>An asynchronous API to remove a network. |
| [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
