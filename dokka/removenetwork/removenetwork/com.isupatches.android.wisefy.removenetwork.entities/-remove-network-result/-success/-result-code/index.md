//[removenetwork](../../../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../../../index.md)/[RemoveNetworkResult](../../index.md)/[Success](../index.md)/[ResultCode](index.md)

# ResultCode

[androidJvm]\
data class [ResultCode](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveNetworkResult.Success](../index.md)

A data representation of a success removing a network based on Android OS level returns.

*NOTE*

- 
   Returns for this are the same as `removeNetworkSuggestion` found here: https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E,%20int)

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [RemoveNetworkResult.Success](../index.md) |

## Constructors

| | |
|---|---|
| [ResultCode](-result-code.md) | [androidJvm]<br>fun [ResultCode](-result-code.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The raw value of the result code from the Android OS |
