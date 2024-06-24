//[wifi](../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../index.md)/[EnableWifiRequest](index.md)

# EnableWifiRequest

sealed class [EnableWifiRequest](index.md)

A set of classes and objects that represent requests to enable wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [Default](-default/index.md) |
| [Android29OrAbove](-android29-or-above/index.md) |

## Types

| Name | Summary |
|---|---|
| [Android29OrAbove](-android29-or-above/index.md) | [androidJvm]<br>data class [Android29OrAbove](-android29-or-above/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [EnableWifiRequest](index.md)<br>A representation of a request to enable wifi on an Android Q / SDK 29 or higher device. |
| [Default](-default/index.md) | [androidJvm]<br>object [Default](-default/index.md) : [EnableWifiRequest](index.md)<br>A representation of a request to enable wifi on a device before Android Q / SDK 29. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
