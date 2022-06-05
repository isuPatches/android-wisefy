//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../index.md)/[NetworkConnectionRequest](index.md)

# NetworkConnectionRequest

[androidJvm]\
sealed class [NetworkConnectionRequest](index.md)

A set of classes that are used in requests to connect and disconnect from a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [NetworkConnectionRequest](index.md)<br>A data representation of a request to connect or disconnect from a network by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [NetworkConnectionRequest](index.md)<br>A data representation of a request to connect to or disconnect from a network by SSID. |

## Properties

| Name | Summary |
|---|---|
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for a connection/disconnection for the network |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
