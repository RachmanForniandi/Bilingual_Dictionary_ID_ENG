package com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.eng_id;

import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.core.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Lenovo on 10/27/2017.
 */

public class EnglishIndonesiaPresenter implements MvpPresenter<EnglishIndonesiaView>{
    private final String TAG = getClass().getSimpleName();
    private EnglishIndonesiaView englishIndonesiaView;

    @Inject
    public EnglishIndonesiaPresenter(){

    }

    @Override
    public void onAttachView(EnglishIndonesiaView mvpView) {
        englishIndonesiaView = mvpView;
    }

    @Override
    public void onDetachView() {
        englishIndonesiaView = null;
    }

    List<KataKamus> onSearchingKeyword(DataManager dataManager, String keyword){
        List<KataKamus> listDataKeyword = new ArrayList<>();
        if (keyword.isEmpty() || keyword.equals("")){
            return listDataKeyword;
        }
        listDataKeyword = dataManager.searchKeyWordEnglishToIndonesia(keyword);
        return listDataKeyword;
    }
}
