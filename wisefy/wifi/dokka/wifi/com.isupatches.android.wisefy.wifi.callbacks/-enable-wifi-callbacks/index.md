//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi.callbacks](../index.md)/[EnableWifiCallbacks](index.md)

# EnableWifiCallbacks

interface [EnableWifiCallbacks](index.md) : BaseWisefyCallbacks

A set of callbacks for enabling wifi.

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
| [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onFailureEnablingWifi](on-failure-enabling-wifi.md) | [androidJvm]<br>abstract fun [onFailureEnablingWifi](on-failure-enabling-wifi.md)(result: [EnableWifiResult.Failure](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/-failure/index.md))<br>A callback triggered when there is a failure enabling wifi. |
| [onSuccessEnablingWifi](on-success-enabling-wifi.md) | [androidJvm]<br>abstract fun [onSuccessEnablingWifi](on-success-enabling-wifi.md)(result: [EnableWifiResult.Success](../../com.isupatches.android.wisefy.wifi.entities/-enable-wifi-result/-success/index.md))<br>A callback triggered when wifi is successfully enabled. |
| [onWisefyAsyncFailure](../-is-wifi-enabled-callbacks/index.md#-2014443064%2FFunctions%2F-130402363) | [androidJvm]<br>abstract fun [onWisefyAsyncFailure](../-is-wifi-enabled-callbacks/index.md#-2014443064%2FFunctions%2F-130402363)(exception: WisefyException) |
| [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.entities/-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
