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
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest(), callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)<br>An asynchronous API to get the saved networks on the device. |
| [searchForSavedNetwork](search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetwork](search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md), callbacks: [SearchForSavedNetworkCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-network-callbacks/index.md)?)<br>An asynchronous API to search for a single saved network. |
| [searchForSavedNetworks](search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetworks](search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md), callbacks: [SearchForSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-networks-callbacks/index.md)?)<br>An asynchronous API to search for a set of saved networks. |

## Inheritors

| Name |
|---|
| [SavedNetworkDelegate](../-saved-network-delegate/index.md) |
