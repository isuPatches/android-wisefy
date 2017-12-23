package com.isupatches.wisefy.main;

import static com.isupatches.wisefy.TestUtils.TEST_SSID;
import static com.isupatches.wisefy.TestUtils.VERIFICATION_SUCCESS_TIMEOUT;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import android.net.wifi.WifiConfiguration;

import com.isupatches.wisefy.AbstractBaseAndroidJUnit4TestClass;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks;
import com.isupatches.wisefy.constants.WiseFyCodeDefs;

import java.util.List;

import org.junit.Test;

/**
 * Tests the ability to retrieve a list of saved networks on a device.
 *
 * @see WiseFy#getSavedNetworks()
 * @see WiseFy#getSavedNetworks(GetSavedNetworksCallbacks)
 * @see WiseFy#getSavedNetworks(String)
 * @see WiseFy#getSavedNetworks(String, GetSavedNetworksCallbacks)
 */
public class GetSavedNetworksTests extends AbstractBaseAndroidJUnit4TestClass {

  public GetSavedNetworksTests() {
    // No-op
  }

  @Test
  public void sync_failure_prechecks() {
    getMockWiseFyPrechecksUtil().getSavedNetworks_failure();
    assertEquals(null, getWiseFy().getSavedNetworks());
    getVerificationUtil().didNotTryToGetSavedNetworks();
  }

  @Test
  public void sync_success() {
    final List<WifiConfiguration> savedNetworks = getMockNetworkUtil().savedNetworks();
    assertEquals(savedNetworks, getWiseFy().getSavedNetworks());
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void async_failure_prechecks() {
    getMockWiseFyPrechecksUtil().getSavedNetworks_failure();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworksWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
    getVerificationUtil().didNotTryToGetSavedNetworks();
  }

  @Test
  public void async_failure_prechecks_nullCallback() {
    getMockWiseFyPrechecksUtil().getSavedNetworks_failure();
    getNullCallbackUtil().callGetSavedNetworks();
    getVerificationUtil().didNotTryToGetSavedNetworks();
  }

  @Test
  public void async_failure_nullSavedNetworks() {
    getMockNetworkUtil().getConfiguredNetworks_null();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void async_failure_nullSavedNetworks_nullCallback() {
    getMockNetworkUtil().getConfiguredNetworks_null();
    getNullCallbackUtil().callGetSavedNetworks();
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void async_failure_emptyConfiguredNetworks() {
    getMockNetworkUtil().getConfiguredNetworks_emptyList();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void async_failure_emptyConfiguredNetworks_nullCallback() {
    getMockNetworkUtil().getConfiguredNetworks_emptyList();
    getNullCallbackUtil().callGetSavedNetworks();
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void async_success() {
    final List<WifiConfiguration> savedNetworks = getMockNetworkUtil().savedNetworks();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(savedNetworks);
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void async_success_nullCallback() {
    getMockNetworkUtil().savedNetworks();
    getNullCallbackUtil().callGetSavedNetworks();
    getVerificationUtil().triedToGetSavedNetworks();
  }

  @Test
  public void sync_failure_prechecks_withRegex() {
    getMockWiseFyPrechecksUtil().getSavedNetworks_failure();
    assertEquals(null, getWiseFy().getSavedNetworks(TEST_SSID));
    getVerificationUtil().didNotTryToGetSavedNetworks();
  }

  @Test
  public void sync_success_withRegex() {
    final List<WifiConfiguration> savedNetwork = getMockWiseFySearchUtil().findSavedNetworksMatchingRegex_success();
    assertEquals(savedNetwork, getWiseFy().getSavedNetworks(TEST_SSID));
  }

  @Test
  public void async_failure_prechecks_withRegex() {
    getMockWiseFyPrechecksUtil().getSavedNetworks_failure();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).getSavedNetworksWiseFyFailure(WiseFyCodeDefs.MISSING_PREREQUISITE);
  }

  @Test
  public void async_failure_prechecks_withRegex_nullCallback() {
    getMockWiseFyPrechecksUtil().getSavedNetworks_failure();
    getNullCallbackUtil().callGetSavedNetworks_withRegex(TEST_SSID);
    getWiseFy().getSavedNetworks(TEST_SSID, null);
  }

  @Test
  public void async_failure_withRegex_nullSavedNetworks() {
    getMockWiseFySearchUtil().findSavedNetworksByRegex_null();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
  }

  @Test
  public void async_failure_withRegex_nullSavedNetworks_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworksByRegex_null();
    getNullCallbackUtil().callGetSavedNetworks_withRegex(TEST_SSID);
  }

  @Test
  public void async_failure_withRegex_emptyConfiguredNetworks() {
    getMockWiseFySearchUtil().findSavedNetworksByRegex_emptyList();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound();
  }

  @Test
  public void async_failure_withRegex_emptyConfiguredNetworks_nullCallback() {
    getMockWiseFySearchUtil().findSavedNetworksByRegex_emptyList();
    getNullCallbackUtil().callGetSavedNetworks_withRegex(TEST_SSID);
  }

  @Test
  public void async_success_withRegex() {
    final List<WifiConfiguration> savedNetworks = getMockWiseFySearchUtil().findSavedNetworksMatchingRegex_success();
    final GetSavedNetworksCallbacks mockCallbacks = mock(GetSavedNetworksCallbacks.class);
    getWiseFy().getSavedNetworks(TEST_SSID, mockCallbacks);
    verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(savedNetworks);
  }

  @Test
  public void async_success_withRegex_nullCallbacks() {
    getMockWiseFySearchUtil().findSavedNetworksMatchingRegex_success();
    getNullCallbackUtil().callGetSavedNetworks_withRegex(TEST_SSID);

  }
}
