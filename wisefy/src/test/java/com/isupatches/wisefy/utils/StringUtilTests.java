package com.isupatches.wisefy.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Used to test the functionality and behavior of the util class that manipulates and checks Strings.
 *
 * @see StringUtil
 *
 * @author Patches
 */
public class StringUtilTests {

  private static final String STRING_EMPTY = "";
  private static final String STRING_SINGLE_SPACE = " ";
  private static final String STRING_TEST = "test";

  public StringUtilTests() {
    // No-op
  }

  @Test
  public void isEmpty_true_null() {
    assertTrue(StringUtil.isEmpty(null));
  }

  @Test
  public void isEmpty_true_zeroCharacters() {
    assertTrue(StringUtil.isEmpty(STRING_EMPTY));
  }

  @Test
  public void isEmpty_true_space() {
    assertTrue(StringUtil.isEmpty(STRING_SINGLE_SPACE));
  }

  @Test
  public void isEmpty_false() {
    assertFalse(StringUtil.isEmpty(STRING_TEST));
  }

  @Test
  public void isNotEmpty_false_null() {
    assertFalse(StringUtil.isNotEmpty(null));
  }

  @Test
  public void isNotEmpty_false_zeroCharacters() {
    assertFalse(StringUtil.isNotEmpty(STRING_EMPTY));
  }

  @Test
  public void isNotEmpty_false_space() {
    assertFalse(StringUtil.isNotEmpty(STRING_SINGLE_SPACE));
  }

  @Test
  public void isNotEmpty_true() {
    assertTrue(StringUtil.isNotEmpty(STRING_TEST));
  }
}
