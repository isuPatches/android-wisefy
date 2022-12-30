//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork.callbacks](../index.md)/[RemoveNetworkCallbacks](index.md)

# RemoveNetworkCallbacks

[androidJvm]\
interface [RemoveNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for removing a network.

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
| [onFailureRemovingNetwork](on-failure-removing-network.md) | [androidJvm]<br>abstract fun [onFailureRemovingNetwork](on-failure-removing-network.md)(result: [RemoveNetworkResult.Failure](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/index.md))<br>A callback triggered when there is a failure removing a network. |
| [onSuccessRemovingNetwork](on-success-removing-network.md) | [androidJvm]<br>abstract fun [onSuccessRemovingNetwork](on-success-removing-network.md)(result: [RemoveNetworkResult.Success](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-success/index.md))<br>A callback triggered when there is a success removing a network. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-1763229663) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-1763229663)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
