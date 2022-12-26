//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../index.md)/[EnableWifiRequest](index.md)

# EnableWifiRequest

[androidJvm]\
sealed class [EnableWifiRequest](index.md)

A set of classes and objects that represent requests to enable wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [Android29OrAbove](-android29-or-above/index.md) | [androidJvm]<br>data class [Android29OrAbove](-android29-or-above/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [EnableWifiRequest](index.md)<br>A representation of a request to enable wifi on an Android Q / SDK 29 or higher device. |
| [Default](-default/index.md) | [androidJvm]<br>object [Default](-default/index.md) : [EnableWifiRequest](index.md)<br>A representation of a request to enable wifi on a device before Android Q / SDK 29. |

## Inheritors

| Name |
|---|
| [Default](-default/index.md) |
| [Android29OrAbove](-android29-or-above/index.md) |
