//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkRequest](../index.md)/[WPA3](index.md)

# WPA3

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

data class [WPA3](index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](../index.md)

A representation of a request to add a WPA3 network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AddNetworkRequest](../index.md) |

## Constructors

| | |
|---|---|
| [WPA3](-w-p-a3.md) | [androidJvm]<br>constructor(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [bssid](bssid.md) | [androidJvm]<br>val [bssid](bssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional BSSID for the WPA3 network being added |
| [passphrase](passphrase.md) | [androidJvm]<br>val [passphrase](passphrase.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The password or passphrase for the WPA3 network to add |
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The SSID of the WPA3 network to add |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [AddNetworkRequest.WPA3](index.md) |
| [equals](../../-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator override fun [equals](../../-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [hashCode](../../-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [toString](../../-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
