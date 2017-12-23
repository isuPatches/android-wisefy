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

```java
package wisefy_sample.isupatches.com.wisefysample.ui;


import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
            case Permissions.ACCESS_COARSE_LOCATION_RESULT_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Access course location permission granted");
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

    private void getNearbyAccessPoints() {
        wisefy = new WiseFy.brains(this).logging(true).getSmarts();

        wisefy.getNearbyAccessPoints(true, new GetNearbyAccessPointsCallbacks() {
            @Override
            public void getNearbyAccessPointsWiseFyFailure(Integer integer) {

            }

            @Override
            public void retrievedNearbyAccessPoints(List<ScanResult> list) {
                // You should see this populate with results after approving the
                // the ACCESS_COARSE_LOCATION permission
                Log.d(TAG, "List: " + list.toString());
            }
        });
    }
}
```

In this example, PermissionUtil is just an abstracted, shared piece of logic:

```java
package wisefy_sample.isupatches.com.wisefysample.util;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


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

```java
package wisefy_sample.isupatches.com.wisefysample.constants;


public class Permissions {
    public static final int ACCESS_COARSE_LOCATION_RESULT_CODE = 1;
}
```