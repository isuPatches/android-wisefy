//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[isNetworkSavedAsync](is-network-saved-async.md)

# isNetworkSavedAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

suspend fun WisefyApi.[isNetworkSavedAsync](is-network-saved-async.md)(query: [IsNetworkSavedQuery](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md)): [IsNetworkSavedResult](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md)

A coroutine extension for checking if a network is saved on a device.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for adding, removing, and querying for saved networks

#### Receiver

WisefyApi

#### Return

IsNetworkSavedResult - The result of checking if a network is saved on the device.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-query/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult](../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks.entities/-is-network-saved-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| query | The details of the request to check if a network is saved on a device |

## Throws

| | |
|---|---|
| [com.isupatches.android.wisefy.core.exceptions.WisefyException](../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |  |
