package com.isupatches.wisefysample.internal.base

import java.lang.RuntimeException

internal class ViewNotAttachedException : RuntimeException("New view attached.  Did you forget to call attachView()?")
