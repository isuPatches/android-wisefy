//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkDelegate](index.md)

# SavedNetworkDelegate

[androidJvm]\
interface [SavedNetworkDelegate](index.md) : [SavedNetworkApi](../-saved-network-api/index.md), [SavedNetworkApiAsync](../-saved-network-api-async/index.md)

A delegate for synchronous and asynchronous APIs for getting and searching for saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.SavedNetworkApi](../-saved-network-api/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.SavedNetworkApiAsync](../-saved-network-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [getSavedNetworks](../-saved-network-api/get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](../-saved-network-api/get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest()): [GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md)<br>A synchronous API to get the saved networks on the device.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](../-saved-network-api-async/get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest(), callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)<br>An asynchronous API to get the saved networks on the device. |
| [isNetworkSaved](../-saved-network-api/is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [isNetworkSaved](../-saved-network-api/is-network-saved.md)(request: [IsNetworkSavedRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-request/index.md)): [IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md)<br>A synchronous API to check if a network is saved on the device. |
| [searchForSavedNetwork](../-saved-network-api/search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetwork](../-saved-network-api/search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md)): [SearchForSavedNetworkResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-result/index.md)<br>A synchronous API to search for a saved network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetwork](../-saved-network-api-async/search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md), callbacks: [SearchForSavedNetworkCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-network-callbacks/index.md)?)<br>An asynchronous API to search for a single saved network. |
| [searchForSavedNetworks](../-saved-network-api/search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetworks](../-saved-network-api/search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md)): [SearchForSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/index.md)<br>A synchronous API to search for a set of saved networks.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetworks](../-saved-network-api-async/search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md), callbacks: [SearchForSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-networks-callbacks/index.md)?)<br>An asynchronous API to search for a set of saved networks. |

## Inheritors

| Name |
|---|
| [WisefySavedNetworkDelegate](../-wisefy-saved-network-delegate/index.md) |
