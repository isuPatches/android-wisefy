//[addnetwork](../../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../../index.md)/[AddNetworkResult](../../index.md)/[Success](../index.md)/[ResultCode](index.md)

# ResultCode

[androidJvm]\
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

androidJvm

| |
|---|
| [AddNetworkResult.Success](../index.md) |

## Constructors

| | |
|---|---|
| [ResultCode](-result-code.md) | [androidJvm]<br>fun [ResultCode](-result-code.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The value of the result code from the Android OS |
