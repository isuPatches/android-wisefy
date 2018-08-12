/*
 * Copyright 2017 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.wisefy.callbacks;

/**
 * Callbacks for finding a nearby SSID on a device.
 *
 * @see com.isupatches.wisefy.WiseFy#searchForSSID(String, int, SearchForSSIDCallbacks)
 */
public interface SearchForSSIDCallbacks {
  void searchForSSIDWiseFyFailure(int wisefyReturnCode);

  void ssidFound(String ssid);

  void ssidNotFound();
}
