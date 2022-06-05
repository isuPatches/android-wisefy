//[security](../../../index.md)/[com.isupatches.android.wisefy.security](../index.md)/[SecurityApi](index.md)

# SecurityApi

[androidJvm]\
interface [SecurityApi](index.md)

A set of synchronous APIs for checking a network's security capabilities.

#### Author

Patches Klinefelter

#### Since

03/2022

## Functions

| Name | Summary |
|---|---|
| [doesNetworkContainSecurityCapability](does-network-contain-security-capability.md) | [androidJvm]<br>abstract fun [doesNetworkContainSecurityCapability](does-network-contain-security-capability.md)(request: [ContainsSecurityCapabilityRequest](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-request/index.md)): [ContainsSecurityCapabilityResult](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-result/index.md)<br>A synchronous API to check if a network has a given security capability. |
| [equals](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSecure](is-network-secure.md) | [androidJvm]<br>abstract fun [isNetworkSecure](is-network-secure.md)(request: [IsNetworkSecureRequest](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-request/index.md)): [IsNetworkSecureResult](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-result/index.md)<br>A synchronous API to check if a network is secure. |
| [toString](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.security.entities/-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [SecurityDelegate](../-security-delegate/index.md) |
