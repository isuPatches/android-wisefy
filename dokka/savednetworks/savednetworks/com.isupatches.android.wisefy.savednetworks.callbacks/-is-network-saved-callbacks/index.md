//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.callbacks](../index.md)/[IsNetworkSavedCallbacks](index.md)

# IsNetworkSavedCallbacks

[androidJvm]\
interface [IsNetworkSavedCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks triggered while checking if a network is saved on the device.

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
| [onNetworkIsNotSaved](on-network-is-not-saved.md) | [androidJvm]<br>abstract fun [onNetworkIsNotSaved](on-network-is-not-saved.md)()<br>A callback triggered when there is no matching saved network. |
| [onNetworkIsSaved](on-network-is-saved.md) | [androidJvm]<br>abstract fun [onNetworkIsSaved](on-network-is-saved.md)()<br>A callback triggered when there are matching saved networks. |
| [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-1378320381) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](index.md#-2014443064%2FFunctions%2F-1378320381)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
