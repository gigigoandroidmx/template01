package gigigo.com.template.presentation.utils.sharedpreferences;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class SharedPreferencesManager {

    private static final String TAG = SharedPreferencesManager.class.getSimpleName();

    private static SharedSettings sSharedSettings;

    public static SharedSettings init(@NonNull Context context) {
        sSharedSettings = new SharedSettings();
        return sSharedSettings.setContext(context);
    }

    public static SharedSettings getSettings() {
        return sSharedSettings;
    }

    public static <T> T getSharedPreferenceAs(Class<T> classType, String key, T defaultValue) {
        try {
            if(getSettings() != null) {
                return getSettings().getSettingFromJson(key, classType, defaultValue);
            }
        } catch (Exception exception) {
            Log.e(TAG, "Get Shared Preference: " + exception.getMessage());
            return defaultValue;
        }

        return null;
    }

    public static <T> boolean setSharedPreferenceAs(String key, T data, boolean replaceIfExist) {
        try {
            if(getSettings() != null) {
                getSettings().setSettingToJson(key, data, replaceIfExist);
                return true;
            }
        } catch (Exception exception) {
            Log.e(TAG, "Set Shared Preference: " + exception.getMessage());
            return false;
        }

        return false;
    }

    public static <T> boolean deleteSharedPreference(String key) {
        try {
            if(getSettings() != null) {
                return getSettings().deleteSetting(key);
            }
        } catch (Exception exception) {
            Log.e(TAG, "Delete Shared Preference: " + exception.getMessage());
            return false;
        }

        return false;
    }

    public static <T> boolean setSharedPreferenceAs(String key, ArrayList<T> data, boolean replaceIfExist) {
        try {
            if(getSettings() != null) {
                getSettings().setSettingToJson(key, data, replaceIfExist);
                return true;
            }
        } catch (Exception exception) {
            Log.e(TAG, "Set Shared Preference: " + exception.getMessage());
            return false;
        }

        return false;
    }

    public static <T> ArrayList<T> getSharedPreferenceAs(String key, ArrayList<T> defaultValue) {
        try {
            if(getSettings() != null) {
                Type type = new TypeToken<ArrayList<T>>() {}.getType();
                return getSettings().getSettingFromJson(key, type, defaultValue);
            }
        } catch (Exception exception) {
            Log.e(TAG, "Get Shared Preference: " + exception.getMessage());
            return defaultValue;
        }

        return null;
    }
}
