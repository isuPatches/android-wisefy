//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.security](../index.md)/[WisefySecurityUtil](index.md)

# WisefySecurityUtil

[androidJvm]\
internal class [WisefySecurityUtil](index.md)(**logger**: [WisefyLogger](../../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?) : [SecurityUtil](../-security-util/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkEAP](is-network-e-a-p.md) | [androidJvm]<br>open override fun [isNetworkEAP](is-network-e-a-p.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkPSK](is-network-p-s-k.md) | [androidJvm]<br>open override fun [isNetworkPSK](is-network-p-s-k.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkSecure](is-network-secure.md) | [androidJvm]<br>open override fun [isNetworkSecure](is-network-secure.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWEP](is-network-w-e-p.md) | [androidJvm]<br>open override fun [isNetworkWEP](is-network-w-e-p.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWPA](is-network-w-p-a.md) | [androidJvm]<br>open override fun [isNetworkWPA](is-network-w-p-a.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWPA2](is-network-w-p-a2.md) | [androidJvm]<br>open override fun [isNetworkWPA2](is-network-w-p-a2.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWPA3](is-network-w-p-a3.md) | [androidJvm]<br>open override fun [isNetworkWPA3](is-network-w-p-a3.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [delegate](delegate.md) | [androidJvm]<br>private val [delegate](delegate.md): [LegacySecurityDelegate](../../com.isupatches.android.wisefy.security.delegates/-legacy-security-delegate/index.md) |
