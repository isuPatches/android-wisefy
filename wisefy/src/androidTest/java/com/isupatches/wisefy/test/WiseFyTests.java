package com.isupatches.wisefy.test;

import static com.isupatches.wisefy.TestUtils.TEST_NUMBER_OF_BARS;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_HIGH;
import static com.isupatches.wisefy.TestUtils.TEST_RSSI_LEVEL_LOW;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.google.android.gms.iid.InstanceID;
import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;

import org.junit.Test;

/**
 * Tests that pertain to the builder for WiseFy or general use.
 *
 * @see WiseFy
 * @see WiseFy#calculateBars(int, int)
 * @see WiseFy#compareSignalLevel(int, int)
 * @see WiseFy#getWiseFyLock()
 * @see com.isupatches.wisefy.WiseFy.brains
 * @see InstanceID#getInstance(Context)
 *
 * @author Patches
 */
public class WiseFyTests extends AbstractBaseAndroidJUnit4TestClass {

  private static final int EXPECTED_NUMBER_OF_BARS = 4;
  private static final int EXPECTED_SIGNAL_RESULT = 35;

  public WiseFyTests() {
    // No-op
  }

  @Test
  public void brains_loggingFalse() {
    final WiseFy wisefy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(false).getSmarts();
    assertEquals(false, wisefy.isLoggingEnabled());
  }

  @Test
  public void brains_loggingTrue() {
    final WiseFy wisefy = new WiseFy.brains(InstrumentationRegistry.getContext()).logging(true).getSmarts();
    assertEquals(true, wisefy.isLoggingEnabled());
  }

  @Test
  public void calculateBars() {
    final int result = getWiseFy().calculateBars(TEST_RSSI_LEVEL_HIGH, TEST_NUMBER_OF_BARS);
    assertEquals(EXPECTED_NUMBER_OF_BARS, result);
  }

  @Test
  public void compareSignalLevel() {
    final int result = getWiseFy().compareSignalLevel(TEST_RSSI_LEVEL_HIGH, TEST_RSSI_LEVEL_LOW);
    assertEquals(EXPECTED_SIGNAL_RESULT, result);
  }

  @Test
  public void getWiseFyLock() {
    assertNotNull(getWiseFy().getWiseFyLock());
  }

  @Test
  public void olderGcm_IllegalAccessError_notThrown() {
    final InstanceID instanceID = InstanceID.getInstance(InstrumentationRegistry.getContext());
    assertNotNull(instanceID);
  }
}
