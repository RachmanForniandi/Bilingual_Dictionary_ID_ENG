package com.example.android.bilingual_dictionary_id_eng.primary_ui.detail;

import com.example.android.bilingual_dictionary_id_eng.primary_ui.core.MvpPresenter;

import javax.inject.Inject;

/**
 * Created by Lenovo on 10/27/2017.
 */

class DetailPresenter implements MvpPresenter<DetailView> {

    private final String TAG = getClass().getSimpleName();
    private DetailView detailView;

    @Inject
    public DetailPresenter(){

    }

    
    @Override
    public void onAttachView(DetailView mvpView) {
        detailView = mvpView;
    }

    @Override
    public void onDetachView() {
        detailView = null;
    }
}
