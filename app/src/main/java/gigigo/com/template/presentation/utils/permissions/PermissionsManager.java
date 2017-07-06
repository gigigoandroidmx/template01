package gigigo.com.template.presentation.utils.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by Omar on 7/6/17.
 */

public class PermissionsManager {

    private String[] requestedPermissions;
    private PermissionsResult permissionsResult;
    private RequestPermissionRationale requestPermissionRationale;
    private Context context;
    private int requestCode;
    private Fragment fragment;
    private boolean isFragment = false;
    boolean showRequestPermissionRationaleOnStart = false;
    boolean showRequestPermissionRationaleOnEnd = false;

    public PermissionsManager(Context context) {
        this.context = context;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
        isFragment = true;
    }

    public void setPermissionsResult(PermissionsResult permissionsResult) {
        this.permissionsResult = permissionsResult;
    }

    public void setRequestPermissionRationale(RequestPermissionRationale requestPermissionRationale) {
        this.requestPermissionRationale = requestPermissionRationale;
    }

    public void checkPermissions(String[] permissions,
                                 int requestCode,
                                 boolean showRequestPermissionRationaleOnStart,
                                 boolean showRequestPermissionRationaleOnEnd) {
        if (isFragment && fragment == null) {
            /*throw new Exception("Fragment is required");*/
        }

        validatePermissions(permissions, requestCode,
                showRequestPermissionRationaleOnStart, showRequestPermissionRationaleOnEnd);
    }

    public void checkPermissions(String[] permissions,
                                 int requestCode) {
        this.checkPermissions(permissions, requestCode, false, false);
    }

    private void validatePermissions(String[] permissions,
                                     int requestCode,
                                     boolean showRequestPermissionRationaleOnStart,
                                     boolean showRequestPermissionRationaleOnEnd) {
        this.requestCode = requestCode;
        this.requestedPermissions = permissions;
        this.showRequestPermissionRationaleOnStart = showRequestPermissionRationaleOnStart;
        this.showRequestPermissionRationaleOnEnd = showRequestPermissionRationaleOnEnd;

        if (hasAllPermissions(permissions)) {

            if (permissionsResult != null)
                permissionsResult.onPermissionsGranted(requestCode);

            return;
        }

        if (ActivityCompat
                .shouldShowRequestPermissionRationale(isFragment ? fragment.getActivity() : (Activity) context,
                        permissions[0]) && showRequestPermissionRationaleOnStart) {

            if (requestPermissionRationale != null)
                requestPermissionRationale.showRequestPermissionRationale(requestCode);
            else
                showRequestPermissionRationaleAlert();
        } else {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        if (fragment != null) {
            fragment.requestPermissions(requestedPermissions, requestCode);
        } else {
            ActivityCompat.requestPermissions((Activity) context, requestedPermissions, requestCode);
        }
    }

    private void showRequestPermissionRationaleAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Permission required")
                .setMessage("We require this permission because of reasons")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (permissionsResult != null)
                            permissionsResult.onPermissionsDenied(requestCode);
                    }
                })
                .create();
        alertDialog.show();
    }

    private boolean hasAllPermissions(String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (!hasPermissionGrated(permissions[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean hasPermissionGrated(String permissionName) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, permissionName);

        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && this.requestCode == requestCode) {

            int permissionDeniedPosition = allPermissionRequestedGranted(grantResults);

            if (permissionsResult == null)
                return;

            if (permissionDeniedPosition == -1) {
                permissionsResult.onPermissionsGranted(requestCode);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        requestedPermissions[permissionDeniedPosition])) {
                    if (showRequestPermissionRationaleOnEnd) {
                        if (requestPermissionRationale != null)
                            requestPermissionRationale.showRequestPermissionRationale(requestCode);
                        else
                            showRequestPermissionRationaleAlert();
                    } else {
                        permissionsResult.onPermissionsDenied(requestCode);
                    }

                } else {
                    permissionsResult.onPermissionsDeniedPermanently(requestCode);
                }
            }
        }
    }

    private int allPermissionRequestedGranted(int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return i;
            }
        }

        return -1;
    }
}
