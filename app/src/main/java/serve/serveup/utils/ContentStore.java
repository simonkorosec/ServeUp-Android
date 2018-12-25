package serve.serveup.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Urban on 8. 12. 2017.
 */

public class ContentStore {

    private static String prefsName = "myprefrences";
    private static SharedPreferences myPrefs;
    private static SharedPreferences.Editor myEditor;
    private static Gson myGson = new Gson();


    public static void initialize(Activity myActivity) {
        myPrefs = myActivity.getApplicationContext().getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        myEditor = myPrefs.edit();
    }

    public static SharedPreferences getMyPrefrences() {
        return myPrefs;
    }

    public static void storeData(Object value, String key) {
        if (value instanceof Integer) {
            myEditor.putInt(key, (int) value);
        } else if (value instanceof String) {
            myEditor.putString(key, value + "");
        } else if (value instanceof Boolean) {
            myEditor.putBoolean(key, (boolean)value);
        }
        else {
            String json = myGson.toJson(value);
            myEditor.putString(key, json);
        }
        myEditor.commit();
    }

    public static void removeValue(String key) {
        myEditor.remove(key);
        myEditor.commit();
    }

    public static Object retrieveFromGson(String key) {
        String json = myPrefs.getString(key, "default_value");
        return myGson.fromJson(json, Object.class);
    }


    public static void clearData() {
        myEditor.clear();
        myEditor.commit();
    }
}
