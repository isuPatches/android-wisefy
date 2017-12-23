package com.isupatches.wisefy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Used to test the WiseFyPrerequisites class and functionality pertaining to getting
 * a connectivity and/or wifi manager.
 *
 * @see WiseFyPrerequisites
 *
 * @author Patches
 */
public class WiseFyPrerequisitesTests extends AbstractBaseAndroidJUnit4TestClass {

  private WiseFyPrerequisites wisefyPrerequisites;

  public WiseFyPrerequisitesTests() {
    // No-op
  }

  @Test
  public void hasPrerequisites_nullWifiManager() {
    wisefyPrerequisites = WiseFyPrerequisites.create(getMockConnectivityManager(), null);
    assertFalse(wisefyPrerequisites.hasPrerequisites());
  }

  @Test
  public void hasPrerequisites_nullConnectivityManager() {
    wisefyPrerequisites = WiseFyPrerequisites.create(null, getMockWifiManager());
    assertFalse(wisefyPrerequisites.hasPrerequisites());
  }

  @Test
  public void hasPrerequisites_noManagers() {
    wisefyPrerequisites = WiseFyPrerequisites.create(null, null);
    assertFalse(wisefyPrerequisites.hasPrerequisites());
  }

  @Test
  public void hasPrerequisites_bothManagers() {
    wisefyPrerequisites = WiseFyPrerequisites.create(getMockConnectivityManager(), getMockWifiManager());
    assertTrue(wisefyPrerequisites.hasPrerequisites());
  }

  @Test
  public void missingPrerequisites_nullWifiManager() {
    wisefyPrerequisites = WiseFyPrerequisites.create(getMockConnectivityManager(), null);
    assertTrue(wisefyPrerequisites.missingPrerequisites());
  }

  @Test
  public void missingPrerequisites_nullConnectivityManager() {
    wisefyPrerequisites = WiseFyPrerequisites.create(null, getMockWifiManager());
    assertTrue(wisefyPrerequisites.missingPrerequisites());
  }

  @Test
  public void missingPrerequisites_noManagers() {
    wisefyPrerequisites = WiseFyPrerequisites.create(null, null);
    assertTrue(wisefyPrerequisites.missingPrerequisites());
  }

  @Test
  public void missingPrerequisites_bothManagers() {
    wisefyPrerequisites = WiseFyPrerequisites.create(getMockConnectivityManager(), getMockWifiManager());
    assertFalse(wisefyPrerequisites.missingPrerequisites());
  }

  @Test
  public void connectivityManager_getterAndSetter() {
    wisefyPrerequisites = WiseFyPrerequisites.create(getMockConnectivityManager(), getMockWifiManager());
    assertEquals(getMockConnectivityManager(), wisefyPrerequisites.getConnectivityManager());
  }

  @Test
  public void wifiManager_getterAndSetter() {
    wisefyPrerequisites = WiseFyPrerequisites.create(getMockConnectivityManager(), getMockWifiManager());
    assertEquals(getMockWifiManager(), wisefyPrerequisites.getWifiManager());
  }
}
