//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionApi](index.md)

# NetworkConnectionApi

[androidJvm]\
interface [NetworkConnectionApi](index.md)

A set of synchronous APIs for connecting to and disconnecting from a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Functions

| Name | Summary |
|---|---|
| [changeNetwork](change-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>abstract fun [changeNetwork](change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md)): [ChangeNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md)<br>An synchronous API to change the network the device is connected to. |
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>~~abstract~~ ~~fun~~ [~~connectToNetwork~~](connect-to-network.md)~~(~~request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md)~~)~~~~:~~ [ConnectToNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/index.md)<br>A synchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)~~(~~request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md)~~)~~~~:~~ [DisconnectFromCurrentNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/index.md)<br>A synchronous API to disconnect from the current network. |

## Inheritors

| Name |
|---|
| [NetworkConnectionDelegate](../-network-connection-delegate/index.md) |
