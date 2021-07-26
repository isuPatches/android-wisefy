package com.isupatches.android.wisefy.internal.models

import com.isupatches.android.wisefy.sample.internal.entities.NetworkType
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

internal class NetworkTypeTest {

    @Test
    fun open() {
        assertEquals(NetworkType.OPEN, NetworkType.of(NetworkType.OPEN.intVal))
    }

    @Test
    fun wpa2() {
        assertEquals(NetworkType.WPA2, NetworkType.of(NetworkType.WPA2.intVal))
    }

    @Test
    fun wep() {
        assertEquals(NetworkType.WEP, NetworkType.of(NetworkType.WEP.intVal))
    }

    @Test
    fun unexpected() {
        try {
            NetworkType.of(999)
            fail("Expected IllegalArgumentException from NetworkType.of")
        } catch (ex: IllegalArgumentException) {
            // Do nothing
        }
    }
}
