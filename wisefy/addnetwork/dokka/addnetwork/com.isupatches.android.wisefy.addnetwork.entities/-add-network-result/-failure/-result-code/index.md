//[addnetwork](../../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../../index.md)/[AddNetworkResult](../../index.md)/[Failure](../index.md)/[ResultCode](index.md)

# ResultCode

data class [ResultCode](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Failure](../index.md)

A representation of a failure while attempting to add a network based on result codes returned from the Android OS.

*NOTE* This could be instances such as:

- 
   Returning -1 for the case of legacy wifiManager.addNetwork() https://developer.android.com/reference/android/net/wifi/WifiManager#addNetwork(android.net.wifi.WifiConfiguration))
- 
   Any of the failure codes for wifiManager.addNetworkSuggestions() for SDK 29 https://developer.android.com/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E)
- 
   As of Android 11, in-place modifications are allowed so there will be no STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE return

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AddNetworkResult.Failure](../index.md) |

## Constructors

| | |
|---|---|
| [ResultCode](-result-code.md) | [androidJvm]<br>constructor(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The raw value of the result code from the Android OS |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [AddNetworkResult.Failure.ResultCode](index.md) |
| [equals](../-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator override fun [equals](../-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [hashCode](../-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [toString](../-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
