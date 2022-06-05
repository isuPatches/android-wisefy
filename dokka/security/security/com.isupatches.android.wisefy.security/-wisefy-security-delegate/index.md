//[security](../../../index.md)/[com.isupatches.android.wisefy.security](../index.md)/[WisefySecurityDelegate](index.md)

# WisefySecurityDelegate

[androidJvm]\
class [WisefySecurityDelegate](index.md)(logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md)) : [SecurityDelegate](../-security-delegate/index.md)

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
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| logger | The logger instance to use |

## Constructors

| | |
|---|---|
| [WisefySecurityDelegate](-wisefy-security-delegate.md) | [androidJvm]<br>fun [WisefySecurityDelegate](-wisefy-security-delegate.md)(logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [doesNetworkContainSecurityCapability](does-network-contain-security-capability.md) | [androidJvm]<br>open override fun [doesNetworkContainSecurityCapability](does-network-contain-security-capability.md)(request: [ContainsSecurityCapabilityRequest](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-request/index.md)): [ContainsSecurityCapabilityResult](../../com.isupatches.android.wisefy.security.entities/-contains-security-capability-result/index.md)<br>A synchronous API to check if a network has a given security capability. |
| [isNetworkSecure](is-network-secure.md) | [androidJvm]<br>open override fun [isNetworkSecure](is-network-secure.md)(request: [IsNetworkSecureRequest](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-request/index.md)): [IsNetworkSecureResult](../../com.isupatches.android.wisefy.security.entities/-is-network-secure-result/index.md)<br>A synchronous API to check if a network is secure. |
