//[core](../../index.md)/[com.isupatches.android.wisefy.core](index.md)/[hasBSSIDMatchingRegex](has-b-s-s-i-d-matching-regex.md)

# hasBSSIDMatchingRegex

[androidJvm]\
fun [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html).[hasBSSIDMatchingRegex](has-b-s-s-i-d-matching-regex.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to determine if the BSSID for the [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html) matches a given regex.

#### Return

Boolean - True if the BSSID of the [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html) matches, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| regex | The regex to check if the BSSID of the access point matches |

[androidJvm]\
fun [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html).[hasBSSIDMatchingRegex](has-b-s-s-i-d-matching-regex.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to determine if the BSSID for the [android.net.wifi.WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html) matches a given regex.

#### Return

Boolean - True if the BSSID of the [android.net.wifi.WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html) matches, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| regex | The regex to check if the BSSID of the access point matches |

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

fun [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html).[hasBSSIDMatchingRegex](has-b-s-s-i-d-matching-regex.md)(regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A helper function to determine if the BSSID for the [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) matches a given regex.

#### Return

Boolean - True if the BSSID of the [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) matches, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| regex | The regex to check if the BSSID of the access point matches |
