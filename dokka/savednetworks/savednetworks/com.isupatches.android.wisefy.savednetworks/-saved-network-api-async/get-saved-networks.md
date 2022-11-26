//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApiAsync](index.md)/[getSavedNetworks](get-saved-networks.md)

# getSavedNetworks

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

abstract fun [getSavedNetworks](get-saved-networks.md)(request: [GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) = GetSavedNetworksRequest(), callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md)?)

An asynchronous API to get the saved networks on the device.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-get-saved-networks-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-get-saved-networks-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to get the saved networks on the device |
| callbacks | The callbacks for when the result for getting saved networks on the device is returned |