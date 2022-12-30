//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionDelegate](index.md)/[changeNetwork](change-network.md)

# changeNetwork

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

open override fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md)): [ChangeNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md)

An synchronous API to change the network the device is connected to.

*Notes*

- 
   Will open up the internet connectivity panel

#### Return

ChangeNetworkResult - The result of changing the device's network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md) |
| [ChangeNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to change the device's network |

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

open override fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md), callbacks: [ChangeNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-change-network-callbacks/index.md)?)

An asynchronous API to change the network the device is connected to.

*Notes*

- 
   Locked by the networkConnectionMutex along with functions for connecting, disconnecting, getting the device's     current network connection status, and getting the device's current network
- 
   Will open up the internet connectivity panel

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [ChangeNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-change-network-callbacks/index.md) |
| [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to change the device's network |
| callbacks | The callbacks for changing the device's network |
