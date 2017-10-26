package com.example.android.bilingual_dictionary_id_eng.data_organizer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;


/**
 * Created by Lenovo on 10/24/2017.
 */
@Qualifier
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
