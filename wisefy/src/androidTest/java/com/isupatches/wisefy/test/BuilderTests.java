package com.isupatches.wisefy.test;


import android.support.test.InstrumentationRegistry;
import com.isupatches.wisefy.WiseFy;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BuilderTests {

    @Test
    public void builder_loggingFalse() {
        WiseFy wiseFy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(false).getSmarts();
        assertEquals(false, wiseFy.isLoggingEnabled());
    }

    @Test
    public void builder_loggingTrue() {
        WiseFy wiseFy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(true).getSmarts();
        assertEquals(true, wiseFy.isLoggingEnabled());
    }
}
