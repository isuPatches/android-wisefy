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
package com.isupatches.android.wisefy.addnetwork.os.adapters

import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android29AddNetworkAdapterTest(
    private val params: AddNetworkParams
) {

    private lateinit var adapter: Android29AddNetworkAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = Android29AddNetworkAdapter(assertions = WisefyAssertions(false))
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun addNetwork() {
        // Given
        val result = adapter.addNetwork(params.request)

        // Expect
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"
        private const val TEST_PASSPHRASE: String = "immaPassPHRASE"

        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun params(): List<AddNetworkParams> {
            return listOf(
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                )
            )
        }

        data class AddNetworkParams(
            val request: AddNetworkRequest,
            val expectedResult: AddNetworkResult
        )
    }
}
