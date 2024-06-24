//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApiAsync](index.md)

# SavedNetworkApiAsync

interface [SavedNetworkApiAsync](index.md)

A set of asynchronous APIs for getting and searching for saved networks.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [SavedNetworkDelegate](../-saved-network-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](get-saved-networks.md)(query: [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) = GetSavedNetworksQuery.All, callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)<br>An asynchronous API to get the saved networks on the device. |
| [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [isNetworkSaved](is-network-saved.md)(query: [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md), callbacks: [IsNetworkSavedCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-is-network-saved-callbacks/index.md)?)<br>An asynchronous API to check if a network is saved on the device. |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
