package com.isupatches.android.wisefy.core.entities

/**
 * A set of states representing the status of a network connection.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
enum class NetworkConnectionStatus {

    /**
     * A representation of when network connectivity is available.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    AVAILABLE,

    /**
     * A representation of when network connectivity starting going from available to unavailable.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    LOSING,

    /**
     * A representation of when network connectivity has gone from available to unavailable.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    LOST,

    /**
     * A representation of when network connectivity is unavailable.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    UNAVAILABLE
}
