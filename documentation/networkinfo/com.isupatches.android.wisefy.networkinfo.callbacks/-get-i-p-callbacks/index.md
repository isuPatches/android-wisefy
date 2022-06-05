//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetIPCallbacks](index.md)

# GetIPCallbacks

[androidJvm]\
interface [GetIPCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for retrieving the current device's IP.

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
| [onFailureRetrievingIP](on-failure-retrieving-i-p.md) | [androidJvm]<br>abstract fun [onFailureRetrievingIP](on-failure-retrieving-i-p.md)()<br>A callback triggered when there is failure retrieving the device's IP. |
| [onIPRetrieved](on-i-p-retrieved.md) | [androidJvm]<br>abstract fun [onIPRetrieved](on-i-p-retrieved.md)(ip: [IPData](../../com.isupatches.android.wisefy.networkinfo.entities/-i-p-data/index.md))<br>A callback triggered when there is a success getting the device's IP. |
| [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F373461554) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F373461554)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.networkinfo.entities/-network-info-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
