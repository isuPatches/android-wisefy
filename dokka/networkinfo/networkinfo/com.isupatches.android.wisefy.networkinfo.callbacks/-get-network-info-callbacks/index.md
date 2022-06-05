//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetNetworkInfoCallbacks](index.md)

# GetNetworkInfoCallbacks

[androidJvm]\
interface [GetNetworkInfoCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving information about a network.

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
| [onNetworkInfoRetrieved](on-network-info-retrieved.md) | [androidJvm]<br>abstract fun [onNetworkInfoRetrieved](on-network-info-retrieved.md)(networkInfo: [NetworkInfoData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md))<br>A callback triggered when there is a success getting information for a network. |
| [onNoNetworkToRetrieveInfo](on-no-network-to-retrieve-info.md) | [androidJvm]<br>abstract fun [onNoNetworkToRetrieveInfo](on-no-network-to-retrieve-info.md)()<br>A callback triggered when there is no network found to retrieve information. |
| [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F2126753235) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#823639724%2FFunctions%2F2126753235)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
