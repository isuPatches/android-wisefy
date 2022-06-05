//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApiAsync](index.md)

# SavedNetworkApiAsync

[androidJvm]\
interface [SavedNetworkApiAsync](index.md)

A set of asynchronous APIs for getting and searching for saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest(), callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)<br>An asynchronous API to get the saved networks on the device. |
| [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [searchForSavedNetwork](search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetwork](search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md), callbacks: [SearchForSavedNetworkCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-network-callbacks/index.md)?)<br>An asynchronous API to search for a single saved network. |
| [searchForSavedNetworks](search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetworks](search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md), callbacks: [SearchForSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-networks-callbacks/index.md)?)<br>An asynchronous API to search for a set of saved networks. |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [SavedNetworkDelegate](../-saved-network-delegate/index.md) |
