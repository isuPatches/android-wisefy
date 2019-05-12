package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a device is roaming.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsDeviceRoamingTests : BaseUnitTest() {

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
