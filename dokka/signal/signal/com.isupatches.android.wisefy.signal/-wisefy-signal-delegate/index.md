//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[WisefySignalDelegate](index.md)

# WisefySignalDelegate

[androidJvm]\
class [WisefySignalDelegate](index.md)(assertions: [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), adapter: [SignalApi](../-signal-api/index.md) = if (sdkUtil.isAtLeastR()) {
        Android30SignalAdapter(wifiManager, logger, assertions)
    } else {
        DefaultSignalAdapter(logger, assertions)
    }) : [SignalDelegate](../-signal-delegate/index.md)

An internal Wisefy delegate for signal strength functionality.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.signal.os.adapters.Android30SignalAdapter |  |
| com.isupatches.android.wisefy.signal.os.adapters.DefaultSignalAdapter |  |
| [com.isupatches.android.wisefy.signal.SignalApi](../-signal-api/index.md) |  |
| [com.isupatches.android.wisefy.signal.SignalDelegate](../-signal-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.util.SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) |  |
| [com.isupatches.android.wisefy.core.assertions.WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| assertions | The [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md) instance to use |
| logger | The [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) instance to use |
| sdkUtil | The [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefySignalDelegate](-wisefy-signal-delegate.md) | [androidJvm]<br>fun [WisefySignalDelegate](-wisefy-signal-delegate.md)(assertions: [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), adapter: [SignalApi](../-signal-api/index.md) = if (sdkUtil.isAtLeastR()) {         Android30SignalAdapter(wifiManager, logger, assertions)     } else {         DefaultSignalAdapter(logger, assertions)     }) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateSignalLevel](calculate-signal-level.md) | [androidJvm]<br>open override fun [calculateSignalLevel](calculate-signal-level.md)(request: [CalculateSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-request/index.md)): [CalculateSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-calculate-signal-level-result/index.md)<br>A synchronous API to calculate the number of signal strength bars for a network. |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>open override fun [compareSignalLevel](compare-signal-level.md)(request: [CompareSignalLevelRequest](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-request/index.md)): [CompareSignalLevelResult](../../com.isupatches.android.wisefy.signal.entities/-compare-signal-level-result/index.md)<br>A synchronous API to compare the signal strength of two networks. |
