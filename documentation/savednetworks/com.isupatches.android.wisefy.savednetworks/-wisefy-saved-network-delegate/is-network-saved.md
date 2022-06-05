//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[WisefySavedNetworkDelegate](index.md)/[isNetworkSaved](is-network-saved.md)

# isNetworkSaved

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

open override fun [isNetworkSaved](is-network-saved.md)(request: [IsNetworkSavedRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-request/index.md)): [IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md)

A synchronous API to check if a network is saved on the device.

#### Return

IsNetworkSavedResult - The result of checking if a network is saved on the device.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult](../../com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to check if a network is saved on the device. |
