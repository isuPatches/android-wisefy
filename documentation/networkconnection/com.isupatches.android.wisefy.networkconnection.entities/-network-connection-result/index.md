//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../index.md)/[NetworkConnectionResult](index.md)

# NetworkConnectionResult

[androidJvm]\
sealed class [NetworkConnectionResult](index.md)

A set of classes that are data representations of a result when connecting to or disconnecting from a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Boolean](-boolean/index.md) | [androidJvm]<br>sealed class [Boolean](-boolean/index.md) : [NetworkConnectionResult](index.md)<br>A set of classes that are data representations of a boolean result when connecting to or disconnecting from a network. |
| [ConnectionRequestSent](-connection-request-sent/index.md) | [androidJvm]<br>object [ConnectionRequestSent](-connection-request-sent/index.md) : [NetworkConnectionResult](index.md)<br>A data representation for when there is a request sent to connecting to a network. |
| [DisconnectRequestSent](-disconnect-request-sent/index.md) | [androidJvm]<br>object [DisconnectRequestSent](-disconnect-request-sent/index.md) : [NetworkConnectionResult](index.md)<br>A data representation for when there is a request sent to disconnect from a network. |
| [NetworkNotFound](-network-not-found/index.md) | [androidJvm]<br>object [NetworkNotFound](-network-not-found/index.md) : [NetworkConnectionResult](index.md)<br>A data representation for when there is no network found to connect to or disconnect from. |

## Functions

| Name | Summary |
|---|---|
| [equals](-disconnect-request-sent/index.md#585090901%2FFunctions%2F-1202619134) | [androidJvm]<br>open operator fun [equals](-disconnect-request-sent/index.md#585090901%2FFunctions%2F-1202619134)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](-disconnect-request-sent/index.md#1794629105%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [hashCode](-disconnect-request-sent/index.md#1794629105%2FFunctions%2F-1202619134)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](-disconnect-request-sent/index.md#1616463040%2FFunctions%2F-1202619134) | [androidJvm]<br>open fun [toString](-disconnect-request-sent/index.md#1616463040%2FFunctions%2F-1202619134)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Boolean](-boolean/index.md) |
| [ConnectionRequestSent](-connection-request-sent/index.md) |
| [NetworkNotFound](-network-not-found/index.md) |
| [DisconnectRequestSent](-disconnect-request-sent/index.md) |
