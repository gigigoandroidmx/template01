package gigigo.com.template.presentation.utils.permissions;

/**
 * Created by Omar on 1/16/17.
 */

public interface PermissionsResult {
    void onPermissionsGranted(int requestCode);
    void onPermissionsDenied(int requestCode);
    void onPermissionsDeniedPermanently(int requestCode);
}
