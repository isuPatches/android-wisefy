/*
 * Copyright 2023 Patches Barrett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.networkinfo.entities

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito.mock

internal class GetCurrentNetworkResultTest {

    @Test
    fun assignsValueWithNulls() {
        // Given
        val result = GetCurrentNetworkResult(
            value = NetworkData(
                network = null,
                connectionInfo = null,
                capabilities = null,
                linkProperties = null
            )
        )

        // Expect
        assertNotNull(result)
        assertNotNull(result.value)
        assertNull(result.value.network)
        assertNull(result.value.connectionInfo)
        assertNull(result.value.capabilities)
        assertNull(result.value.linkProperties)
    }

    @Test
    fun assignsValueWithNonNulls() {
        // Given
        val result = GetCurrentNetworkResult(
            value = NetworkData(
                network = mock(Network::class.java),
                connectionInfo = mock(WifiInfo::class.java),
                capabilities = mock(NetworkCapabilities::class.java),
                linkProperties = mock(LinkProperties::class.java)
            )
        )

        // Expect
        assertNotNull(result)
        assertNotNull(result.value)
        assertNotNull(result.value.network)
        assertNotNull(result.value.connectionInfo)
        assertNotNull(result.value.capabilities)
        assertNotNull(result.value.linkProperties)
    }
}
