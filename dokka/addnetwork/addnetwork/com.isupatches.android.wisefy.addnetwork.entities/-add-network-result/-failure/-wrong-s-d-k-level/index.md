//[addnetwork](../../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../../index.md)/[AddNetworkResult](../../index.md)/[Failure](../index.md)/[WrongSDKLevel](index.md)

# WrongSDKLevel

[androidJvm]\
data class [WrongSDKLevel](index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddNetworkResult.Failure](../index.md)

A data representation of a failure to add a network due to a function being called for the wrong SDK level.

*NOTE* This is a developer specific use case to catch instances within Wisefy that proxy to the wrong SDK level.  This should NEVER actually be hit and if it is, it is a bug.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult.Failure](../index.md) |  |

## Constructors

| | |
|---|---|
| [WrongSDKLevel](-wrong-s-d-k-level.md) | [androidJvm]<br>fun [WrongSDKLevel](-wrong-s-d-k-level.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [message](message.md) | [androidJvm]<br>val [message](message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A text description describing the SDK level and error hit |
