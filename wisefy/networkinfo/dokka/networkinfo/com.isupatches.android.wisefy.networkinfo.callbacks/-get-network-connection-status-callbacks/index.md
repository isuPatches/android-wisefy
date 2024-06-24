//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetNetworkConnectionStatusCallbacks](index.md)

# GetNetworkConnectionStatusCallbacks

interface [GetNetworkConnectionStatusCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving the device's current network connection status.

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
| [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onDeviceNetworkConnectionStatusRetrieved](on-device-network-connection-status-retrieved.md) | [androidJvm]<br>abstract fun [onDeviceNetworkConnectionStatusRetrieved](on-device-network-connection-status-retrieved.md)(networkConnectionStatus: [NetworkConnectionStatusData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-connection-status-data/index.md))<br>A callback triggered when there is a success getting the device's current network connection status. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F373461554) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F373461554)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
