//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[DisconnectFromCurrentNetworkCallbacks](index.md)

# DisconnectFromCurrentNetworkCallbacks

[androidJvm]\
interface [DisconnectFromCurrentNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for disconnecting from a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks |  |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onDisconnectedFromCurrentNetwork](on-disconnected-from-current-network.md) | [androidJvm]<br>abstract fun [onDisconnectedFromCurrentNetwork](on-disconnected-from-current-network.md)()<br>A callback triggered when the device has successfully disconnected from a network. |
| [onDisconnectRequestPlaced](on-disconnect-request-placed.md) | [androidJvm]<br>abstract fun [onDisconnectRequestPlaced](on-disconnect-request-placed.md)()<br>A callback triggered when a request is placed to disconnect from a network. |
| [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md) | [androidJvm]<br>abstract fun [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md)()<br>A callback triggered when there is a failure disconnecting from a network. |
| [onNetworkNotFoundToDisconnectFrom](on-network-not-found-to-disconnect-from.md) | [androidJvm]<br>abstract fun [onNetworkNotFoundToDisconnectFrom](on-network-not-found-to-disconnect-from.md)()<br>A callback triggered when there is a no network found to disconnect from. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F-1202619134) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F-1202619134)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
