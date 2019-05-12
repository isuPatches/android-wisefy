package com.isupatches.wisefysample.internal.di

import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.internal.util.PermissionsUtilImpl

import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module internal interface PermissionsModule {
    @Binds fun bindPermissionUtil(impl: PermissionsUtilImpl): PermissionUtil
}
