//[core](../../index.md)/[com.isupatches.android.wisefy.core.wifimanager.legacy](index.md)/[createWPA2NetworkConfiguration](create-w-p-a2-network-configuration.md)

# createWPA2NetworkConfiguration

[androidJvm]\
fun [createWPA2NetworkConfiguration](create-w-p-a2-network-configuration.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html)

A function that will create the network configuration for a WPA2 network.

#### Return

WifiConfiguration - The network configuration for the WPA2 network

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| ssid | The SSID of the WPA2 network |
| passphrase | The passphrase of the WPA2 network |
| bssid | The optional BSSID for the WPA2 network being added |
