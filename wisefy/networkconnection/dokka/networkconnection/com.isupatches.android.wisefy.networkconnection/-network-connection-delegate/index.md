//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[NetworkConnectionDelegate](index.md)

# NetworkConnectionDelegate

interface [NetworkConnectionDelegate](index.md) : [NetworkConnectionApi](../-network-connection-api/index.md), [NetworkConnectionApiAsync](../-network-connection-api-async/index.md)

A delegate for synchronous and asynchronous APIs to connect to and disconnect from networks.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [NetworkConnectionApi](../-network-connection-api/index.md) |
| [NetworkConnectionApiAsync](../-network-connection-api-async/index.md) |

#### Inheritors

| |
|---|
| [WisefyNetworkConnectionDelegate](../-wisefy-network-connection-delegate/index.md) |

## Functions

| Name | Summary |
|---|---|
| [changeNetwork](../-network-connection-api/change-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>abstract fun [changeNetwork](../-network-connection-api/change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md)): [ChangeNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/index.md)<br>An synchronous API to change the network the device is connected to.<br>[androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>abstract fun [changeNetwork](../-network-connection-api-async/change-network.md)(request: [ChangeNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-request/index.md), callbacks: [ChangeNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-change-network-callbacks/index.md)?)<br>An asynchronous API to change the network the device is connected to. |
| [connectToNetwork](../-network-connection-api/connect-to-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>abstract fun [~~connectToNetwork~~](../-network-connection-api/connect-to-network.md)(request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md)): [ConnectToNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/index.md)<br>A synchronous API to connect to a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>abstract fun [~~connectToNetwork~~](../-network-connection-api-async/connect-to-network.md)(request: [ConnectToNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-request/index.md), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-connect-to-network-callbacks/index.md)?)<br>An asynchronous API to connect to a network. |
| [disconnectFromCurrentNetwork](../-network-connection-api/disconnect-from-current-network.md) | [androidJvm]<br>abstract fun [~~disconnectFromCurrentNetwork~~](../-network-connection-api/disconnect-from-current-network.md)(request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md)): [DisconnectFromCurrentNetworkResult](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/index.md)<br>A synchronous API to disconnect from the current network.<br>[androidJvm]<br>abstract fun [~~disconnectFromCurrentNetwork~~](../-network-connection-api-async/disconnect-from-current-network.md)(request: [DisconnectFromCurrentNetworkRequest](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-request/index.md), callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.networkconnection.callbacks/-disconnect-from-current-network-callbacks/index.md)?)<br>An asynchronous API to disconnect from the current network. |
| [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
