package com.example.android.bilingual_dictionary_id_eng.data_organizer.modules;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 10/28/2017.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    /*@Provides
    @ActivityContext
    Context provideContext() {
        return fragment.getContext();
   }*/

    @Provides
    Fragment provideFragment(){
        return fragment;
    }
}
