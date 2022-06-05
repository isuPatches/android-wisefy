//[core](../../../../index.md)/[com.isupatches.android.wisefy.core.entities](../../index.md)/[ErrorMessages](../index.md)/[Signal](index.md)

# Signal

[androidJvm]\
object [Signal](index.md)

A singleton that houses the error messages present for the signal features.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [equals](../../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427) | [androidJvm]<br>open operator fun [equals](../../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [hashCode](../../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [toString](../../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [CALCULATE_BARS_ANDROID_30](-c-a-l-c-u-l-a-t-e_-b-a-r-s_-a-n-d-r-o-i-d_30.md) | [androidJvm]<br>const val [CALCULATE_BARS_ANDROID_30](-c-a-l-c-u-l-a-t-e_-b-a-r-s_-a-n-d-r-o-i-d_30.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A string value for the error message for using calculateBars(rssiLevel: Int) before Android 30. |
| [CALCULATE_BARS_LEGACY](-c-a-l-c-u-l-a-t-e_-b-a-r-s_-l-e-g-a-c-y.md) | [androidJvm]<br>const val [CALCULATE_BARS_LEGACY](-c-a-l-c-u-l-a-t-e_-b-a-r-s_-l-e-g-a-c-y.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A string value for the error message for using calculateBars(rssiLevel: Int, targetNumberOfBars: Int) on Android 30+. |
