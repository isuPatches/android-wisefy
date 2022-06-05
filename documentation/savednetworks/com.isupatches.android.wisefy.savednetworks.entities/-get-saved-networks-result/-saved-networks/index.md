//[savednetworks](../../../../index.md)/[com.isupatches.android.wisefy.savednetworks.entities](../../index.md)/[GetSavedNetworksResult](../index.md)/[SavedNetworks](index.md)

# SavedNetworks

[androidJvm]\
data class [SavedNetworks](index.md)(val data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../-saved-network-data/index.md)&gt;) : [GetSavedNetworksResult](../index.md)

A data representation of a success retrieving saved networks on the device based on Android OS level returns.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult](../index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData](../../-saved-network-data/index.md) |  |

## Constructors

| | |
|---|---|
| [SavedNetworks](-saved-networks.md) | [androidJvm]<br>fun [SavedNetworks](-saved-networks.md)(data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../-saved-network-data/index.md)&gt;) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../-saved-network-data/index.md)&gt; |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(data: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../-saved-network-data/index.md)&gt;): [GetSavedNetworksResult.SavedNetworks](index.md) |
| [equals](../../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362) | [androidJvm]<br>open operator override fun [equals](../../-search-for-saved-networks-result/-saved-networks/index.md#585090901%2FFunctions%2F656463362)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [hashCode](../../-search-for-saved-networks-result/-saved-networks/index.md#1794629105%2FFunctions%2F656463362)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362) | [androidJvm]<br>open override fun [toString](../../-search-for-saved-networks-result/-saved-networks/index.md#1616463040%2FFunctions%2F656463362)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SavedNetworkData](../../-saved-network-data/index.md)&gt;<br>The raw value of the saved network retrieved on the device from the Android OS |
