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

## Inheritors

| Name |
|---|
| [Default](-default/index.md) |
| [Android30OrAbove](-android30-or-above/index.md) |
