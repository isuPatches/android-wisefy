//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddOpenNetworkRequest](../index.md)/[Android30OrAbove](index.md)

# Android30OrAbove

[androidJvm]\
@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

data class [Android30OrAbove](index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val launcher: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;) : [AddOpenNetworkRequest](../index.md)

A data representation of a request to add an open network on Android 30 and above devices.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest](../index.md) |  |

## Constructors

| | |
|---|---|
| [Android30OrAbove](-android30-or-above.md) | [androidJvm]<br>fun [Android30OrAbove](-android30-or-above.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), launcher: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [launcher](launcher.md) | [androidJvm]<br>val [launcher](launcher.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)&gt;<br>The activity result launcher for the request to add an open network |
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The SSID of the open network to add |
