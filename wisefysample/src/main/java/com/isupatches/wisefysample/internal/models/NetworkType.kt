package com.isupatches.wisefysample.internal.models

internal enum class NetworkType(val intVal: Int) {
    OPEN(0),
    WEP(1),
    WPA2(2);

    companion object {

        fun of(intVal: Int): NetworkType {
            return when (intVal) {
                OPEN.intVal -> OPEN
                WEP.intVal -> WEP
                WPA2.intVal -> WPA2
                else -> throw IllegalArgumentException("Invalid NetworkType, intVal: $intVal")
            }
        }
    }
}
