//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApiAsync](index.md)

# SavedNetworkApiAsync

[androidJvm]\
interface [SavedNetworkApiAsync](index.md)

A set of asynchronous APIs for getting and searching for saved networks.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [getSavedNetworks](get-saved-networks.md)(query: [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) = GetSavedNetworksQuery.All, callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)<br>An asynchronous API to get the saved networks on the device. |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>abstract fun [isNetworkSaved](is-network-saved.md)(query: [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md), callbacks: [IsNetworkSavedCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-is-network-saved-callbacks/index.md)?)<br>An asynchronous API to check if a network is saved on the device. |

## Inheritors

| Name |
|---|
| [SavedNetworkDelegate](../-saved-network-delegate/index.md) |
