package com.isupatches.wisefy.test;


import android.support.test.rule.ActivityTestRule;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.base.TestActivity;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BuilderTests extends ActivityTestRule<TestActivity> {

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    public BuilderTests() {
        super(TestActivity.class);
    }

    @Test
    public void builder_loggingFalse() {
        WiseFy wiseFy = new WiseFy.brains(mActivityTestRule.getActivity()).logging(false).getSmarts();
        assertEquals(false, wiseFy.isLoggingEnabled());
    }

    @Test
    public void builder_loggingTrue() {
        WiseFy wiseFy = new WiseFy.brains(mActivityTestRule.getActivity()).logging(true).getSmarts();
        assertEquals(true, wiseFy.isLoggingEnabled());
    }
}
