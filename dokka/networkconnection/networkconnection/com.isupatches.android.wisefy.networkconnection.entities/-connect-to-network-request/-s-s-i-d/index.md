//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[ConnectToNetworkRequest](../index.md)/[SSID](index.md)

# SSID

[androidJvm]\
data class [SSID](index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [ConnectToNetworkRequest](../index.md)

A representation of a request to connect to a network by SSID.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [ConnectToNetworkRequest](../index.md) |

## Constructors

| | |
|---|---|
| [SSID](-s-s-i-d.md) | [androidJvm]<br>fun [SSID](-s-s-i-d.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The SSID of the network to connect to or disconnect from |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open override val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for a connection to the network |
