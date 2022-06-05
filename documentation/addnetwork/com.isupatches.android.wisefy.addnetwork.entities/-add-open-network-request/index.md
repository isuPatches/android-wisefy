//[addnetwork](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../index.md)/[AddOpenNetworkRequest](index.md)

# AddOpenNetworkRequest

[androidJvm]\
sealed class [AddOpenNetworkRequest](index.md)

A set of classes and objects that are used to represent requests to add an open network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Android30OrAbove](-android30-or-above/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>data class [Android30OrAbove](-android30-or-above/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val launcher: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;) : [AddOpenNetworkRequest](index.md)<br>A data representation of a request to add an open network on Android 30 and above devices. |
| [Default](-default/index.md) | [androidJvm]<br>data class [Default](-default/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddOpenNetworkRequest](index.md)<br>A data representation of a request to add an open network. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-add-w-p-a3-network-request/-android30-or-above/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](../-add-w-p-a3-network-request/-android30-or-above/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-add-w-p-a3-network-request/-android30-or-above/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](../-add-w-p-a3-network-request/-android30-or-above/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-add-w-p-a3-network-request/-android30-or-above/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](../-add-w-p-a3-network-request/-android30-or-above/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Default](-default/index.md) |
| [Android30OrAbove](-android30-or-above/index.md) |
