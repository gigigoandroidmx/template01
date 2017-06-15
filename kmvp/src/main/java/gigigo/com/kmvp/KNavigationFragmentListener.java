package gigigo.com.kmvp;

import android.support.v4.app.Fragment;

/**
 * Defines an listener interface for fragment actions
 *
 * @author Alan Espinosa - 17/05/17
 * @version 2.0.0
 * @since 2.0.0
 */
public interface KNavigationFragmentListener {
    void addFragmentBackstack(Fragment fragment);
    void replaceFragment(Fragment fragment);
    void showActivity(Class type);
}