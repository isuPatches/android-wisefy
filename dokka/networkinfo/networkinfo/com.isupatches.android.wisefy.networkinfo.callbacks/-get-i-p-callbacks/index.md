//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.callbacks](../index.md)/[GetIPCallbacks](index.md)

# GetIPCallbacks

[androidJvm]\
interface [GetIPCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for retrieving the current device's IP.

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
| [onFailureRetrievingIP](on-failure-retrieving-i-p.md) | [androidJvm]<br>abstract fun [onFailureRetrievingIP](on-failure-retrieving-i-p.md)()<br>A callback triggered when there is failure retrieving the device's IP. |
| [onIPRetrieved](on-i-p-retrieved.md) | [androidJvm]<br>abstract fun [onIPRetrieved](on-i-p-retrieved.md)(ip: [IPData](../../com.isupatches.android.wisefy.networkinfo.entities/-i-p-data/index.md))<br>A callback triggered when there is a success getting the device's IP. |
| [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F2126753235) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-get-network-info-callbacks/index.md#823639724%2FFunctions%2F2126753235)(throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
