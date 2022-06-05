//[signal](../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../index.md)/[CalculateBarsRequest](../index.md)/[Android30AndAbove](index.md)

# Android30AndAbove

[androidJvm]\
@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

data class [Android30AndAbove](index.md)(val rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CalculateBarsRequest](../index.md)

A data representation of a request on devices above Android 30 to calculate the number of signal bars based on an RSSI level.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.entities.CalculateBarsRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [Android30AndAbove](-android30-and-above.md) | [androidJvm]<br>fun [Android30AndAbove](-android30-and-above.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [rssiLevel](rssi-level.md) | [androidJvm]<br>val [rssiLevel](rssi-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The RSSI level of the network |
