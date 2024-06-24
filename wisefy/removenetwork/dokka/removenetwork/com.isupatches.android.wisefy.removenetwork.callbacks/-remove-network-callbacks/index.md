//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork.callbacks](../index.md)/[RemoveNetworkCallbacks](index.md)

# RemoveNetworkCallbacks

interface [RemoveNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for removing a network.

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
| [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onFailureRemovingNetwork](on-failure-removing-network.md) | [androidJvm]<br>abstract fun [onFailureRemovingNetwork](on-failure-removing-network.md)(result: [RemoveNetworkResult.Failure](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/index.md))<br>A callback triggered when there is a failure removing a network. |
| [onSuccessRemovingNetwork](on-success-removing-network.md) | [androidJvm]<br>abstract fun [onSuccessRemovingNetwork](on-success-removing-network.md)(result: [RemoveNetworkResult.Success](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-success/index.md))<br>A callback triggered when there is a success removing a network. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-2039424092) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-2039424092)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
