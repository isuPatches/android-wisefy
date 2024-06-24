//[addnetwork](../../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../../index.md)/[AddNetworkResult](../../index.md)/[Success](../index.md)/[ResultCode](index.md)

# ResultCode

data class [ResultCode](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Success](../index.md)

A representation of a success while attempting to add a network based on result codes returned from the Android OS.

*NOTES* This could be instances such as:

- 
   Returning the id of the new network for the case of legacy wifiManager.addNetwork() https://developer.android.com/reference/android/net/wifi/WifiManager#addNetwork(android.net.wifi.WifiConfiguration))
- 
   STATUS_NETWORK_SUGGESTIONS_SUCCESS for SDK 29 https://developer.android.com/reference/android/net/wifi/WifiManager#STATUS_NETWORK_SUGGESTIONS_SUCCESS
- 
   As of Android 11, in-place modifications are allowed so there will be no STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE return

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AddNetworkResult.Success](../index.md) |

## Constructors

| | |
|---|---|
| [ResultCode](-result-code.md) | [androidJvm]<br>constructor(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The value of the result code from the Android OS |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [AddNetworkResult.Success.ResultCode](index.md) |
| [equals](../../-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator override fun [equals](../../-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [hashCode](../../-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [toString](../../-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
