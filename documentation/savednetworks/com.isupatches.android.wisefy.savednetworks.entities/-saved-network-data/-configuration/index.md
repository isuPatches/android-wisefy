//[savednetworks](../../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../../index.md)/[SavedNetworkData](../index.md)/[Configuration](index.md)

# Configuration

[androidJvm]\
data class [Configuration](index.md)(val value: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)) : [SavedNetworkData](../index.md)

A data representation of a network configuration from Android OS level returns.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData](../index.md) |  |

## Constructors

| | |
|---|---|
| [Configuration](-configuration.md) | [androidJvm]<br>fun [Configuration](-configuration.md)(value: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(value: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)): [SavedNetworkData.Configuration](index.md) |
| [equals](../../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator override fun [equals](../../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [hashCode](../../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [toString](../../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [androidJvm]<br>val [value](value.md): [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)<br>The raw value of the saved network as a configuration from the Android OS |
