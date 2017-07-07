package gigigo.com.template.presentation.utils.permissions;

/**
 * Created by Omar on 7/6/17.
 */

public interface RequestPermissionRationale {
    void showRequestPermissionRationale(int requestCode, UserResponse userResponse);

    interface UserResponse {
        void accepted(int requestCode);
        void canceled(int requestCode);
    }
}
