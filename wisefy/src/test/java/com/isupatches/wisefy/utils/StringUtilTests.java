package com.isupatches.wisefy.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Used to test the functionality and behavior of the util class that manipulates and checks Strings.
 *
 * @see StringUtil
 */
public class StringUtilTests {

  public StringUtilTests() {
    // No-op
  }

  @Test
  public void isEmpty_true_null() {
    assertTrue(StringUtil.isEmpty(null));
  }

  @Test
  public void isEmpty_true_zeroCharacters() {
    assertTrue(StringUtil.isEmpty(""));
  }

  @Test
  public void isEmpty_true_space() {
    assertTrue(StringUtil.isEmpty(" "));
  }

  @Test
  public void isEmpty_false() {
    assertFalse(StringUtil.isEmpty("test"));
  }
}
