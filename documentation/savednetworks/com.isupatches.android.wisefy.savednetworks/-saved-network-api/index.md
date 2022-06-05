//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApi](index.md)

# SavedNetworkApi

[androidJvm]\
interface [SavedNetworkApi](index.md)

A set of synchronous APIs for getting and searching for saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest()): [GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md)<br>A synchronous API to get the saved networks on the device. |
| [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [isNetworkSaved](is-network-saved.md)(request: [IsNetworkSavedRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-request/index.md)): [IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md)<br>A synchronous API to check if a network is saved on the device. |
| [searchForSavedNetwork](search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetwork](search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md)): [SearchForSavedNetworkResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-result/index.md)<br>A synchronous API to search for a saved network. |
| [searchForSavedNetworks](search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [searchForSavedNetworks](search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md)): [SearchForSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/index.md)<br>A synchronous API to search for a set of saved networks. |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [SavedNetworkDelegate](../-saved-network-delegate/index.md) |
