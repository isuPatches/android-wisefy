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
package com.isupatches.android.wisefy.sample.ui.search

import dagger.Binds
import dagger.Module
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class SearchScope

@Suppress("unused")
@Module
internal interface SearchFragmentModule {
    @Binds
    @SearchScope
    fun bindSearchModel(impl: DefaultSearchModel): SearchModel

    @Binds
    @SearchScope
    fun bindSearchPresenter(impl: DefaultSearchPresenter): SearchPresenter

    @Binds
    @SearchScope
    fun bindSearchStore(impl: SharedPreferencesSearchStore): SearchStore
}
