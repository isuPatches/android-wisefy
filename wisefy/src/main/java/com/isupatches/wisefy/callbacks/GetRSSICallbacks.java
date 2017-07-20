package com.isupatches.wisefy.callbacks;


public interface GetRSSICallbacks {
    void retrievedRSSI(Integer rssi);
    void networkNotFoundToRetrieveRSSI();
    void getRSSIWiseFyFailure(Integer wisefyReturnCode);
}
