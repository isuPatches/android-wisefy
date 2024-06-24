//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.callbacks](../index.md)/[AddNetworkCallbacks](index.md)

# AddNetworkCallbacks

interface [AddNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for adding a network.

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
| [equals](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onFailureAddingNetwork](on-failure-adding-network.md) | [androidJvm]<br>abstract fun [onFailureAddingNetwork](on-failure-adding-network.md)(result: [AddNetworkResult.Failure](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/index.md))<br>A callback triggered when there is a failure adding a network. |
| [onSuccessAddingNetwork](on-success-adding-network.md) | [androidJvm]<br>abstract fun [onSuccessAddingNetwork](on-success-adding-network.md)(result: [AddNetworkResult.Success](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-success/index.md))<br>A callback triggered when there is a success while adding a network. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-271260435) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-271260435)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
