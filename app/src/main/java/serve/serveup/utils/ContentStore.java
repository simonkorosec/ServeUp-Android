package serve.serveup.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import serve.serveup.dataholder.MealInfo;
import serve.serveup.dataholder.RestaurantInfo;
import serve.serveup.dataholder.session.Session;
import serve.serveup.dataholder.session.SessionContent;
import serve.serveup.dataholder.session.SessionManager;

/*
    @author: urban.jagodic
*/
public class ContentStore implements SessionManager {

    private String prefsName = "myprefrences";
    private String sessionKey = "session_key";
    private String sessionDefault = "default_session_value";
    private SharedPreferences myPrefs;
    private SharedPreferences.Editor myEditor;
    private Gson myGson = new Gson();


    public ContentStore(Context context) {
        this.myPrefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        this.myEditor = myPrefs.edit();
    }

    private void storeData(Object value, String key) {
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

    private void removeValue(String key) {
        myEditor.remove(key);
        myEditor.commit();
    }

    private void clearData() {
        myEditor.clear();
        myEditor.commit();
    }

    // Session methods

    @Override
    public void addToSession(SessionContent type, Object data) {
        Session currentSesh = new Session();
        if(getSession() != null)
            currentSesh = getSession();

        switch (type) {
            case CURRENT_USER:
                currentSesh.setCurrentUser((String) data);
                break;
            case MEALS:
                currentSesh.addCurrentMeal((MealInfo) data);
                break;
            case RESTUANRANT:
                currentSesh.setCurrentRestaurant((RestaurantInfo) data);
            break;
        }
        storeData(currentSesh, sessionKey);
    }

    @Override
    public void deleteFromSession(SessionContent type, Object data) {
        Session currentSesh = new Session();
        if(getSession() != null)
            currentSesh = getSession();

        switch (type) {
            case CURRENT_USER:
                currentSesh.clearCurrentUser();
                break;
            case MEALS:
                currentSesh.deleteMeal((MealInfo) data);
                break;
            case RESTUANRANT:
                currentSesh.clearCurrentRestaurant();
                break;
        }
        storeData(currentSesh, sessionKey);
    }

    @Override
    public Session getSession() {
        String json = myPrefs.getString(sessionKey, sessionDefault);
        if(!json.equals(sessionDefault))
            return myGson.fromJson(json, Session.class);
        else
            return null;
    }

    @Override
    public void eraseSession() {
        removeValue(sessionKey);
    }


}
