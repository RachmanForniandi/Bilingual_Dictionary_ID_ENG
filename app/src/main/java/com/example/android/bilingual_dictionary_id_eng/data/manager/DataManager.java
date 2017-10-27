package com.example.android.bilingual_dictionary_id_eng.data.manager;

import android.content.Context;

import com.example.android.bilingual_dictionary_id_eng.data.db.DBHelper;
import com.example.android.bilingual_dictionary_id_eng.data.sharedpreference.SharedPreferenceSettings;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.ApplicationContext;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Lenovo on 10/25/2017.
 */
@Singleton
public class DataManager {

    private Context context;
    private DBHelper databaseHelper;
    private SharedPreferenceSettings sharedPreferenceSettings;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DBHelper databaseHelper,
                       SharedPreferenceSettings sharedPreferenceSettings){
        this.context = context;
        this.databaseHelper = databaseHelper;
        this.sharedPreferenceSettings = sharedPreferenceSettings;
    }

    public void saveFirstInstallConfig(String firstInstallStatus){
        sharedPreferenceSettings.put(
                SharedPreferenceSettings.PREF_KEY_FIRST_INSTALL,
                firstInstallStatus
        );
    }

    public String getFirstInstallConfig(){
        return sharedPreferenceSettings.get(
                SharedPreferenceSettings.PREF_KEY_FIRST_INSTALL,
                null
        );
    }

    public Long insertDataKamusIndonesiaToEnglish(KataKamus kataKamus)throws Exception{
        return databaseHelper.insertDataKamusIndonesiaToEnglish(kataKamus);
    }

    public Long insertDataKamusEnglishToIndonesia(KataKamus kataKamus)throws Exception{
        return databaseHelper.insertDataKamusEnglishToIndonesia(kataKamus);
    }

    public KataKamus getDataKamusIndonesiaToEnglish(String keyword)throws Exception{
        return null;
    }

    public KataKamus getDataKamusEnglishToIndonesia(String keyword)throws Exception{
        return null;
    }

    public int deleteDataKamusIndonesiaToEnglish()throws Exception{
        return databaseHelper.deleteDataKamusIndonesiaToEnglish();
    }

    public int deleteDataKamusEnglishToIndonesia()throws Exception{
        return databaseHelper.deleteDataKamusEnglishToIndonesia();
    }

    public int getSizeItemDataKamusEnglishToIndonesia(){
        return databaseHelper.itemCountDataKamusEnglishToIndonesia();
    }

    public int getSizeItemDataKamusIndonesiaToEnglish(){
        return databaseHelper.itemCountDataKamusIndonesiaToEnglish();
    }
}
