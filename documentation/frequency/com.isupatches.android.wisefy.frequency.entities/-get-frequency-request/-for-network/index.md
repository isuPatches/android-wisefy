//[frequency](../../../../index.md)/[com.isupatches.android.wisefy.frequency.entities](../../index.md)/[GetFrequencyRequest](../index.md)/[ForNetwork](index.md)

# ForNetwork

[androidJvm]\
data class [ForNetwork](index.md)(val network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)) : [GetFrequencyRequest](../index.md)

A data representation of a request to get a given network's frequency.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [ForNetwork](-for-network.md) | [androidJvm]<br>fun [ForNetwork](-for-network.md)(network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)): [GetFrequencyRequest.ForNetwork](index.md) |
| [equals](../../-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846) | [androidJvm]<br>open operator override fun [equals](../../-is-network5g-hz-result/-false/index.md#585090901%2FFunctions%2F-831600846)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846) | [androidJvm]<br>open override fun [hashCode](../../-is-network5g-hz-result/-false/index.md#1794629105%2FFunctions%2F-831600846)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846) | [androidJvm]<br>open override fun [toString](../../-is-network5g-hz-result/-false/index.md#1616463040%2FFunctions%2F-831600846)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [network](network.md) | [androidJvm]<br>val [network](network.md): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)<br>The network to get the frequency of |
