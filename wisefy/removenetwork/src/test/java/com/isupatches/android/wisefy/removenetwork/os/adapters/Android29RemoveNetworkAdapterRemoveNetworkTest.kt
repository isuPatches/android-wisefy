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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android29RemoveNetworkAdapterRemoveNetworkTest(
    private val params: RemoveNetworkParams
) {

    private lateinit var adapter: Android29RemoveNetworkAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = Android29RemoveNetworkAdapter(assertions = WisefyAssertions(throwOnAssertions = false))
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // When
        val result = adapter.removeNetwork(params.request)

        // Expect
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<RemoveNetworkParams> {
            return listOf(
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    expectedResult = RemoveNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    expectedResult = RemoveNetworkResult.Failure.Assertion(
                        AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
                    )
                )
            )
        }

        data class RemoveNetworkParams(
            val request: RemoveNetworkRequest,
            val expectedResult: RemoveNetworkResult
        )
    }
}
