//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetCurrentNetworkCallbacks](index.md)

# GetCurrentNetworkCallbacks

interface [GetCurrentNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving the device's current network.

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
| [onCurrentNetworkRetrieved](on-current-network-retrieved.md) | [androidJvm]<br>abstract fun [onCurrentNetworkRetrieved](on-current-network-retrieved.md)(network: [NetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md))<br>A callback triggered when there is a success getting the device's current network. |
| [onWisefyAsyncFailure](../-get-network-connection-status-callbacks/index.md#-2014443064%2FFunctions%2F373461554) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-get-network-connection-status-callbacks/index.md#-2014443064%2FFunctions%2F373461554)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
