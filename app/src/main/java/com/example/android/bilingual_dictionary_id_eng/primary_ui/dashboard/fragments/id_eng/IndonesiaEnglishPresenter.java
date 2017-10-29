package com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.id_eng;

import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.core.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Lenovo on 10/27/2017.
 */

class IndonesiaEnglishPresenter implements MvpPresenter<IndonesiaEnglishView>{

    private final String TAG = getClass().getSimpleName();
    private IndonesiaEnglishView indonesiaEnglishView;

    @Inject
    public IndonesiaEnglishPresenter(){

    }


    @Override
    public void onAttachView(IndonesiaEnglishView mvpView) {
        indonesiaEnglishView = mvpView;
    }

    @Override
    public void onDetachView() {
        indonesiaEnglishView = null;
    }

    List<KataKamus>onSearchingKeyword(DataManager dataManager, String keyword){
        List<KataKamus> listDataKeyword = new ArrayList<>();
        if (keyword.isEmpty() || keyword.equals("")){
            return listDataKeyword;
        }
        listDataKeyword = dataManager.searchKeyWordIndonesiaToEnglish(keyword);
        return listDataKeyword;
    }
}
