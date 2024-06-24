//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[WisefySavedNetworkDelegate](index.md)

# WisefySavedNetworkDelegate

class [WisefySavedNetworkDelegate](index.md)(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [SavedNetworkApi](../-saved-network-api/index.md) = when {
        sdkUtil.isAtLeastR() -&gt; Android30SavedNetworkAdapter(wifiManager, logger)
        sdkUtil.isAtLeastQ() -&gt; Android29SavedNetworkAdapter(assertions)
        else -&gt; DefaultSavedNetworkAdapter(wifiManager, logger)
    }) : [SavedNetworkDelegate](../-saved-network-delegate/index.md)

An internal Wisefy delegate for getting and searching for saved networks.

*Notes*

- 
   These functions share a mutex with add/remove network

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
| scope | The CoroutineScope to use |
| savedNetworkMutex | A mutex shared with add/remove network to ensure synchronization between saved network reads and writes |
| adapter | The adapter instance to use for querying for saved networks and checking if a network is saved (determined based on the Android OS level) |

#### See also

| |
|---|
| Android29SavedNetworkAdapter |
| Android30SavedNetworkAdapter |
| CoroutineDispatcherProvider |
| DefaultSavedNetworkAdapter |
| WisefyAssertions |
| WisefyLogger |
| [SavedNetworkApi](../-saved-network-api/index.md) |
| SdkUtil |

## Constructors

| | |
|---|---|
| [WisefySavedNetworkDelegate](-wisefy-saved-network-delegate.md) | [androidJvm]<br>constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, savedNetworkMutex: Mutex, adapter: [SavedNetworkApi](../-saved-network-api/index.md) = when {         sdkUtil.isAtLeastR() -&gt; Android30SavedNetworkAdapter(wifiManager, logger)         sdkUtil.isAtLeastQ() -&gt; Android29SavedNetworkAdapter(assertions)         else -&gt; DefaultSavedNetworkAdapter(wifiManager, logger)     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [getSavedNetworks](get-saved-networks.md)(query: [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md)): [GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md)<br>A synchronous API to get all of the saved networks on the device.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [getSavedNetworks](get-saved-networks.md)(query: [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md), callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)<br>An asynchronous API to get the saved networks on the device. |
| [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [isNetworkSaved](is-network-saved.md)(query: [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md)): [IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md)<br>A synchronous API to check if a network is saved on the device.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [isNetworkSaved](is-network-saved.md)(query: [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md), callbacks: [IsNetworkSavedCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-is-network-saved-callbacks/index.md)?)<br>An asynchronous API to check if a network is saved on the device. |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
