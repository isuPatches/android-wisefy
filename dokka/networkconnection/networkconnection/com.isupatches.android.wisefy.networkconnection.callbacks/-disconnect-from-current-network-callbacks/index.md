//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[DisconnectFromCurrentNetworkCallbacks](index.md)

# DisconnectFromCurrentNetworkCallbacks

[androidJvm]\
interface [DisconnectFromCurrentNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for disconnecting from a network.

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
| [onDisconnectedFromCurrentNetwork](on-disconnected-from-current-network.md) | [androidJvm]<br>abstract fun [onDisconnectedFromCurrentNetwork](on-disconnected-from-current-network.md)()<br>A callback triggered when the device has successfully disconnected from a network. |
| [onDisconnectRequestPlaced](on-disconnect-request-placed.md) | [androidJvm]<br>abstract fun [onDisconnectRequestPlaced](on-disconnect-request-placed.md)()<br>A callback triggered when a request is placed to disconnect from a network. |
| [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md) | [androidJvm]<br>abstract fun [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md)()<br>A callback triggered when there is a failure disconnecting from a network. |
| [onNetworkNotFoundToDisconnectFrom](on-network-not-found-to-disconnect-from.md) | [androidJvm]<br>abstract fun [onNetworkNotFoundToDisconnectFrom](on-network-not-found-to-disconnect-from.md)()<br>A callback triggered when there is a no network found to disconnect from. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F1257109763) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F1257109763)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
