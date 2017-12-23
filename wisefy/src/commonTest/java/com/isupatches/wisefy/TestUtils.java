package com.isupatches.wisefy;

import java.util.regex.Pattern;

/**
 * Common constants and methods for tests.
 *
 * @author Patches
 */
public final class TestUtils {

  public static final String OPEN_NETWORK_SSID = "Open Network";

  public static final String WEP_NETWORK_SSID = "WEP Network";
  public static final String WEP_NETWORK_PASSWORD = "12345678";

  public static final String WPA2_NETWORK_SSID = "WPA2 Network";
  public static final String WPA2_NETWORK_PASSWORD = "123456";

  public static final int TEST_DELAY = 1;

  public static final int TEST_NUMBER_OF_BARS = 5;

  public static final String TEST_SSID = "Test Network";

  public static final int TEST_NETWORK_FREQUENCY_BELOW_5GHZ = WiseFy.MIN_FREQUENCY_5GHZ - 1;
  public static final int TEST_NETWORK_FREQUENCY_ABOVE_5GHZ = WiseFy.MAX_FREQUENCY_5GHZ + 1;

  public static final int TEST_NETWORK_FREQUENCY_24GHZ = 2400;
  public static final int TEST_NETWORK_FREQUENCY_5GHZ = 5000;

  public static final int TEST_RSSI_LEVEL = -60;
  public static final int TEST_RSSI_LEVEL_LOW = -70;
  public static final int TEST_RSSI_LEVEL_HIGH = -35;

  public static final int TEST_TIMEOUT = 1;

  public static final Integer VERIFICATION_SUCCESS_TIMEOUT = 5000;
  public static final Integer VERIFICATION_FAILURE_TIMEOUT = 3000;

  public static final String TEST_IP_ADDRESS_STRING = "127.0.0.1";

  static final String TEST_REGEX = ".*Test.*";

  static final String TEST_TYPE1 = "TYPE 1";
  static final String TEST_TYPE2 = "TYPE 2";

  static final String TEST_SSID2 = "Test Network 2";
  static final String TEST_SSID3 = "Test Network 3";

  static final int TEST_IP_ADDRESS_INT = getIntVersionOfTestIPAddress();

  private static final int BIT_SHIFT_VALUE = 8;

  private TestUtils() {
    // No-op
  }

  /**
   * To convert the TEST_IP_ADDRESS_STRING string into an integer value to be used for Inet and
   * tests.
   *
   * @see #TEST_IP_ADDRESS_STRING
   *
   * @return int - The int value of a given IP
   */
  private static int getIntVersionOfTestIPAddress() {
    int result = 0;

    // iterate over each octet
    for (String part : TEST_IP_ADDRESS_STRING.split(Pattern.quote("."))) {
      // Shift the previously parsed bits over by 1 byte
      result = result << BIT_SHIFT_VALUE;
      // Set the low order bits to the current octet
      result |= Integer.parseInt(part);
    }
    return result;
  }
}
