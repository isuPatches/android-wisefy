//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetCurrentNetworkCallbacks](index.md)

# GetCurrentNetworkCallbacks

[androidJvm]\
interface [GetCurrentNetworkCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving the device's current network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |

## Functions

| Name | Summary |
|---|---|
| [onCurrentNetworkRetrieved](on-current-network-retrieved.md) | [androidJvm]<br>abstract fun [onCurrentNetworkRetrieved](on-current-network-retrieved.md)(network: [NetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md))<br>A callback triggered when there is a success getting the device's current network. |
| [onWisefyAsyncFailure](../-get-network-connection-status-callbacks/index.md#-2014443064%2FFunctions%2F2126753235) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-get-network-connection-status-callbacks/index.md#-2014443064%2FFunctions%2F2126753235)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
