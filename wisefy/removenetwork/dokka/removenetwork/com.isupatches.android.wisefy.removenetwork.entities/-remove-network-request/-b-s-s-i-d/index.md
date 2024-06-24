//[removenetwork](../../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../../index.md)/[RemoveNetworkRequest](../index.md)/[BSSID](index.md)

# BSSID

data class [BSSID](index.md)(val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [RemoveNetworkRequest](../index.md)

A data representation to remove a network by BSSID.

*NOTE*

- 
   The request will only remove the first network whose SSID matches

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [RemoveNetworkRequest](../index.md) |

## Constructors

| | |
|---|---|
| [BSSID](-b-s-s-i-d.md) | [androidJvm]<br>constructor(bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [bssid](bssid.md) | [androidJvm]<br>val [bssid](bssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The BSSID value of the network to remove |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [RemoveNetworkRequest.BSSID](index.md) |
| [equals](../../-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator override fun [equals](../../-remove-network-result/-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open override fun [hashCode](../../-remove-network-result/-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open override fun [toString](../../-remove-network-result/-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
