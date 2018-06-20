package com.isupatches.wisefy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

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
   * @param mockWiseFyPrerequisites The mock WiseFyPrerequisites instance to use
   *
   * @see WiseFyPrerequisites
   */
  MockWiseFyPrerequisitesUtil(@NonNull final WiseFyPrerequisites mockWiseFyPrerequisites) {
    this.mockWiseFyPrerequisites = mockWiseFyPrerequisites;
  }

  /**
   * Mocks an active network return.
   *
   * @see ConnectivityManager#getActiveNetworkInfo()
   * @see WiseFyPrerequisites#getConnectivityManager()
   */
  public void activeNetwork() {
    when(mockWiseFyPrerequisites.getConnectivityManager().getActiveNetworkInfo()).thenReturn(mock(NetworkInfo.class));
  }

  /**
   * Mocks having all required prerequisites.
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFyPrerequisites#missingPrerequisites()
   */
  void hasPrerequisites() {
    when(mockWiseFyPrerequisites.hasPrerequisites()).thenReturn(true);
    when(mockWiseFyPrerequisites.missingPrerequisites()).thenReturn(false);
  }

  /**
   * Mocks missing one of the required prerequisites.
   *
   * @see WiseFyPrerequisites#hasPrerequisites()
   * @see WiseFyPrerequisites#missingPrerequisites()
   */
  void missingPrerequisites() {
    when(mockWiseFyPrerequisites.hasPrerequisites()).thenReturn(false);
    when(mockWiseFyPrerequisites.missingPrerequisites()).thenReturn(true);
  }
}
