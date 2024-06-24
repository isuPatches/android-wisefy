//[core](../../index.md)/[com.isupatches.android.wisefy.core.wifimanager](index.md)/[createOpenNetworkSuggestion](create-open-network-suggestion.md)

# createOpenNetworkSuggestion

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

fun [createOpenNetworkSuggestion](create-open-network-suggestion.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)

A function that will create the network configuration for a WPA2 network.

#### Return

WifiNetworkSuggestion - The network suggestion for the open network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| ssid | The SSID of the open network |
| bssid | The optional BSSID for the open network |
