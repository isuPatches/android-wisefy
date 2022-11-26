//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApi](index.md)/[getSavedNetworks](get-saved-networks.md)

# getSavedNetworks

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

abstract fun [getSavedNetworks](get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest()): [GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md)

A synchronous API to get the saved networks on the device.

#### Return

GetSavedNetworksResult - The result of getting the saved networks on the device

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the saved networks on the device |