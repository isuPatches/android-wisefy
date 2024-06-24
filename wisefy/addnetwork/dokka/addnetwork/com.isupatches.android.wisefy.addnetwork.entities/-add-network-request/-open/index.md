//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkRequest](../index.md)/[Open](index.md)

# Open

data class [Open](index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [AddNetworkRequest](../index.md)

A representation of a request to add an open network.

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
| [Open](-open.md) | [androidJvm]<br>constructor(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [bssid](bssid.md) | [androidJvm]<br>val [bssid](bssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional BSSID for the open network being added |
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The SSID of the open network to add |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [AddNetworkRequest.Open](index.md) |
| [equals](../../-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator override fun [equals](../../-add-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [hashCode](../../-add-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [toString](../../-add-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
