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
| [isNetworkSecure](is-network-secure.md) | [androidJvm]<br>abstract fun [isNetworkSecure](is-network-secure.md)(request: [IsNetworkSecureRequest](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-request/index.md)): [IsNetworkSecureResult](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-result/index.md)<br>A synchronous API to check if a network is secure. |

## Inheritors

| Name |
|---|
| [SecurityDelegate](../-security-delegate/index.md) |
