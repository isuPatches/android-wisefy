//[wifi](../../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../../index.md)/[DisableWifiResult](../index.md)/[Success](index.md)

# Success

[androidJvm]\
sealed class [Success](index.md) : [DisableWifiResult](../index.md)

A data representation for when there is a success disabling Wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [DisableWifiResult](../index.md) |

## Types

| Name | Summary |
|---|---|
| [Disabled](-disabled/index.md) | [androidJvm]<br>object [Disabled](-disabled/index.md) : [DisableWifiResult.Success](index.md)<br>A representation of when wifi is successfully disabled on pre-Android Q / SDK 29 devices. |
| [WifiSettingScreenOpened](-wifi-setting-screen-opened/index.md) | [androidJvm]<br>object [WifiSettingScreenOpened](-wifi-setting-screen-opened/index.md) : [DisableWifiResult.Success](index.md)<br>A representation of when the wifi settings screen is opened on Android Q / SDK 29 or higher devices for the user to manually disable wifi. |

## Inheritors

| Name |
|---|
| [Disabled](-disabled/index.md) |
| [WifiSettingScreenOpened](-wifi-setting-screen-opened/index.md) |
