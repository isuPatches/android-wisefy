//[core](../../index.md)/[com.isupatches.android.wisefy.core](index.md)/[hasSSIDMatchingRegex](has-s-s-i-d-matching-regex.md)

# hasSSIDMatchingRegex

[androidJvm]\
fun [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html).[hasSSIDMatchingRegex](has-s-s-i-d-matching-regex.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to determine if the SSID for the [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html) matches a given regex.

#### Return

Boolean - True if the SSID of the [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html) matches, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| regex | The regex to check if the SSID of the access point matches |

[androidJvm]\
fun [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html).[hasSSIDMatchingRegex](has-s-s-i-d-matching-regex.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to determine if the SSID for the [android.net.wifi.WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html) matches a given regex.

#### Return

Boolean - True if the SSID of the [android.net.wifi.WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html) matches, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| regex | The regex to check if the SSID of the access point matches |

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

fun [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html).[hasSSIDMatchingRegex](has-s-s-i-d-matching-regex.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to determine if the SSID for the [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) matches a given regex.

#### Return

Boolean - True if the SSID of the [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) matches, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| regex | The regex to check if the SSID of the access point matches |
