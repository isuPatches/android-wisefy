package com.isupatches.wisefysample.internal.models

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

internal class SearchTypeTest {

    @Test
    fun accessPoint() {
        assertEquals(SearchType.ACCESS_POINT, SearchType.of(SearchType.ACCESS_POINT.intVal))
    }

    @Test
    fun ssid() {
        assertEquals(SearchType.SSID, SearchType.of(SearchType.SSID.intVal))
    }

    @Test
    fun savedNetwork() {
        assertEquals(SearchType.SAVED_NETWORK, SearchType.of(SearchType.SAVED_NETWORK.intVal))
    }

    @Test
    fun unexpected() {
        try {
            SearchType.of(999)
            fail("Expected IllegalArgumentException from NetworkType.of")
        } catch (ex: IllegalArgumentException) {
            // Do nothing
        }
    }
}
