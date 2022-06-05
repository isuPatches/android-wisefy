//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.callbacks](../index.md)/[GetSavedNetworksCallbacks](index.md)

# GetSavedNetworksCallbacks

[androidJvm]\
interface [GetSavedNetworksCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving the saved networks on a device.

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
| [onNoSavedNetworksFound](on-no-saved-networks-found.md) | [androidJvm]<br>abstract fun [onNoSavedNetworksFound](on-no-saved-networks-found.md)()<br>A callback triggered when there are no saved networks on the device. |
| [onSavedNetworksRetrieved](on-saved-networks-retrieved.md) | [androidJvm]<br>abstract fun [onSavedNetworksRetrieved](on-saved-networks-retrieved.md)(savedNetworks: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)&gt;)<br>A callback triggered when there is a success retrieving saved networks on the device. |
| [onWisefyAsyncFailure](../-search-for-saved-networks-callbacks/index.md#823639724%2FFunctions%2F-1378320381) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-saved-networks-callbacks/index.md#823639724%2FFunctions%2F-1378320381)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
