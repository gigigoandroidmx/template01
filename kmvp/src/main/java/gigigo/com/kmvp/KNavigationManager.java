package gigigo.com.kmvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ajea on 16/05/17.
 */

public class KNavigationManager {

    private static FragmentManager fragmentManager;

    public KNavigationManager(@NonNull FragmentManager fragmentManager) {
        KNavigationManager.fragmentManager = fragmentManager;
    }

    public void replaceFragment(Fragment fragment, int idContainer) {
        fragmentManager.beginTransaction().replace(idContainer,fragment).commit();
    }

    public void addFragmentToBackStack(@NonNull Fragment fragment, int idContainer) {
        if(fragment != null && idContainer > 0) {
            if (!exitsFragment(fragment.getClass().getName())) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(idContainer, fragment);
                ft.addToBackStack(fragment.getClass().getName());
                ft.commit();
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if(fragment != null) {
            fragmentManager.beginTransaction().remove(fragment);
            fragmentManager.beginTransaction().commit();
            fragmentManager.popBackStack();
        }
    }

    public void navigateBack(Activity baseActivity) {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            baseActivity.finish();
        } else {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void openAsRoot(Fragment fragment, int idContainer) {
        if(fragment != null && idContainer > 0) {
            popAllFragment();
            replaceFragment(fragment, idContainer);
        }
    }

    private void popAllFragment() {
        // Clear all back stack.
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } /* end of for */
    }

    public void popFragments(String fragmentName){
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if(backStackCount > 0) {
            for (int i = 1; i < backStackCount; i++) {
                fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    public boolean exitsFragment(String name) {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && fragment.isAdded() && fragment.getClass().getName().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
