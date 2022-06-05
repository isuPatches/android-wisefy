//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetCurrentNetworkCallbacks](index.md)

# GetCurrentNetworkCallbacks

[androidJvm]\
interface [GetCurrentNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving the device's current network.

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
| [onCurrentNetworkRetrieved](on-current-network-retrieved.md) | [androidJvm]<br>abstract fun [onCurrentNetworkRetrieved](on-current-network-retrieved.md)(network: [NetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md))<br>A callback triggered when there is a success getting the device's current network. |
| [onNoCurrentNetwork](on-no-current-network.md) | [androidJvm]<br>abstract fun [onNoCurrentNetwork](on-no-current-network.md)()<br>A callback triggered when the device has no current network. |
| [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F2126753235) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F2126753235)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
