//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[addNetworkAsync](add-network-async.md)

# addNetworkAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])

suspend fun WisefyApi.[addNetworkAsync](add-network-async.md)(request: AddNetworkRequest): AddNetworkResult

A coroutine extension for adding a network.

*Notes*

- 
   Locked by the savedNetworkMutex along with functions for removing, querying, and checking if a network is saved

#### Receiver

WisefyApi

#### Return

AddNetworkResult - The result when adding a network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to add a network |

#### See also

| |
|---|
| AddNetworkRequest |
| AddNetworkResult |

#### Throws

| |
|---|
| WisefyException |
