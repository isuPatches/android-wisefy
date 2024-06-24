//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../index.md)/[AddNetworkRequest](index.md)

# AddNetworkRequest

sealed class [AddNetworkRequest](index.md)

A set of classes and objects that represent requests to add a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Inheritors

| |
|---|
| [Open](-open/index.md) |
| [WPA2](-w-p-a2/index.md) |
| [WPA3](-w-p-a3/index.md) |

## Types

| Name | Summary |
|---|---|
| [Open](-open/index.md) | [androidJvm]<br>data class [Open](-open/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](index.md)<br>A representation of a request to add an open network. |
| [WPA2](-w-p-a2/index.md) | [androidJvm]<br>data class [WPA2](-w-p-a2/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](index.md)<br>A representation of a request to add a WPA2 network. |
| [WPA3](-w-p-a3/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>data class [WPA3](-w-p-a3/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](index.md)<br>A representation of a request to add a WPA3 network. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](../-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](../-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](../-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
