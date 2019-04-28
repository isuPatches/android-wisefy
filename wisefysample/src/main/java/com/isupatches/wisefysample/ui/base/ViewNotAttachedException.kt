package com.isupatches.wisefysample.ui.base

import java.lang.RuntimeException

class ViewNotAttachedException : RuntimeException("New view attached.  Did you forget to call attachView()?")
