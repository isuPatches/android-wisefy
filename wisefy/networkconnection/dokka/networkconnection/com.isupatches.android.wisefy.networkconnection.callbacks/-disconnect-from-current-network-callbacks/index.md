//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[DisconnectFromCurrentNetworkCallbacks](index.md)

# DisconnectFromCurrentNetworkCallbacks

interface [DisconnectFromCurrentNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for disconnecting from the current network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| BaseWisefyCallbacks |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md) | [androidJvm]<br>abstract fun [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md)(result: [DisconnectFromCurrentNetworkResult.Failure](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/index.md))<br>A callback triggered when there is a failure disconnecting from the current network. |
| [onSuccessDisconnectingFromCurrentNetwork](on-success-disconnecting-from-current-network.md) | [androidJvm]<br>abstract fun [onSuccessDisconnectingFromCurrentNetwork](on-success-disconnecting-from-current-network.md)(result: [DisconnectFromCurrentNetworkResult.Success](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-success/index.md))<br>A callback triggered when there is a success while disconnecting from the current network. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-1202619134) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-1202619134)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
