package com.metova.wisefy.base;


import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;


public abstract class BaseTestClass<T extends Activity> extends ActivityTestRule<T> {

    private Context targetContext = InstrumentationRegistry.getTargetContext();


    public BaseTestClass(Class<T> activityClass) {
        super(activityClass);
    }


    public Context getTargetContext() {
        return targetContext;
    }
}