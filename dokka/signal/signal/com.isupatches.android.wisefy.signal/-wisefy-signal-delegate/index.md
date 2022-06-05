//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[WisefySignalDelegate](index.md)

# WisefySignalDelegate

[androidJvm]\
class [WisefySignalDelegate](index.md)(logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [SignalDelegate](../-signal-delegate/index.md)

An internal Wisefy delegate for signal strength functionality.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.SignalDelegate](../-signal-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.util.SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| logger | The logger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefySignalDelegate](-wisefy-signal-delegate.md) | [androidJvm]<br>fun [WisefySignalDelegate](-wisefy-signal-delegate.md)(logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateBars](calculate-bars.md) | [androidJvm]<br>open override fun [calculateBars](calculate-bars.md)(request: [CalculateBarsRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-bars-request/index.md)): [CalculateBarsResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-bars-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>open override fun [compareSignalLevel](compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |
