package com.example.android.bilingual_dictionary_id_eng;

import android.app.Application;
import android.content.Context;

import com.example.android.bilingual_dictionary_id_eng.data.db.DBHelper;
import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.data.sharedpreference.SharedPreferenceSettings;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 10/24/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(TheApp app);

@ApplicationContext
    Context getContext();

    Application getApplication();

    DBHelper getDatabaseHelper();

    SharedPreferenceSettings getSharedPreferenceSettings();

    DataManager getDataManager();
}
