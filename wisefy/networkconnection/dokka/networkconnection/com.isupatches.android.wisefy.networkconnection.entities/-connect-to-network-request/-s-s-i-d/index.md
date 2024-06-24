//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[ConnectToNetworkRequest](../index.md)/[SSID](index.md)

# SSID

data class [SSID](index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [ConnectToNetworkRequest](../index.md)

A representation of a request to connect to a network by SSID.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [ConnectToNetworkRequest](../index.md) |

## Constructors

| | |
|---|---|
| [SSID](-s-s-i-d.md) | [androidJvm]<br>constructor(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The SSID of the network to connect to or disconnect from |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open override val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for a connection to the network |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ConnectToNetworkRequest.SSID](index.md) |
| [equals](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator override fun [equals](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open override fun [hashCode](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open override fun [toString](../../-disconnect-from-current-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
