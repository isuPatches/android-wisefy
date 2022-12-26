//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApi](index.md)/[getSavedNetworks](get-saved-networks.md)

# getSavedNetworks

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

abstract fun [getSavedNetworks](get-saved-networks.md)(query: [GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) = GetSavedNetworksQuery.All): [GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md)

A synchronous API to get all of the saved networks on the device.

#### Return

GetSavedNetworksResult - The result of getting the saved networks on the device

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the saved networks on the device |
