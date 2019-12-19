package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is roaming.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsDeviceRoamingTests : BaseInstrumentationTest() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isDeviceRoaming_failure()
        assertFalse(wisefy.isDeviceRoaming())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceRoaming_success()
        mockWiseFyConnectionUtil.isDeviceRoaming(false)
        assertFalse(wisefy.isDeviceRoaming())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceRoaming_success()
        mockWiseFyConnectionUtil.isDeviceRoaming(true)
        assertTrue(wisefy.isDeviceRoaming())
    }
}
