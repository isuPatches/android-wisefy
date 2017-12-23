package com.isupatches.wisefy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.runner.RunWith;

/**
 * A common test class to extend.
 *
 * @author Patches
 */
@RunWith(AndroidJUnit4.class)
public abstract class AbstractBaseAndroidJUnit4TestClass {

  private final WiseFy wisefy;
  private final WiseFyPrerequisites mockWiseFyPrerequisites;
  private final WiseFySearch mockWiseFySearch;

  private final ConnectivityManager mockConnectivityManager;
  private final WifiManager mockWifiManager;

  private final MockNetworkUtil mockNetworkUtil;
  private final MockWiseFyConnectionUtil mockWiseFyConnectionUtil;
  private final MockWiseFyPrechecksUtil mockWiseFyPrechecksUtil;
  private final MockWiseFyPrerequisitesUtil mockWiseFyPrerequisitesUtil;
  private final MockWiseFySearchUtil mockWiseFySearchUtil;

  private final NullCallbackUtil nullCallbackUtil;

  private final VerificationUtil verificationUtil;

  /**
   * Default constructor.
   */
  public AbstractBaseAndroidJUnit4TestClass() {
    final WiseFyConnection mockWiseFyConnection = mock(WiseFyConnection.class);
    final WiseFyPrechecks mockWiseFyPrechecks = mock(WiseFyPrechecks.class);
    mockWiseFyPrerequisites = mock(WiseFyPrerequisites.class);
    mockWiseFySearch = mock(WiseFySearch.class);

    mockWifiManager = mock(WifiManager.class);
    mockConnectivityManager = mock(ConnectivityManager.class);

    wisefy = new WiseFy.brains(InstrumentationRegistry.getTargetContext())
      .setCustomWiseFyConnection(mockWiseFyConnection)
      .setCustomWiseFyPrechecks(mockWiseFyPrechecks)
      .setCustomWiseFyPrerequisites(mockWiseFyPrerequisites)
      .setCustomWiseFySearch(mockWiseFySearch)
      .logging(true)
      .getSmarts();

    mockNetworkUtil = new MockNetworkUtil(mockConnectivityManager, mockWifiManager);
    mockWiseFyConnectionUtil = new MockWiseFyConnectionUtil(mockWiseFyConnection);
    mockWiseFyPrechecksUtil = new MockWiseFyPrechecksUtil(mockWiseFyPrechecks);
    mockWiseFyPrerequisitesUtil = new MockWiseFyPrerequisitesUtil(mockWiseFyPrerequisites);
    mockWiseFySearchUtil = new MockWiseFySearchUtil(mockWiseFySearch);

    nullCallbackUtil = new NullCallbackUtil(wisefy);

    verificationUtil = new VerificationUtil(mockWifiManager);

    setManagers();
  }

  @After
  public void tearDown() {
    wisefy.dump();
  }

  @NonNull
  public final WiseFy getWiseFy() {
    return wisefy;
  }

  public final WiseFyPrerequisites getMockWiseFyPrerequisites() {
    return mockWiseFyPrerequisites;
  }

  public final WiseFySearch getMockWiseFySearch() {
    return mockWiseFySearch;
  }

  public final ConnectivityManager getMockConnectivityManager() {
    return mockConnectivityManager;
  }

  public final WifiManager getMockWifiManager() {
    return mockWifiManager;
  }

  @NonNull
  public final MockNetworkUtil getMockNetworkUtil() {
    return mockNetworkUtil;
  }

  @NonNull
  public final MockWiseFyConnectionUtil getMockWiseFyConnectionUtil() {
    return mockWiseFyConnectionUtil;
  }

  @NonNull
  public final MockWiseFyPrechecksUtil getMockWiseFyPrechecksUtil() {
    return mockWiseFyPrechecksUtil;
  }

  @NonNull
  public final MockWiseFyPrerequisitesUtil getMockWiseFyPrerequisitesUtil() {
    return mockWiseFyPrerequisitesUtil;
  }

  @NonNull
  public final MockWiseFySearchUtil getMockWiseFySearchUtil() {
    return mockWiseFySearchUtil;
  }

  @NonNull
  public final NullCallbackUtil getNullCallbackUtil() {
    return nullCallbackUtil;
  }

  @NonNull
  public final VerificationUtil getVerificationUtil() {
    return verificationUtil;
  }

  /*
   * HELPERS
   */

  /**
   * Checks to see if the device has Lollipop or higher.
   *
   * @return bool - true if device is at least Lollipop
   */
  protected final boolean preLollipop() {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
  }

  /**
   * To setup mock returns for WiseFyPrerequisites.
   */
  private void setManagers() {
    when(mockWiseFyPrerequisites.hasPrerequisites()).thenReturn(true);
    when(mockWiseFyPrerequisites.missingPrerequisites()).thenReturn(false);
    when(mockWiseFyPrerequisites.getWifiManager()).thenReturn(mockWifiManager);
    when(mockWiseFyPrerequisites.getConnectivityManager()).thenReturn(mockConnectivityManager);
  }
}
