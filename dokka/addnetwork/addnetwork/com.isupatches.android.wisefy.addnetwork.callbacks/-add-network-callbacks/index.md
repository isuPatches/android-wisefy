//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.callbacks](../index.md)/[AddNetworkCallbacks](index.md)

# AddNetworkCallbacks

[androidJvm]\
interface [AddNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for adding a network.

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
| [onFailureAddingNetwork](on-failure-adding-network.md) | [androidJvm]<br>abstract fun [onFailureAddingNetwork](on-failure-adding-network.md)(result: [AddNetworkResult.Failure](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/index.md))<br>A callback triggered when there is a failure adding a network. |
| [onSuccessAddingNetwork](on-success-adding-network.md) | [androidJvm]<br>abstract fun [onSuccessAddingNetwork](on-success-adding-network.md)(result: [AddNetworkResult.Success](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-success/index.md))<br>A callback triggered when there is a success while adding a network. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F516743864) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F516743864)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
