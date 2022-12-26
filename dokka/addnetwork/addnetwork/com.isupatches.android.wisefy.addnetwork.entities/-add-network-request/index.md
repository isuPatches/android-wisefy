//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../index.md)/[AddNetworkRequest](index.md)

# AddNetworkRequest

[androidJvm]\
sealed class [AddNetworkRequest](index.md)

A set of classes and objects that represent requests to add a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [Open](-open/index.md) | [androidJvm]<br>data class [Open](-open/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](index.md)<br>A representation of a request to add an open network. |
| [WPA2](-w-p-a2/index.md) | [androidJvm]<br>data class [WPA2](-w-p-a2/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](index.md)<br>A representation of a request to add a WPA2 network. |
| [WPA3](-w-p-a3/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>data class [WPA3](-w-p-a3/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](index.md)<br>A representation of a request to add a WPA3 network. |

## Inheritors

| Name |
|---|
| [Open](-open/index.md) |
| [WPA2](-w-p-a2/index.md) |
| [WPA3](-w-p-a3/index.md) |
