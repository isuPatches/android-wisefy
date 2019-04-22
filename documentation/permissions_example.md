The `ACCESS_COARSE_LOCATION` location permission is necessary to query for nearby access points.

First we define the permissions in the manifest:

```xml
 <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```

Then on the activity using WiseFy then we can start asking for permissions and handling the permission callbacks.

_With Kotlin_

```kotlin
package com.isupatches.wisefysample.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.util.PermissionUtil

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var wisefy: WiseFy

    private val permissionUtil = PermissionUtil.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wisefy = WiseFy.Brains(this).logging(true).getSmarts()

        if (checkForPermissions()) {
            getNearbyAccessPoints()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wisefy.dump()
    }

    private fun checkForPermissions(): Boolean {
        return isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_PERMISSION_REQUEST_CODE)
    }

    private fun isPermissionGranted(permission: String, requestCode: Int): Boolean {
        return if (permissionUtil.permissionNotGranted(this, permission)) {
            if (permissionUtil.shouldShowPermissionRationale(this, permission)) {
                // Display dialog or rationale for requesting permission here
            } else {
                permissionUtil.requestPermissions(this, arrayOf(permission), requestCode)
            }
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "WiseFy permissions granted")
                    // Continue WiseFy logic here
                } else {
                    Log.e(TAG, "Access course location permission denied")
                    // Display permission error here
                }
            } else -> {
                // Display permission error here
                Log.wtf(TAG, "Weird permission requested, not handled")
            }
        }
    }

    @Throws(SecurityException::class)
    private fun getNearbyAccessPoints() {
        wisefy.getNearbyAccessPoints(true, object : GetNearbyAccessPointsCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
                // failure logic goes here
            }

            override  fun retrievedNearbyAccessPoints(accessPoints: List<ScanResult>) {
                // You should see this populate with results after approving the
                // the ACCESS_COARSE_LOCATION permission
                Log.d(TAG, "List: $nearbyAccessPoints")
            }
        })
    }
}
```

_With Java_

```java
package wisefy_sample.isupatches.com.wisefysample.ui;

import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks;

import java.util.List;

import wisefy_sample.isupatches.com.wisefysample.R;
import wisefy_sample.isupatches.com.wisefysample.constants.Permissions;
import wisefy_sample.isupatches.com.wisefysample.util.PermissionUtil;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private WiseFy wisefy;

    private PermissionUtil permissionUtil = PermissionUtil.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wisefy = new WiseFy.Brains(this).logging(true).getSmarts();

        if (checkForPermissions()) {
            getNearbyAccessPoints();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wisefy.dump();
    }

    private boolean checkForPermissions() {
        return isPermissionGranted(ACCESS_COARSE_LOCATION, R.string.access_course_location_permission_rationale, Permissions.ACCESS_COARSE_LOCATION_RESULT_CODE);
    }

    public boolean isPermissionGranted(String permission, int rationaleResId, int requestCode) {
        if (permissionUtil.permissionNotGranted(this, permission)) {
            if (permissionUtil.shouldShowPermissionRationale(this, permission)) {
                // Display dialog or rationale for requesting permission here
            } else {
                permissionUtil.requestPermissions(this, new String[]{permission}, requestCode);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case Permissions.WISEFY_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Wisefy permissions granted");
                    // Continue WiseFy logic here
                } else {
                    Log.e(TAG, "Access course location permission denied");
                    // Display permission error here
                }
                break;
            default:
                Log.wtf(TAG, "Weird permission requested, not handled");
                // Display permission error here
                break;
        }
    }

    private void getNearbyAccessPoints() throws SecurityException {
        wisefy.getNearbyAccessPoints(true, new GetNearbyAccessPointsCallbacks() {
            @Override
            public void retrievedNearbyAccessPoints(List<ScanResult> accessPoints) {
                // You should see this populate with results after approving the
                // the ACCESS_COARSE_LOCATION permission
                Log.d(TAG, "List: " + list.toString());
            }

            @Override
            public void wisefyFailure(int wisefyFailureCode) {

            }
        });
    }
}
```

In this example, PermissionUtil is just an abstracted, shared piece of logic:

_With Kotlin_

```kotlin
package com.isupatches.wisefysample.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PermissionUtil private constructor() {

    companion object {
        private val INSTANCE: PermissionUtil = PermissionUtil()

        fun getInstance(): PermissionUtil = INSTANCE
    }

    fun permissionNotGranted(activity: Activity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED
    }

    fun shouldShowPermissionRationale(activity: Activity, permission: String): Boolean =
        ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)


    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }
}
```

_With Java_

```java
package wisefy_sample.isupatches.com.wisefysample.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    private static final PermissionUtil PERMISSION_UTIL = new PermissionUtil();

    private PermissionUtil() {

    }

    public static PermissionUtil getInstance() {
        return PERMISSION_UTIL;
    }

    public boolean permissionNotGranted(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED;
    }

    public boolean shouldShowPermissionRationale(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }
}
```

And there is a class for storing constants for permission checks:

_With Kotlin_

```
package wisefy_sample.isupatches.com.wisefysample.constants;

internal const val WISEFY_PERMISSION_REQUEST_CODE = 1
```

_With Java_

```java
package wisefy_sample.isupatches.com.wisefysample.constants;

public class Permissions {
    public static final int WISEFY_PERMISSION_REQUEST_CODE = 1;
}
```