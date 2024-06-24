//[wifi](../../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../../index.md)/[EnableWifiResult](../index.md)/[Success](index.md)

# Success

sealed class [Success](index.md) : [EnableWifiResult](../index.md)

A data representation for when there is a success enabling Wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [EnableWifiResult](../index.md) |

#### Inheritors

| |
|---|
| [Enabled](-enabled/index.md) |
| [WifiSettingScreenOpened](-wifi-setting-screen-opened/index.md) |

## Types

| Name | Summary |
|---|---|
| [Enabled](-enabled/index.md) | [androidJvm]<br>object [Enabled](-enabled/index.md) : [EnableWifiResult.Success](index.md)<br>A representation of when wifi is successfully enable on pre-Android Q / SDK 29 devices. |
| [WifiSettingScreenOpened](-wifi-setting-screen-opened/index.md) | [androidJvm]<br>object [WifiSettingScreenOpened](-wifi-setting-screen-opened/index.md) : [EnableWifiResult.Success](index.md)<br>A representation of when the wifi settings screen is opened on Android Q / SDK 29 or higher devices for the user to manually enable wifi. |

## Functions

| Name | Summary |
|---|---|
| [equals](../../-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../../-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../../-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../../-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
