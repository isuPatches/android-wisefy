//[security](../../../index.md)/[com.isupatches.android.wisefy.security](../index.md)/[SecurityDelegate](index.md)

# SecurityDelegate

[androidJvm]\
interface [SecurityDelegate](index.md) : [SecurityApi](../-security-api/index.md)

A delegate for synchronous APIs to check a network's security capabilities.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.security.SecurityApi](../-security-api/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [doesNetworkContainSecurityCapability](../-security-api/does-network-contain-security-capability.md) | [androidJvm]<br>abstract fun [doesNetworkContainSecurityCapability](../-security-api/does-network-contain-security-capability.md)(request: [ContainsSecurityCapabilityRequest](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-request/index.md)): [ContainsSecurityCapabilityResult](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-result/index.md)<br>A synchronous API to check if a network has a given security capability. |
| [isNetworkSecure](../-security-api/is-network-secure.md) | [androidJvm]<br>abstract fun [isNetworkSecure](../-security-api/is-network-secure.md)(request: [IsNetworkSecureRequest](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-request/index.md)): [IsNetworkSecureResult](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-result/index.md)<br>A synchronous API to check if a network is secure. |

## Inheritors

| Name |
|---|
| [WisefySecurityDelegate](../-wisefy-security-delegate/index.md) |
