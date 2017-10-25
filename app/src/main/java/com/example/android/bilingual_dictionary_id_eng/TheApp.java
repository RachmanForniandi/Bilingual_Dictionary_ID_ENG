package com.example.android.bilingual_dictionary_id_eng;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

/**
 * Created by Lenovo on 10/24/2017.
 */

public class TheApp extends Application {

    protected AppComponent component;

    public static TheApp get(Context context){
        return (TheApp) context.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    @VisibleForTesting
    protected AppComponent createComponent(){
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return component;
    }

}
