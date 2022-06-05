//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.callbacks](../index.md)/[GetRSSICallbacks](index.md)

# GetRSSICallbacks

[androidJvm]\
interface [GetRSSICallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving the RSSI level of a nearby access point.

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
| [onNoNetworkToRetrieveRSSI](on-no-network-to-retrieve-r-s-s-i.md) | [androidJvm]<br>abstract fun [onNoNetworkToRetrieveRSSI](on-no-network-to-retrieve-r-s-s-i.md)()<br>A callback triggered when there is no matching nearby access point to retrieve RSSI. |
| [onRSSIRetrieved](on-r-s-s-i-retrieved.md) | [androidJvm]<br>abstract fun [onRSSIRetrieved](on-r-s-s-i-retrieved.md)(rssi: [RSSIData](../../com.isupatches.android.wisefy.accesspoints.entities/-r-s-s-i-data/index.md))<br>A callback triggered when there is a matching access point to retrieve RSSI data. |
| [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-search-for-s-s-i-ds-callbacks/index.md#823639724%2FFunctions%2F2111858834)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
