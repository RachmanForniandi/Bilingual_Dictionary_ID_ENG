package com.example.android.bilingual_dictionary_id_eng.data_organizer.components;

import com.example.android.bilingual_dictionary_id_eng.AppComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.PerActivity;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.ActivityModule;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Lenovo on 10/24/2017.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
