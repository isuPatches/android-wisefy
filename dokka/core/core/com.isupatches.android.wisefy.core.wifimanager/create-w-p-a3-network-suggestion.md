//[core](../../index.md)/[com.isupatches.android.wisefy.core.wifimanager](index.md)/[createWPA3NetworkSuggestion](create-w-p-a3-network-suggestion.md)

# createWPA3NetworkSuggestion

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

fun [createWPA3NetworkSuggestion](create-w-p-a3-network-suggestion.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)

A function that will create the network configuration for a WPA2 network.

#### Return

WifiNetworkSuggestion - The network suggestion for the WPA3 network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| ssid | The SSID of the WPA3 network |
| passphrase | The passphrase of the WPA3 network |
| bssid | The optional BSSID for the WPA3 network |
