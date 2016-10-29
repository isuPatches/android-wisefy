package com.isupatches.wisefy.base;


import android.app.Activity;
import android.support.test.rule.ActivityTestRule;


public abstract class BaseTestClass<T extends Activity> extends ActivityTestRule<T> {

    public BaseTestClass(Class<T> activityClass) {
        super(activityClass);
    }
}