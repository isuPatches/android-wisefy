//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[ConnectToNetworkCallbacks](index.md)

# ConnectToNetworkCallbacks

interface [ConnectToNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for connecting to a network.

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
| [onFailureConnectingToNetwork](on-failure-connecting-to-network.md) | [androidJvm]<br>abstract fun [onFailureConnectingToNetwork](on-failure-connecting-to-network.md)(result: [ConnectToNetworkResult.Failure](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/-failure/index.md))<br>A callback triggered when there is a failure connecting to a network. |
| [onSuccessConnectingToNetwork](on-success-connecting-to-network.md) | [androidJvm]<br>abstract fun [onSuccessConnectingToNetwork](on-success-connecting-to-network.md)(result: [ConnectToNetworkResult.Success](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/-success/index.md))<br>A callback triggered when there is a success while connecting to a network. |
| [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F-1202619134) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F-1202619134)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
