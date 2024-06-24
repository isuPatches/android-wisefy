//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.callbacks](../index.md)/[IsNetworkSavedCallbacks](index.md)

# IsNetworkSavedCallbacks

interface [IsNetworkSavedCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks triggered while checking if a network is saved on the device.

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
| [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onNetworkIsNotSaved](on-network-is-not-saved.md) | [androidJvm]<br>abstract fun [onNetworkIsNotSaved](on-network-is-not-saved.md)()<br>A callback triggered when there is no matching saved network. |
| [onNetworkIsSaved](on-network-is-saved.md) | [androidJvm]<br>abstract fun [onNetworkIsSaved](on-network-is-saved.md)()<br>A callback triggered when there are matching saved networks. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F656463362) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F656463362)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
