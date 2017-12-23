package com.isupatches.wisefy;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.TEST_SSID2;
import static com.isupatches.wisefy.TestUtils.TEST_TIMEOUT;
import static com.isupatches.wisefy.TestUtils.TEST_TYPE1;
import static com.isupatches.wisefy.TestUtils.TEST_TYPE2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;

/**
 * Used to test the WiseFyConnection class and functionality determining various connection states.
 *
 * @see WiseFyConnection
 *
 * @author Patches
 */
public class WiseFyConnectionTests extends AbstractBaseAndroidJUnit4TestClass {

  private WiseFyConnection wisefyConnection;

  public WiseFyConnectionTests() {
    // No-op
  }

  @Before
  public void setUp() {
    wisefyConnection = WiseFyConnection.create(getMockWiseFyPrerequisites());
  }

  /*
   * isCurrentNetworkConnectedToSSID tests
   */

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_nullSSIDParam() {
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(null));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_nullConnectionInfo() {
    getMockNetworkUtil().currentNetwork_null();
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_nullSSID() {
    getMockNetworkUtil().currentNetwork(null);
    getMockNetworkUtil().currentNetworkConnectionStatus(true, true, null);
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_differentSSID() {
    getMockNetworkUtil().currentNetwork(TEST_SSID2);
    getMockNetworkUtil().currentNetworkConnectionStatus(true, true, null);
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_notAvailable() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    getMockNetworkUtil().currentNetworkConnectionStatus(false, true, null);
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_notAvailableOrConnected() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    getMockNetworkUtil().currentNetworkConnectionStatus(false, false, null);
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_failure_notConnected() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    getMockNetworkUtil().currentNetworkConnectionStatus(true, false, null);
    assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  @Test
  public void isCurrentNetworkConnectedToSSID_success() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    getMockNetworkUtil().currentNetworkConnectionStatus(true, true, null);
    assertTrue(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID));
  }

  /*
   * isNetworkConnected tests
   */

  @Test
  public void isNetworkConnected_failure_nullNetworkInfoParam() {
    assertFalse(wisefyConnection.isNetworkConnected(null));
  }

  @Test
  public void isNetworkConnected_failure_notAvailable() {
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(false, true, null);
    assertFalse(wisefyConnection.isNetworkConnected(networkInfo));
  }

  @Test
  public void isNetworkConnected_failure_notAvailableOrConnected() {
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(false, false, null);
    assertFalse(wisefyConnection.isNetworkConnected(networkInfo));
  }

  @Test
  public void isNetworkConnected_failure_notConnected() {
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(true, false, null);
    assertFalse(wisefyConnection.isNetworkConnected(networkInfo));
  }

  @Test
  public void isNetworkConnected_success() {
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(true, true, null);
    assertTrue(wisefyConnection.isNetworkConnected(networkInfo));
  }

  /*
   * isNetworkConnectedAndMatchesType tests
   */

  @Test
  public void isNetworkConnectedAndMatchesType_failure_nullNetworkInfo() {
    assertFalse(wisefyConnection.isNetworkConnectedAndMatchesType(null, TEST_TYPE1));
  }

  @Test
  public void isNetworkConnectedAndMatchesType_failure_notAvailable() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(false, true, TEST_TYPE1);
    assertFalse(wisefyConnection.isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
  }

  @Test
  public void isNetworkConnectedAndMatchesType_failure_notAvailableOrConnected() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(false, false, TEST_TYPE1);
    assertFalse(wisefyConnection.isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
  }

  @Test
  public void isNetworkConnectedAndMatchesType_failure_notConnected() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(true, false, TEST_TYPE1);
    assertFalse(wisefyConnection.isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
  }

  @Test
  public void isNetworkConnectedAndMatchesType_failure_nullTypeName() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(true, true, null);
    assertFalse(wisefyConnection.isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
  }

  @Test
  public void isNetworkConnectedAndMatchesType_failure_differentTypeName() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(true, true, TEST_TYPE2);
    assertFalse(wisefyConnection.isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
  }

  @Test
  public void isNetworkConnectedAndMatchesType_success() {
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    final NetworkInfo networkInfo = getMockNetworkUtil().currentNetworkConnectionStatus(true, true, TEST_TYPE1);
    assertTrue(wisefyConnection.isNetworkConnectedAndMatchesType(networkInfo, TEST_TYPE1));
  }

  /*
   * waitToConnectToSSID tests
   */

  @Test
  public void waitToConnectToSSID_failure_nullSSIDParam() {
    assertFalse(wisefyConnection.waitToConnectToSSID(null, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_failure_nullConnectionInfo() {
    getMockNetworkUtil().currentNetwork_null();
    assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_failure_nullSSID() {
    getMockNetworkUtil().currentNetwork(null);
    assertFalse(wisefyConnection.waitToConnectToSSID(null, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_failure_differentSSID() {
    getMockNetworkUtil().currentNetworkConnectionStatus(true, true, TEST_TYPE1);
    getMockNetworkUtil().currentNetwork(TEST_SSID2);
    assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_failure_notAvailable() {
    getMockNetworkUtil().currentNetworkConnectionStatus(false, true, TEST_TYPE1);
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_failure_notAvailableOrConnected() {
    getMockNetworkUtil().currentNetworkConnectionStatus(false, false, TEST_TYPE1);
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_failure_notConnected() {
    getMockNetworkUtil().currentNetworkConnectionStatus(true, false, TEST_TYPE1);
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
  }

  @Test
  public void waitToConnectToSSID_success() {
    getMockNetworkUtil().currentNetworkConnectionStatus(true, true, TEST_TYPE1);
    getMockNetworkUtil().currentNetwork(TEST_SSID);
    assertTrue(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT));
  }
}
