//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[ConnectToNetworkCallbacks](index.md)

# ConnectToNetworkCallbacks

[androidJvm]\
interface [ConnectToNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for connecting to a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |

## Functions

| Name | Summary |
|---|---|
| [onFailureConnectingToNetwork](on-failure-connecting-to-network.md) | [androidJvm]<br>abstract fun [onFailureConnectingToNetwork](on-failure-connecting-to-network.md)(result: [ConnectToNetworkResult.Failure](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/-failure/index.md))<br>A callback triggered when there is a failure connecting to a network. |
| [onSuccessConnectingToNetwork](on-success-connecting-to-network.md) | [androidJvm]<br>abstract fun [onSuccessConnectingToNetwork](on-success-connecting-to-network.md)(result: [ConnectToNetworkResult.Success](../../com.isupatches.android.wisefy.networkconnection.entities/-connect-to-network-result/-success/index.md))<br>A callback triggered when there is a success while connecting to a network. |
| [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F1257109763) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F1257109763)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
