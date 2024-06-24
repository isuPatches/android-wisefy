//[wisefy](../../../../index.md)/[com.isupatches.android.wisefy](../../index.md)/[Wisefy](../index.md)/[Brains](index.md)

# Brains

class [Brains](index.md)@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, logger: WisefyLogger = DefaultWisefyLogger())

The public builder to create a Wisefy instance.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| context | The application context. Used for creating a [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html) and wifiManager instance to use within Wisefy |
| throwOnAssertions | Whether assertions will throw an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) when hit or be no-op |
| logger | The WisefyLogger instance to use within Wisefy |

#### See also

| |
|---|
| DefaultWisefyLogger |
| WisefyLogger |

## Constructors

| | |
|---|---|
| [Brains](-brains.md) | [androidJvm]<br>@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, logger: WisefyLogger = DefaultWisefyLogger()) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../-wisefy-api/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../-wisefy-api/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSmarts](get-smarts.md) | [androidJvm]<br>fun [getSmarts](get-smarts.md)(): [WisefyApi](../../-wisefy-api/index.md)<br>A function on the [Brains](index.md) builder class that returns a Wisefy instance ([WisefyApi](../../-wisefy-api/index.md)) and the equivalent to a builder.build() call. |
| [hashCode](../../-wisefy-api/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../-wisefy-api/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-wisefy-api/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../-wisefy-api/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
