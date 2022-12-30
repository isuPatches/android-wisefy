//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApiAsync](index.md)/[getSavedNetworks](get-saved-networks.md)

# getSavedNetworks

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

abstract fun [getSavedNetworks](get-saved-networks.md)(query: [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) = GetSavedNetworksQuery.All, callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)

An asynchronous API to get the saved networks on the device.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for adding, removing, and checking if a network is saved

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) |
| [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the saved networks on the device |
| callbacks | The callbacks for when the result for getting saved networks on the device is returned |
