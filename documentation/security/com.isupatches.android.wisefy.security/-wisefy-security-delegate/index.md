//[security](../../../index.md)/[com.isupatches.android.wisefy.security](../index.md)/[WisefySecurityDelegate](index.md)

# WisefySecurityDelegate

[androidJvm]\
class [WisefySecurityDelegate](index.md)(logger: WisefyLogger) : [SecurityDelegate](../-security-delegate/index.md)

An internal Wisefy delegate for checking a network's security capabilities.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.security.SecurityDelegate](../-security-delegate/index.md) |  |
| com.isupatches.android.wisefy.core.logging.WisefyLogger |  |

## Parameters

androidJvm

| | |
|---|---|
| logger | The logger instance to use |

## Constructors

| | |
|---|---|
| [WisefySecurityDelegate](-wisefy-security-delegate.md) | [androidJvm]<br>fun [WisefySecurityDelegate](-wisefy-security-delegate.md)(logger: WisefyLogger) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [doesNetworkContainSecurityCapability](does-network-contain-security-capability.md) | [androidJvm]<br>open override fun [doesNetworkContainSecurityCapability](does-network-contain-security-capability.md)(request: [ContainsSecurityCapabilityRequest](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-request/index.md)): [ContainsSecurityCapabilityResult](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-result/index.md)<br>A synchronous API to check if a network has a given security capability. |
| [equals](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSecure](is-network-secure.md) | [androidJvm]<br>open override fun [isNetworkSecure](is-network-secure.md)(request: [IsNetworkSecureRequest](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-request/index.md)): [IsNetworkSecureResult](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-result/index.md)<br>A synchronous API to check if a network is secure. |
| [toString](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
