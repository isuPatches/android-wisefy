//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[ConnectToNetworkCallbacks](index.md)

# ConnectToNetworkCallbacks

[androidJvm]\
interface [ConnectToNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for connecting to a network.

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
| [onConnectedToNetwork](on-connected-to-network.md) | [androidJvm]<br>abstract fun [onConnectedToNetwork](on-connected-to-network.md)()<br>A callback triggered when the device has successfully connected to a network. |
| [onConnectionRequestPlaced](on-connection-request-placed.md) | [androidJvm]<br>abstract fun [onConnectionRequestPlaced](on-connection-request-placed.md)()<br>A callback triggered when a request is placed to connect to a network. |
| [onFailureConnectingToNetwork](on-failure-connecting-to-network.md) | [androidJvm]<br>abstract fun [onFailureConnectingToNetwork](on-failure-connecting-to-network.md)()<br>A callback triggered when there is a failure connecting to a network. |
| [onNetworkNotFoundToConnectTo](on-network-not-found-to-connect-to.md) | [androidJvm]<br>abstract fun [onNetworkNotFoundToConnectTo](on-network-not-found-to-connect-to.md)()<br>A callback triggered when there is a no network found to connect to. |
| [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#823639724%2FFunctions%2F-1202619134) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#823639724%2FFunctions%2F-1202619134)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/-disconnect-request-sent/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
