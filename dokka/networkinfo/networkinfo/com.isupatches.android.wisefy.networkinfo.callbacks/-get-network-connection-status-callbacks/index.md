//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetNetworkConnectionStatusCallbacks](index.md)

# GetNetworkConnectionStatusCallbacks

[androidJvm]\
interface [GetNetworkConnectionStatusCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving the device's current network connection status.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [onDeviceNetworkConnectionStatusRetrieved](on-device-network-connection-status-retrieved.md) | [androidJvm]<br>abstract fun [onDeviceNetworkConnectionStatusRetrieved](on-device-network-connection-status-retrieved.md)(networkConnectionStatus: [NetworkConnectionStatusData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-connection-status-data/index.md))<br>A callback triggered when there is a success getting the device's current network connection status. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F2126753235) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F2126753235)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
