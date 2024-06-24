//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[ChangeNetworkCallbacks](index.md)

# ChangeNetworkCallbacks

interface [ChangeNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for changing the current network.

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
| [onFailureChangingNetworks](on-failure-changing-networks.md) | [androidJvm]<br>abstract fun [onFailureChangingNetworks](on-failure-changing-networks.md)(result: [ChangeNetworkResult.Failure](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/-failure/index.md))<br>A callback triggered when there is a failure changing the current network. |
| [onSuccessChangingNetworks](on-success-changing-networks.md) | [androidJvm]<br>abstract fun [onSuccessChangingNetworks](on-success-changing-networks.md)(result: [ChangeNetworkResult.Success](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/-success/index.md))<br>A callback triggered when there is a success while changing the current network. |
| [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F-1202619134) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F-1202619134)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
