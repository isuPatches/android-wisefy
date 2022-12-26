//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi.callbacks](../index.md)/[DisableWifiCallbacks](index.md)

# DisableWifiCallbacks

[androidJvm]\
interface [DisableWifiCallbacks](index.md) : [BaseWisefyCallbacks](../../../../core/core/com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)

A set of callbacks for disabling wifi.

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
| [onFailureDisablingWifi](on-failure-disabling-wifi.md) | [androidJvm]<br>abstract fun [onFailureDisablingWifi](on-failure-disabling-wifi.md)(result: [DisableWifiResult.Failure](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/-failure/index.md))<br>A callback triggered when there is a failure disabling wifi. |
| [onSuccessDisablingWifi](on-success-disabling-wifi.md) | [androidJvm]<br>abstract fun [onSuccessDisablingWifi](on-success-disabling-wifi.md)(result: [DisableWifiResult.Success](../../com.isupatches.android.wisefy.wifi.entities/-disable-wifi-result/-success/index.md))<br>A callback triggered when wifi is successfully disabled. |
| [onWisefyAsyncFailure](../-is-wifi-enabled-callbacks/index.md#-2014443064%2FFunctions%2F-1859834656) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-is-wifi-enabled-callbacks/index.md#-2014443064%2FFunctions%2F-1859834656)(exception: [WisefyException](../../../../core/core/com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md)) |
