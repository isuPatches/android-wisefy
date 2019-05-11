package com.isupatches.wisefy.internal

import android.net.NetworkCapabilities

internal fun getNetworkCapabilities(transportType: Int): NetworkCapabilities {
    val networkCapabilities = NetworkCapabilities(null)
    ReflectionHelpers.callInstanceMethod<Any>(
        networkCapabilities,
        "addTransportType",
        ReflectionHelpers.ClassParameter.from(Int::class.java, transportType)
    )
    return networkCapabilities
}

internal fun getNetworkCapabilities(capabilities: Array<Int>): NetworkCapabilities {
    val networkCapabilities = NetworkCapabilities(null)
    for (capability in capabilities) {
        ReflectionHelpers.callInstanceMethod<Any>(
            networkCapabilities,
            "addCapability",
            ReflectionHelpers.ClassParameter.from(Int::class.java, capability)
        )
    }
    return networkCapabilities
}

internal fun getNetworkCapabilities(transportType: Int, capabilities: Array<Int>): NetworkCapabilities {
    val networkCapabilities = NetworkCapabilities(null)
    ReflectionHelpers.callInstanceMethod<Any>(
        networkCapabilities,
        "addTransportType",
        ReflectionHelpers.ClassParameter.from(Int::class.java, transportType)
    )
    for (capability in capabilities) {
        ReflectionHelpers.callInstanceMethod<Any>(
            networkCapabilities,
            "addCapability",
            ReflectionHelpers.ClassParameter.from(Int::class.java, capability)
        )
    }
    return networkCapabilities
}
