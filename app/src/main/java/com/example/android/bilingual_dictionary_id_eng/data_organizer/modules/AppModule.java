package com.example.android.bilingual_dictionary_id_eng.data_organizer.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.bilingual_dictionary_id_eng.data_organizer.ApplicationContext;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.DBInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 10/24/2017.
 */

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    @DBInfo
    String provideDatabaseName(){
        return "myKamus.db";
    }

    @Provides
    @DBInfo
    Integer provideDatabaseVersion(){
        return 1;
    }

    @Provides
    SharedPreferences provideSharedPreferenceSettings(){
        return application.getSharedPreferences("settings-prefs", Context.MODE_PRIVATE);
    }

}


