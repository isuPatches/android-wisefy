package com.isupatches.wisefy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * A class to mock returns from the WiseFyConnection class.
 *
 * @see WiseFyConnection
 *
 * @author Patches
 */
public final class MockWiseFyConnectionUtil {

  private final WiseFyConnection mockWiseFyConnection;

  /**
   * Constructor.
   *
   * @param mockWiseFyConnection The mock WiseFyConnection class to use
   *
   * @see WiseFyConnection
   */
  MockWiseFyConnectionUtil(@NonNull final WiseFyConnection mockWiseFyConnection) {
    this.mockWiseFyConnection = mockWiseFyConnection;
  }

  /**
   * Mocks if the device is connected to a specific SSID.
   *
   * @param connected Whether the device is connected to the specific SSID or not
   *
   * @see WiseFyConnection#isCurrentNetworkConnectedToSSID(String)
   */
  public void isCurrentNetworkConnectedToSSID(final boolean connected) {
    when(mockWiseFyConnection.isCurrentNetworkConnectedToSSID(anyString())).thenReturn(connected);
  }

  /**
   * Mocks if the device is connected to a network.
   *
   * @param connected Whether the device is connected or not
   *
   * @see WiseFyConnection#isNetworkConnected(NetworkInfo)
   */
  public void isNetworkConnected(final boolean connected) {
    when(mockWiseFyConnection.isNetworkConnected(any(NetworkInfo.class))).thenReturn(connected);
  }

  /**
   * To mock if the device is connected to a mobile network.
   *
   * @param connectedAndMatchesType If the device is connected to a mobile network
   *
   * @see WiseFyConnection#isNetworkConnectedAndMatchesType(NetworkInfo, String)
   */
  public void isNetworkConnectedAndMatchesType(final boolean connectedAndMatchesType) {
    when(mockWiseFyConnection.isNetworkConnectedAndMatchesType(any(NetworkInfo.class), anyString())).thenReturn(connectedAndMatchesType);
  }

  /**
   * Mocks if waitToConnectToSSID is successful.
   *
   * @param success Whether waitToConnectToSSID succeeds or not
   *
   * @see WiseFyConnection#waitToConnectToSSID(String, int)
   */
  public void waitToConnectToSSID(final boolean success) {
    when(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(success);
  }
}
