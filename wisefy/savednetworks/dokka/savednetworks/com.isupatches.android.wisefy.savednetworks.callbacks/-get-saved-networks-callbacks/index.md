//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.callbacks](../index.md)/[GetSavedNetworksCallbacks](index.md)

# GetSavedNetworksCallbacks

interface [GetSavedNetworksCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving all of the saved networks on a device.

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
| [onNoSavedNetworksFound](on-no-saved-networks-found.md) | [androidJvm]<br>abstract fun [onNoSavedNetworksFound](on-no-saved-networks-found.md)()<br>A callback triggered when there are no saved networks on the device. |
| [onSavedNetworksRetrieved](on-saved-networks-retrieved.md) | [androidJvm]<br>abstract fun [onSavedNetworksRetrieved](on-saved-networks-retrieved.md)(savedNetworks: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)&gt;)<br>A callback triggered when there is a success retrieving all of the saved networks on the device. |
| [onWisefyAsyncFailure](../-is-network-saved-callbacks/index.md#-2014443064%2FFunctions%2F656463362) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-is-network-saved-callbacks/index.md#-2014443064%2FFunctions%2F656463362)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
