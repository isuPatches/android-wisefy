//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[NetworkConnectionRequest](../index.md)/[BSSID](index.md)

# BSSID

[androidJvm]\
data class [BSSID](index.md)(val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [NetworkConnectionRequest](../index.md)

A data representation of a request to connect or disconnect from a network by BSSID.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [BSSID](-b-s-s-i-d.md) | [androidJvm]<br>fun [BSSID](-b-s-s-i-d.md)(bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [bssid](bssid.md) | [androidJvm]<br>val [bssid](bssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The BSSID of the network to connect to or disconnect from |
| [timeoutInMillis](timeout-in-millis.md) | [androidJvm]<br>open override val [timeoutInMillis](timeout-in-millis.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The timeout in milliseconds to wait for a connection/disconnection for the network |
