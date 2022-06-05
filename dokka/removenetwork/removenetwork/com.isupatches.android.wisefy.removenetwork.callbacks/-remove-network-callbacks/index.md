//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork.callbacks](../index.md)/[RemoveNetworkCallbacks](index.md)

# RemoveNetworkCallbacks

[androidJvm]\
interface [RemoveNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for removing a network.

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
| [onFailureRemovingNetwork](on-failure-removing-network.md) | [androidJvm]<br>abstract fun [onFailureRemovingNetwork](on-failure-removing-network.md)(result: [RemoveNetworkResult.Failure](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/index.md))<br>A callback triggered when there is a failure removing a network. |
| [onNetworkNotFoundToRemove](on-network-not-found-to-remove.md) | [androidJvm]<br>abstract fun [onNetworkNotFoundToRemove](on-network-not-found-to-remove.md)()<br>A callback triggered when the network is not found to remove. |
| [onNetworkRemoved](on-network-removed.md) | [androidJvm]<br>abstract fun [onNetworkRemoved](on-network-removed.md)(result: [RemoveNetworkResult.Success](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-success/index.md))<br>A callback triggered when there is a success removing a network. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F-1763229663) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F-1763229663)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
