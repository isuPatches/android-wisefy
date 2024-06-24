//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[getCurrentNetworkAsync](get-current-network-async.md)

# getCurrentNetworkAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

suspend fun WisefyApi.[getCurrentNetworkAsync](get-current-network-async.md)(query: GetCurrentNetworkQuery = GetCurrentNetworkQuery()): GetCurrentNetworkResult

A coroutine extension for getting the device's current network.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing, and getting     the device's current network connection status

#### Receiver

WisefyApi

#### Return

GetCurrentNetworkResult - The result when getting device's current network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to get the device's current network |

#### See also

| |
|---|
| GetCurrentNetworkQuery |
| GetCurrentNetworkResult |

#### Throws

| |
|---|
| WisefyException |
