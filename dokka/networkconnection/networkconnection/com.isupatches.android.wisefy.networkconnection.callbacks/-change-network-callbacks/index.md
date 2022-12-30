//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[ChangeNetworkCallbacks](index.md)

# ChangeNetworkCallbacks

[androidJvm]\
interface [ChangeNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for changing the current network.

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
| [onFailureChangingNetworks](on-failure-changing-networks.md) | [androidJvm]<br>abstract fun [onFailureChangingNetworks](on-failure-changing-networks.md)(result: [ChangeNetworkResult.Failure](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/-failure/index.md))<br>A callback triggered when there is a failure changing the current network. |
| [onSuccessChangingNetworks](on-success-changing-networks.md) | [androidJvm]<br>abstract fun [onSuccessChangingNetworks](on-success-changing-networks.md)(result: [ChangeNetworkResult.Success](../../com.isupatches.android.wisefy.networkconnection.entities/-change-network-result/-success/index.md))<br>A callback triggered when there is a success while changing the current network. |
| [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F1257109763) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-disconnect-from-current-network-callbacks/index.md#-2014443064%2FFunctions%2F1257109763)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
