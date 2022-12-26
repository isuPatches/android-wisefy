//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../index.md)/[DisableWifiRequest](index.md)

# DisableWifiRequest

[androidJvm]\
sealed class [DisableWifiRequest](index.md)

A set of classes and objects that represent requests to disable wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [Android29OrAbove](-android29-or-above/index.md) | [androidJvm]<br>data class [Android29OrAbove](-android29-or-above/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [DisableWifiRequest](index.md)<br>A representation of a request to disable wifi on an Android Q / SDK 29 or higher device. |
| [Default](-default/index.md) | [androidJvm]<br>object [Default](-default/index.md) : [DisableWifiRequest](index.md)<br>A representation of a request to disable wifi on a device before Android Q / SDK 29. |

## Inheritors

| Name |
|---|
| [Default](-default/index.md) |
| [Android29OrAbove](-android29-or-above/index.md) |
