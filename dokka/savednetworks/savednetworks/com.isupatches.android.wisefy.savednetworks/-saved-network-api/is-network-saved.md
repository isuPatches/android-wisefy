//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApi](index.md)/[isNetworkSaved](is-network-saved.md)

# isNetworkSaved

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

abstract fun [isNetworkSaved](is-network-saved.md)(query: [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md)): [IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md)

A synchronous API to check if a network is saved on the device.

#### Return

IsNetworkSavedResult - The result of checking if a network is saved on the device

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [IsNetworkSavedQuery](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md) |
| [IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to check if a network is saved on the device |
