//[wisefy](../../index.md)/[com.isupatches.android.wisefy.util](index.md)

# Package com.isupatches.android.wisefy.util

## Types

| Name | Summary |
|---|---|
| [SdkUtil](-sdk-util/index.md) | [androidJvm]<br>internal interface [SdkUtil](-sdk-util/index.md) |
| [SdkUtilImpl](-sdk-util-impl/index.md) | [androidJvm]<br>internal class [SdkUtilImpl](-sdk-util-impl/index.md) : [SdkUtil](-sdk-util/index.md) |

## Functions

| Name | Summary |
|---|---|
| [createOpenNetworkSuggestion](create-open-network-suggestion.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>internal fun [createOpenNetworkSuggestion](create-open-network-suggestion.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) |
| [createWPA2NetworkSuggestion](create-w-p-a2-network-suggestion.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>internal fun [createWPA2NetworkSuggestion](create-w-p-a2-network-suggestion.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) |
| [createWPA3NetworkSuggestion](create-w-p-a3-network-suggestion.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>internal fun [createWPA3NetworkSuggestion](create-w-p-a3-network-suggestion.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [WifiNetworkSuggestion](https://developer.android.com/reference/kotlin/android/net/wifi/WifiNetworkSuggestion.html) |
| [fail](fail.md) | [androidJvm]<br>fun [fail](fail.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [rest](rest.md) | [androidJvm]<br>internal fun [rest](rest.md)() |
| [sleep](sleep.md) | [androidJvm]<br>private fun [sleep](sleep.md)(timeToSleepInMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = BASE_DELAY) |

## Properties

| Name | Summary |
|---|---|
| [BASE_DELAY](-b-a-s-e_-d-e-l-a-y.md) | [androidJvm]<br>private const val [BASE_DELAY](-b-a-s-e_-d-e-l-a-y.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 1000L |
