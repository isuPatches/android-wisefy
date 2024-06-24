//[wisefy](../../../../index.md)/[com.isupatches.android.wisefy](../../index.md)/[Wisefy](../index.md)/[Brains](index.md)/[Brains](-brains.md)

# Brains

[androidJvm]\

@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)

constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, logger: WisefyLogger = DefaultWisefyLogger())

#### Parameters

androidJvm

| | |
|---|---|
| context | The application context. Used for creating a [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html) and wifiManager instance to use within Wisefy |
| throwOnAssertions | Whether assertions will throw an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) when hit or be no-op |
| logger | The WisefyLogger instance to use within Wisefy |
