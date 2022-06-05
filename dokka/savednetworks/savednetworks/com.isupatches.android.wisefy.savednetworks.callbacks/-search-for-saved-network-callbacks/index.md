//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.callbacks](../index.md)/[SearchForSavedNetworkCallbacks](index.md)

# SearchForSavedNetworkCallbacks

[androidJvm]\
interface [SearchForSavedNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks used while searching for a saved network on a device.

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
| [onSavedNetworkNotFound](on-saved-network-not-found.md) | [androidJvm]<br>abstract fun [onSavedNetworkNotFound](on-saved-network-not-found.md)()<br>A callback triggered when there is no saved network found. |
| [onSavedNetworkRetrieved](on-saved-network-retrieved.md) | [androidJvm]<br>abstract fun [onSavedNetworkRetrieved](on-saved-network-retrieved.md)(savedNetwork: [SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md))<br>A callback triggered when there is a matching saved network on the device. |
| [onWisefyAsyncFailure](../-search-for-saved-networks-callbacks/index.md#823639724%2FFunctions%2F-1378320381) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-saved-networks-callbacks/index.md#823639724%2FFunctions%2F-1378320381)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
