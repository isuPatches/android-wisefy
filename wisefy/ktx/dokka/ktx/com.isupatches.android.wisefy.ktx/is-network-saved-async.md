//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[isNetworkSavedAsync](is-network-saved-async.md)

# isNetworkSavedAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

suspend fun WisefyApi.[isNetworkSavedAsync](is-network-saved-async.md)(query: IsNetworkSavedQuery): IsNetworkSavedResult

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

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the request to check if a network is saved on a device |

#### See also

| |
|---|
| IsNetworkSavedQuery |
| IsNetworkSavedResult |

#### Throws

| |
|---|
| WisefyException |
