package com.isupatches.wisefy.internal

import com.isupatches.wisefy.logging.WiseFyLogger

class TestWiseFyLogger : WiseFyLogger {

    override fun i(tag: String, message: String, vararg args: Any) {
        // No-op
    }

    override fun v(tag: String, message: String, vararg args: Any) {
        // No-op
    }

    override fun d(tag: String, message: String, vararg args: Any) {
        // No-op
    }

    override fun w(tag: String, message: String, vararg args: Any) {
        // No-op
    }

    override fun e(tag: String, message: String, vararg args: Any) {
        // No-op
    }

    override fun e(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        // No-op
    }

    override fun wtf(tag: String, message: String, vararg args: Any) {
        // No-op
    }

    override fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        // No-op
    }
}
