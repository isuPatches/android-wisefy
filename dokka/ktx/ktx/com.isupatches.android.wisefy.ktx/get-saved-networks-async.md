//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[getSavedNetworksAsync](get-saved-networks-async.md)

# getSavedNetworksAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

suspend fun WisefyApi.[getSavedNetworksAsync](get-saved-networks-async.md)(query: [GetSavedNetworksQuery](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) = GetSavedNetworksQuery.All): [GetSavedNetworksResult](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md)

A coroutine extension for getting all of the saved networks on a device.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for adding, removing, and checking if a network is saved

#### Receiver

WisefyApi

#### Return

GetSavedNetworksResult - The result of getting the saved networks on the device

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [GetSavedNetworksQuery](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-query/index.md) |
| [GetSavedNetworksResult](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get all saved networks on the device |

#### Throws

| |
|---|
| [WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |
