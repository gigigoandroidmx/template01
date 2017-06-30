package gigigo.com.template.presentation.utils.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class SharedSettings {
    private Context mContext;
    private SharedPreferences mSharedPreferences;

    private Gson mGson;

    public Context getContext() {
        return mContext;
    }

    private SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public SharedSettings setContext(Context context) {
        mContext = context;
        mGson = new Gson();
        return this;
    }

    public SharedSettings setSharedPreferencesName(String name) {
        mSharedPreferences = getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return this;
    }

    public boolean settingExist(String key) {
        boolean exist;

        try {
            exist = getSharedPreferences().contains(key);
        } catch (Exception exception) {
            exist = false;
        }

        return  exist;
    }

    public String getSetting(String key, String otherwise) {
        return getSharedPreferences().getString(key, otherwise);
    }

    public void setSetting(String key, String data, boolean replaceIfExist) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();

        if(replaceIfExist) {
            editor.putString(key, data);
            editor.apply();
        }
        else {
            if(!settingExist(key)) {
                editor.putString(key, data);
                editor.apply();
            }
        }
    }

    public boolean deleteSetting(String key) {
        try {
            SharedPreferences.Editor editor = getSharedPreferences().edit();
            return editor.remove(key).commit();
        } catch (Exception ex) {
            return false;
        }
    }

    public <T> T getSettingFromJson(String key, Class<T> typeClass, T otherwise) {
        T setting;

        if(settingExist(key)) {
            String jsonPreference = getSetting(key, null);
            setting = deserialize(jsonPreference, typeClass);
        }
        else {
            setting = otherwise;
        }

        return setting;
    }

    public <T> ArrayList<T> getSettingFromJson(String key, Type type, ArrayList<T> otherwise) {
        ArrayList<T> setting;

        if(settingExist(key)) {
            String jsonPreference = getSetting(key, null);
            setting = deserialize(jsonPreference, type);
        }
        else {
            setting = otherwise;
        }

        return setting;
    }

    public <T> void setSettingToJson(String key, T data, boolean replaceIfExist) {
        String json = serialize(data);
        setSetting(key, json, replaceIfExist);
    }

    public <T> String serialize(T data) {
        String json;

        try {
            json = mGson.toJson(data);
        } catch (JsonSyntaxException e) {
            json = null;
        }

        return json;
    }

    public <T> T deserialize(String json, Class<T> typeClass) {
        T data;

        try {
            data = mGson.fromJson(json, typeClass);
        } catch (JsonSyntaxException e) {
            data = null;
        }

        return data;
    }

    public <T> T deserialize(String json, Type type) {
        T data;

        try {
            data = mGson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            data = null;
        }

        return data;
    }
}
