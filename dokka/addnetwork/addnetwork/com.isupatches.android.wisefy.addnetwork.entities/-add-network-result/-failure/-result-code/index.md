//[addnetwork](../../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../../index.md)/[AddNetworkResult](../../index.md)/[Failure](../index.md)/[ResultCode](index.md)

# ResultCode

[androidJvm]\
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

androidJvm

| |
|---|
| [AddNetworkResult.Failure](../index.md) |

## Constructors

| | |
|---|---|
| [ResultCode](-result-code.md) | [androidJvm]<br>fun [ResultCode](-result-code.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The raw value of the result code from the Android OS |
