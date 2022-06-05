//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[ConnectToNetworkCallbacks](index.md)

# ConnectToNetworkCallbacks

[androidJvm]\
interface [ConnectToNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for connecting to a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onConnectedToNetwork](on-connected-to-network.md) | [androidJvm]<br>abstract fun [onConnectedToNetwork](on-connected-to-network.md)()<br>A callback triggered when the device has successfully connected to a network. |
| [onConnectionRequestPlaced](on-connection-request-placed.md) | [androidJvm]<br>abstract fun [onConnectionRequestPlaced](on-connection-request-placed.md)()<br>A callback triggered when a request is placed to connect to a network. |
| [onFailureConnectingToNetwork](on-failure-connecting-to-network.md) | [androidJvm]<br>abstract fun [onFailureConnectingToNetwork](on-failure-connecting-to-network.md)()<br>A callback triggered when there is a failure connecting to a network. |
| [onNetworkNotFoundToConnectTo](on-network-not-found-to-connect-to.md) | [androidJvm]<br>abstract fun [onNetworkNotFoundToConnectTo](on-network-not-found-to-connect-to.md)()<br>A callback triggered when there is a no network found to connect to. |
| [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#823639724%2FFunctions%2F1257109763) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#823639724%2FFunctions%2F1257109763)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
