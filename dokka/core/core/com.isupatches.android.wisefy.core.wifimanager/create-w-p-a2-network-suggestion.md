//[core](../../index.md)/[com.isupatches.android.wisefy.core.wifimanager](index.md)/[createWPA2NetworkSuggestion](create-w-p-a2-network-suggestion.md)

# createWPA2NetworkSuggestion

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

fun [createWPA2NetworkSuggestion](create-w-p-a2-network-suggestion.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html)

A function that will create the network configuration for a WPA2 network.

#### Return

WifiNetworkSuggestion - The network suggestion for the WPA2 network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| ssid | The SSID of the WPA2 network |
| passphrase | The passphrase of the WPA2 network |
| bssid | The optional BSSID for the WPA2 network |
