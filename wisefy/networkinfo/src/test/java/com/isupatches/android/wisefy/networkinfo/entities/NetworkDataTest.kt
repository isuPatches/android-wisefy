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
import org.mockito.Mockito

internal class NetworkDataTest {

    @Test
    fun assignsValueWithNulls() {
        // Given
        val value = NetworkData(
            network = null,
            connectionInfo = null,
            capabilities = null,
            linkProperties = null
        )

        // Expect
        assertNotNull(value)
        assertNull(value.network)
        assertNull(value.connectionInfo)
        assertNull(value.capabilities)
        assertNull(value.linkProperties)
    }

    @Test
    fun assignsValueWithNonNulls() {
        // Given
        val value = NetworkData(
            network = Mockito.mock(Network::class.java),
            connectionInfo = Mockito.mock(WifiInfo::class.java),
            capabilities = Mockito.mock(NetworkCapabilities::class.java),
            linkProperties = Mockito.mock(LinkProperties::class.java)
        )

        // Expect
        assertNotNull(value)
        assertNotNull(value.network)
        assertNotNull(value.connectionInfo)
        assertNotNull(value.capabilities)
        assertNotNull(value.linkProperties)
    }
}
