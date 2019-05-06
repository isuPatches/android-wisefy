package com.isupatches.wisefysample.internal.models

internal enum class SearchType(val intVal: Int) {
    ACCESS_POINT(0),
    SSID(1),
    SAVED_NETWORK(2);

    companion object {

        fun of(intVal: Int): SearchType {
            return when (intVal) {
                ACCESS_POINT.intVal -> ACCESS_POINT
                SSID.intVal -> SSID
                SAVED_NETWORK.intVal -> SAVED_NETWORK
                else -> throw IllegalArgumentException("Invalid SearchType intVal: $intVal")
            }
        }
    }
}
