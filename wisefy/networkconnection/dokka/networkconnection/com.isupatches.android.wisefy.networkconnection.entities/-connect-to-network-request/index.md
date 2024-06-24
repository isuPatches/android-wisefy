//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../index.md)/[ConnectToNetworkRequest](index.md)

# ConnectToNetworkRequest

sealed class [ConnectToNetworkRequest](index.md)

A set of classes and objects that are used in requests to connect to a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [ConnectToNetworkRequest](index.md)<br>A representation of a request to connect to a network by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [ConnectToNetworkRequest](index.md)<br>A representation of a request to connect to a network by SSID. |

## Properties

| Name | Summary |
|---|---|
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for a connection to the network |

## Functions

| Name | Summary |
|---|---|
| [equals](../-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](../-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](../-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](../-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
