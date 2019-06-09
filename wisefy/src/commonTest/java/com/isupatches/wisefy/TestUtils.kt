package com.isupatches.wisefy

import com.isupatches.wisefy.WiseFy.Companion.MAX_FREQUENCY_5GHZ
import com.isupatches.wisefy.WiseFy.Companion.MIN_FREQUENCY_5GHZ

internal const val OPEN_NETWORK_SSID = "Open Network"

internal const val WEP_NETWORK_SSID = "WEP Network"
internal const val WEP_NETWORK_PASSWORD = "12345678"

internal const val WPA2_NETWORK_SSID = "WPA2 Network"
internal const val WPA2_NETWORK_PASSWORD = "123456"

internal const val TEST_DELAY = 1

internal const val TEST_NUMBER_OF_BARS = 5

internal const val TEST_SSID = "Test Network"

internal const val TEST_NETWORK_FREQUENCY_BELOW_5GHZ = MIN_FREQUENCY_5GHZ - 1
internal const val TEST_NETWORK_FREQUENCY_ABOVE_5GHZ = MAX_FREQUENCY_5GHZ + 1

internal const val TEST_NETWORK_FREQUENCY_24GHZ = 2400
internal const val TEST_NETWORK_FREQUENCY_5GHZ = 5000

internal const val TEST_RSSI_LEVEL = -60
internal const val TEST_RSSI_LEVEL_LOW = -70
internal const val TEST_RSSI_LEVEL_HIGH = -35

internal const val TEST_TIMEOUT = 1

internal const val VERIFICATION_SUCCESS_TIMEOUT = 5000L
internal const val VERIFICATION_FAILURE_TIMEOUT = 3000L

internal const val TEST_IP_ADDRESS_STRING = "127.0.0.1"

internal const val TEST_REGEX = ".*Test.*"

internal const val TEST_SSID2 = "Test Network 2"
internal const val TEST_SSID3 = "Test Network 3"

internal val TEST_IP_ADDRESS_INT = getIntVersionOfTestIPAddress()

private const val BIT_SHIFT_VALUE = 8

/**
 * To convert the TEST_IP_ADDRESS_STRING string into an integer value to be used for Inet and
 * tests.
 *
 * @see #TEST_IP_ADDRESS_STRING
 *
 * @return int - The int value of a given IP
 */
private fun getIntVersionOfTestIPAddress(): Int {
    var result = 0

    // iterate over each octet
    for (part in TEST_IP_ADDRESS_STRING.split(".")) {
        // Shift the previously parsed bits over by 1 byte
        result = result shl BIT_SHIFT_VALUE
        // Set the low order bits to the current octet
        result = result or part.toInt()
    }
    return result
}
