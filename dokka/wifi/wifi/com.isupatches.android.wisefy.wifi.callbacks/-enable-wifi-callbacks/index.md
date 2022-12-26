//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi.callbacks](../index.md)/[EnableWifiCallbacks](index.md)

# EnableWifiCallbacks

[androidJvm]\
interface [EnableWifiCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for enabling wifi.

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
| [onFailureEnablingWifi](on-failure-enabling-wifi.md) | [androidJvm]<br>abstract fun [onFailureEnablingWifi](on-failure-enabling-wifi.md)(result: [EnableWifiResult.Failure](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/-failure/index.md))<br>A callback triggered when there is a failure enabling wifi. |
| [onSuccessEnablingWifi](on-success-enabling-wifi.md) | [androidJvm]<br>abstract fun [onSuccessEnablingWifi](on-success-enabling-wifi.md)(result: [EnableWifiResult.Success](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/-success/index.md))<br>A callback triggered when wifi is successfully enabled. |
| [onWisefyAsyncFailure](../-is-wifi-enabled-callbacks/index.md#-2014443064%2FFunctions%2F-1859834656) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-is-wifi-enabled-callbacks/index.md#-2014443064%2FFunctions%2F-1859834656)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
