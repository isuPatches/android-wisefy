package com.metova.wisefy.base;


import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;


public abstract class BaseInstrumentationTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    private Solo mSolo;

    public BaseInstrumentationTestCase(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().toString());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        getSolo().finishOpenedActivities();
    }


    protected Solo getSolo() {
        if (mSolo == null) {
            mSolo = new Solo(getInstrumentation(), getActivity());
        }
        return mSolo;
    }
}