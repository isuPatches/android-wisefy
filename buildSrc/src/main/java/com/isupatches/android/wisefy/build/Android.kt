/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.build

import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.SigningConfig
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<SigningConfig>.debug(
    action: Action<SigningConfig>
): SigningConfig = getByName("debug", action)

fun NamedDomainObjectContainer<SigningConfig>.release(
    action: Action<SigningConfig>
): SigningConfig = create("release", action)

fun NamedDomainObjectContainer<BuildType>.debug(
    action: Action<BuildType>
): BuildType = getByName("debug", action)

fun NamedDomainObjectContainer<BuildType>.release(
    action: Action<BuildType>
): BuildType = getByName("release", action)
