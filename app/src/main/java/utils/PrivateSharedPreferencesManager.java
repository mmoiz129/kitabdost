package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class PrivateSharedPreferencesManager {

    public static final String SHARED_PREFERENCES_KEY = "com.invend.kitabdost";
    public static final String LATITUDE_KEY = "isTrusty";



    private static PrivateSharedPreferencesManager instance;

    private SharedPreferences privateSharedPreferences;

    private PrivateSharedPreferencesManager(Context context) {

        this.privateSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    public static PrivateSharedPreferencesManager getInstance(Context context) {

        synchronized (PrivateSharedPreferencesManager.class) {
            if (instance == null) {
                instance = new PrivateSharedPreferencesManager(context);
            }
            return instance;
        }
    }

    private void storeStringInSharedPreferences(String key, String content) {

        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putString(key, content);
        editor.apply();
    }


    private void storeLongInSharedPreferences(String key, long content) {

        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putLong(key, content);
        editor.apply();
    }

    private void storeIntInSharedPreferences(String key, int content) {

        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putInt(key, content);
        editor.apply();
    }

    private void storeFloatInSharedPreferences(String key, float content) {

        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putFloat(key, content);
        editor.apply();
    }

    private void storeBooleanInSharedPreferences(String key, boolean status) {

        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putBoolean(key, status);
        editor.apply();
    }

    public String getStringFromSharedPreferences(String key) {

        return privateSharedPreferences.getString(key, "");
    }

    private long getLongFromSharedPreferences(String key) {

        return privateSharedPreferences.getLong(key, 0);
    }

    private int getIntFromSharedPreferences(String key) {

        return privateSharedPreferences.getInt(key, 0);
    }

    private float getFloatFromSharedPreferences(String key) {

        return privateSharedPreferences.getFloat(key, 0);
    }

    private boolean getBooleanFromSharedPreferences(String key) {

        return privateSharedPreferences.getBoolean(key, false);
    }




}
