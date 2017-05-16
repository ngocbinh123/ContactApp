package vn.hcm.nnbinh.contactapp.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public void goToActivityResult(Class activityClass, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        startActivityForResult(intent, requestCode);
    };

    public void quitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected boolean isNeedRequestPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return checkSelfPermission(permission) != PERMISSION_GRANTED;
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void requestPermission(String permission, int requestCode) {
        requestPermissions(new String[]{permission}, requestCode);
    }
}
