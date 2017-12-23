package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_IP_ADDRESS_STRING;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetIPCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import org.junit.Test;

/**
 * Tests the ability to get the IP of a device.
 *
 * @see WiseFy#getIP()
 * @see WiseFy#getIP(GetIPCallbacks)
 *
 * @author Patches
 */
public class GetIIPTests extends AbstractBaseAndroidJUnit4TestClass {

  public GetIIPTests() {
    // No-op
  }

  @Test
  public void sync_getIP_failure_missingPrerequisites() {
    getMockWiseFyPrechecksUtil().geIP_failure();
    assertEquals(null, getWiseFy().getIP());
  }

  @Test
  public void sync_getIP_failure() {
    getMockNetworkUtil().ip_failure();
    assertEquals(null, getWiseFy().getIP());
  }

  @Test
  public void sync_getIP_success() {
    getMockNetworkUtil().ip_success();
    assertEquals(TEST_IP_ADDRESS_STRING, getWiseFy().getIP());
  }

  @Test
  public void async_getIP_failure_missingPrerequisites() {
    getMockWiseFyPrechecksUtil().geIP_failure();
    final GetIPCallbacks mockCallbacks = mock(GetIPCallbacks.class);
    getWiseFy().getIP(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getIPWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_getIP_failure() {
    getMockNetworkUtil().ip_failure();
    final GetIPCallbacks mockCallbacks = mock(GetIPCallbacks.class);
    getWiseFy().getIP(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureRetrievingIP();
  }

  @Test
  public void async_getIP_success() {
    getMockNetworkUtil().ip_success();
    final GetIPCallbacks mockCallbacks = mock(GetIPCallbacks.class);
    getWiseFy().getIP(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedIP(TEST_IP_ADDRESS_STRING);
  }
}
