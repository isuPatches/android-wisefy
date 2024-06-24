//[savednetworks](../../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../../index.md)/[SavedNetworkData](../index.md)/[Configuration](index.md)

# Configuration

data class [Configuration](index.md)(val rawValue: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)) : [SavedNetworkData](../index.md)

A data representation of a saved network configuration prior to Android Q.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [SavedNetworkData](../index.md) |

## Constructors

| | |
|---|---|
| [Configuration](-configuration.md) | [androidJvm]<br>constructor(rawValue: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)) |

## Properties

| Name | Summary |
|---|---|
| [rawValue](raw-value.md) | [androidJvm]<br>val [rawValue](raw-value.md): [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)<br>The raw value of the saved network as a configuration |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(rawValue: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)): [SavedNetworkData.Configuration](index.md) |
| [equals](../-suggestion/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator override fun [equals](../-suggestion/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-suggestion/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [hashCode](../-suggestion/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-suggestion/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [toString](../-suggestion/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
