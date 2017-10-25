package com.example.android.bilingual_dictionary_id_eng.data.sharedpreference;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Lenovo on 10/25/2017.
 */

@Singleton
public class SharedPreferenceSettings {

    public static final String PREF_KEY_FIRST_INSTALL = "first_install";
    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferenceSettings(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public void put(String key, String value){
        sharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    public void put(String key, int value){
        sharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }

    public void put(String key, float value){
        sharedPreferences.edit()
                .putFloat(key, value)
                .apply();
    }

    public void put(String key, boolean value){
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    public String get(String key, String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue){
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key){
        sharedPreferences.edit()
                .remove(key)
                .apply();
    }

}
