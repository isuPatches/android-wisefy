//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetCurrentNetworkCallbacks](index.md)

# GetCurrentNetworkCallbacks

[androidJvm]\
interface [GetCurrentNetworkCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving the device's current network.

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
| [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onCurrentNetworkRetrieved](on-current-network-retrieved.md) | [androidJvm]<br>abstract fun [onCurrentNetworkRetrieved](on-current-network-retrieved.md)(network: [NetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-network-data/index.md))<br>A callback triggered when there is a success getting the device's current network. |
| [onNoCurrentNetwork](on-no-current-network.md) | [androidJvm]<br>abstract fun [onNoCurrentNetwork](on-no-current-network.md)()<br>A callback triggered when the device has no current network. |
| [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F373461554) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F373461554)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
