//[removenetwork](../../../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../../../index.md)/[RemoveNetworkResult](../../index.md)/[Success](../index.md)/[ResultCode](index.md)

# ResultCode

[androidJvm]\
data class [ResultCode](index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveNetworkResult.Success](../index.md)

A data representation of a success removing a network based on Android OS level returns.

*NOTE*

- 
   Returns for this are the same as removeNetworkSuggestion found here: https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E,%20int)

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult.Failure](../../-failure/index.md) |  |

## Constructors

| | |
|---|---|
| [ResultCode](-result-code.md) | [androidJvm]<br>fun [ResultCode](-result-code.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [RemoveNetworkResult.Success.ResultCode](index.md) |
| [equals](../../-failure/-result-code/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator override fun [equals](../../-failure/-result-code/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-failure/-result-code/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open override fun [hashCode](../../-failure/-result-code/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-failure/-result-code/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open override fun [toString](../../-failure/-result-code/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The raw value of the result code from the Android OS |
