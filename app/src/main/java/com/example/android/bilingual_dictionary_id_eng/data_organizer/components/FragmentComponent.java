package com.example.android.bilingual_dictionary_id_eng.data_organizer.components;

import com.example.android.bilingual_dictionary_id_eng.data_organizer.PerFragment;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.FragmentModule;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.eng_id.EnglishIndonesiaFragment;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.id_eng.IndonesiaEnglishFragment;

import dagger.Component;

/**
 * Created by Lenovo on 10/28/2017.
 */

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(EnglishIndonesiaFragment englishIndonesiaFragment);

    void inject(IndonesiaEnglishFragment indonesiaEnglishFragment);
}
