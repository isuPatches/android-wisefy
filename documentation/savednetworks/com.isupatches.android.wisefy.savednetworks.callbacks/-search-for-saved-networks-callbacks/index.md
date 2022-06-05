//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.callbacks](../index.md)/[SearchForSavedNetworksCallbacks](index.md)

# SearchForSavedNetworksCallbacks

[androidJvm]\
interface [SearchForSavedNetworksCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks used while searching for saved networks on a device.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks |  |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onNoSavedNetworksFound](on-no-saved-networks-found.md) | [androidJvm]<br>abstract fun [onNoSavedNetworksFound](on-no-saved-networks-found.md)()<br>A callback triggered when there are saved networks found. |
| [onSavedNetworksRetrieved](on-saved-networks-retrieved.md) | [androidJvm]<br>abstract fun [onSavedNetworksRetrieved](on-saved-networks-retrieved.md)(savedNetworks: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)&gt;)<br>A callback triggered when there are matching saved networks on the device. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F656463362) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F656463362)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
