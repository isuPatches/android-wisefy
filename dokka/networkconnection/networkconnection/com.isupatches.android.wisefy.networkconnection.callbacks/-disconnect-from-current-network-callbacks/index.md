//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.callbacks](../index.md)/[DisconnectFromCurrentNetworkCallbacks](index.md)

# DisconnectFromCurrentNetworkCallbacks

[androidJvm]\
interface [DisconnectFromCurrentNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for disconnecting from the current network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md) | [androidJvm]<br>abstract fun [onFailureDisconnectingFromCurrentNetwork](on-failure-disconnecting-from-current-network.md)(result: [DisconnectFromCurrentNetworkResult.Failure](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-failure/index.md))<br>A callback triggered when there is a failure disconnecting from the current network. |
| [onSuccessDisconnectingFromCurrentNetwork](on-success-disconnecting-from-current-network.md) | [androidJvm]<br>abstract fun [onSuccessDisconnectingFromCurrentNetwork](on-success-disconnecting-from-current-network.md)(result: [DisconnectFromCurrentNetworkResult.Success](../../com.isupatches.android.wisefy.networkconnection.entities/-disconnect-from-current-network-result/-success/index.md))<br>A callback triggered when there is a success while disconnecting from the current network. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F1257109763) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F1257109763)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
