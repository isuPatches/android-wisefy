//[ktx](../../index.md)/[com.isupatches.android.wisefy.ktx](index.md)/[isWifiEnabledAsync](is-wifi-enabled-async.md)

# isWifiEnabledAsync

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)

suspend fun WisefyApi.[isWifiEnabledAsync](is-wifi-enabled-async.md)(query: IsWifiEnabledQuery = IsWifiEnabledQuery()): IsWifiEnabledResult

A coroutine extension for checking the current state of wifi.

*NOTES*

- 
   Locked by the wifiMutex along with functions for enabling and disabling wifi

#### Receiver

WisefyApi

#### Return

IsWifiEnabledResult - The result of checking if wifi is enabled

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| query | The details of the query to check the current state of wifi |

#### See also

| |
|---|
| IsWifiEnabledQuery |
| IsWifiEnabledResult |

#### Throws

| |
|---|
| WisefyException |
