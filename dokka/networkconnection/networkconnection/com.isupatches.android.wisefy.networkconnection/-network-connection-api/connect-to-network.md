//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionApi](index.md)/[connectToNetwork](connect-to-network.md)

# connectToNetwork

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])

abstract fun [~~connectToNetwork~~](connect-to-network.md)(request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md)): [ConnectToNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/index.md)

---

### Deprecated

As of Android Q / SDK, applications cannot directly connect to networks

---

A synchronous API to connect to a network.

#### Return

ConnectToNetworkResult - The result of connecting to a network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md) |
| [ConnectToNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to connect to a network |
