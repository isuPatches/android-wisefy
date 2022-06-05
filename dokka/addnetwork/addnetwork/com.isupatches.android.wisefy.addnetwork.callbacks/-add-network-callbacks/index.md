//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.callbacks](../index.md)/[AddNetworkCallbacks](index.md)

# AddNetworkCallbacks

[androidJvm]\
interface [AddNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for adding a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onFailureAddingNetwork](on-failure-adding-network.md) | [androidJvm]<br>abstract fun [onFailureAddingNetwork](on-failure-adding-network.md)(result: [AddNetworkResult.Failure](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/index.md))<br>A callback triggered when there is a failure adding a network. |
| [onNetworkAdded](on-network-added.md) | [androidJvm]<br>abstract fun [onNetworkAdded](on-network-added.md)(result: [AddNetworkResult.Success](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-success/index.md))<br>A callback triggered when a success is returned for adding a network. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F516743864) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F516743864)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
