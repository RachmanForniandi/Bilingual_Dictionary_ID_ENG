package com.example.android.bilingual_dictionary_id_eng.data_organizer.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 10/24/2017.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    /*@Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }*/

    @Provides
    Activity provideActivity(){
        return activity;
    }
}
