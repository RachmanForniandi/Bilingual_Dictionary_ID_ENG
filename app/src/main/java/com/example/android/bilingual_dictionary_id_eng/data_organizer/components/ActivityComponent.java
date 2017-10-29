package com.example.android.bilingual_dictionary_id_eng.data_organizer.components;

import com.example.android.bilingual_dictionary_id_eng.data_organizer.PerActivity;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.ActivityModule;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.DashboardActivity;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.detail.DetailKamus;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Lenovo on 10/24/2017.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DashboardActivity dashboardActivity);

    void inject(DetailKamus detailKamus);

}
