package com.isupatches.wisefysample.internal.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import dagger.android.AndroidInjection

internal abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
