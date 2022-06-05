//[frequency](../../index.md)/[com.isupatches.android.wisefy.frequency](index.md)

# Package com.isupatches.android.wisefy.frequency

## Types

| Name | Summary |
|---|---|
| [FrequencyApi](-frequency-api/index.md) | [androidJvm]<br>interface [FrequencyApi](-frequency-api/index.md)<br>A set of synchronous APIs for getting the frequency of a network. |
| [FrequencyApiAsync](-frequency-api-async/index.md) | [androidJvm]<br>interface [FrequencyApiAsync](-frequency-api-async/index.md)<br>A set of asynchronous APIs for getting the frequency of a network. |
| [FrequencyDelegate](-frequency-delegate/index.md) | [androidJvm]<br>interface [FrequencyDelegate](-frequency-delegate/index.md) : [FrequencyApi](-frequency-api/index.md), [FrequencyApiAsync](-frequency-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs to get the frequency of a network. |
| [WisefyFrequencyDelegate](-wisefy-frequency-delegate/index.md) | [androidJvm]<br>class [WisefyFrequencyDelegate](-wisefy-frequency-delegate/index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [FrequencyDelegate](-frequency-delegate/index.md)<br>An internal Wisefy delegate for getting the frequency of a network. |

## Properties

| Name | Summary |
|---|---|
| [MAX_FREQUENCY_5GHZ](-m-a-x_-f-r-e-q-u-e-n-c-y_5-g-h-z.md) | [androidJvm]<br>const val [MAX_FREQUENCY_5GHZ](-m-a-x_-f-r-e-q-u-e-n-c-y_5-g-h-z.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 5900<br>A constant for the maximum frequency for a 5G network. |
| [MIN_FREQUENCY_5GHZ](-m-i-n_-f-r-e-q-u-e-n-c-y_5-g-h-z.md) | [androidJvm]<br>const val [MIN_FREQUENCY_5GHZ](-m-i-n_-f-r-e-q-u-e-n-c-y_5-g-h-z.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 4900<br>A constant for the minimum frequency for a 5G network. |
