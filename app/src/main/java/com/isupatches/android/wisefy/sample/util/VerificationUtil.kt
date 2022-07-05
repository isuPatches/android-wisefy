/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.sample.util

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

private const val MIN_SSID_LENGTH = 2
private const val MAX_SSID_LENGTH = 32

private const val MIN_PASSPHRASE_LENGTH = 8
private const val MAX_PASSPHRASE_LENGTH = 63

internal fun String.validateSSID(): SSIDInputError {
    val unicodeEncoder = StandardCharsets.UTF_8.newEncoder()
    return when {
        isBlank() -> SSIDInputError.EMPTY
        length < MIN_SSID_LENGTH -> SSIDInputError.TOO_SHORT
        length > MAX_SSID_LENGTH -> SSIDInputError.TOO_LONG
        contains("?") ||
            contains("\"") ||
            contains("$") ||
            contains("[") ||
            contains("\\") ||
            contains("]") ||
            contains("+") -> {
            SSIDInputError.INVALID_CHARACTERS
        }
        startsWith("!") ||
            startsWith("#") ||
            startsWith(";") -> {
            SSIDInputError.INVALID_START_CHARACTERS
        }
        trimStart() != this ||
            trimEnd() != this -> {
            SSIDInputError.LEADING_OR_TRAILING_SPACES
        }
        !unicodeEncoder.canEncode(this) -> SSIDInputError.NOT_VALID_UNICODE
        else -> SSIDInputError.NONE
    }
}

internal fun String.validatePassphrase(): PassphraseInputError {
    return when {
        length < MIN_PASSPHRASE_LENGTH -> PassphraseInputError.TOO_SHORT
        length > MAX_PASSPHRASE_LENGTH -> PassphraseInputError.TOO_LONG
        !Charset.forName("US-ASCII").newEncoder().canEncode(this) -> PassphraseInputError.NOT_VALID_ASCII
        else -> PassphraseInputError.NONE
    }
}

internal fun String.validateBSSID(): BSSIDInputError {
    return when {
        isBlank() -> BSSIDInputError.EMPTY
        !matches(Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")) -> BSSIDInputError.INVALID
        else -> BSSIDInputError.NONE
    }
}

internal enum class SSIDInputError {
    NONE,
    EMPTY,
    TOO_SHORT,
    TOO_LONG,
    INVALID_CHARACTERS,
    INVALID_START_CHARACTERS,
    LEADING_OR_TRAILING_SPACES,
    NOT_VALID_UNICODE
}

internal enum class PassphraseInputError {
    NONE,
    TOO_SHORT,
    TOO_LONG,
    NOT_VALID_ASCII
}

internal enum class BSSIDInputError {
    NONE,
    EMPTY,
    INVALID
}
