//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApiAsync](index.md)/[isNetworkSaved](is-network-saved.md)

# isNetworkSaved

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

abstract fun [isNetworkSaved](is-network-saved.md)(query: [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md), callbacks: [IsNetworkSavedCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-is-network-saved-callbacks/index.md)?)

An asynchronous API to check if a network is saved on the device.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for adding, removing, and querying for saved networks

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md) |
| [IsNetworkSavedCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-is-network-saved-callbacks/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to check if a network is saved on the device |
| callbacks | The callbacks for the result of whether the network is saved on the device |
