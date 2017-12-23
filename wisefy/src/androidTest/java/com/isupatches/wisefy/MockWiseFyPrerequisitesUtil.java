package com.isupatches.wisefy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.net.NetworkInfo;

/**
 * A class to mock returns from the WiseFyPrerequisites class.
 *
 * @see WiseFyPrerequisites
 *
 * @author Patches
 */
public final class MockWiseFyPrerequisitesUtil {

  private final WiseFyPrerequisites mockWiseFyPrerequisites;

  /**
   * Constructor.
   *
   * @param mockWiseFyPrerequisites The mock WiseFyPrerequisites instance to use.
   */
  MockWiseFyPrerequisitesUtil(final WiseFyPrerequisites mockWiseFyPrerequisites) {
    this.mockWiseFyPrerequisites = mockWiseFyPrerequisites;
  }

  /**
   * Mocks an active network return.
   */
  public void activeNetwork() {
    when(mockWiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));
  }

  /**
   * Mocks having all required prerequisites.
   */
  void hasPrerequisites() {
    when(mockWiseFyPrerequisites.hasPrerequisites()).thenReturn(true);
    when(mockWiseFyPrerequisites.missingPrerequisites()).thenReturn(false);
  }

  /**
   * Mocks missing one of the required prerequisites.
   */
  void missingPrerequisites() {
    when(mockWiseFyPrerequisites.hasPrerequisites()).thenReturn(false);
    when(mockWiseFyPrerequisites.missingPrerequisites()).thenReturn(true);
  }
}
