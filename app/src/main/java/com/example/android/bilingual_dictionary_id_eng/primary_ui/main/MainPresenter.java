package com.example.android.bilingual_dictionary_id_eng.primary_ui.main;

import com.example.android.bilingual_dictionary_id_eng.primary_ui.core.MvpPresenter;

import javax.inject.Inject;

/**
 * Created by Lenovo on 10/25/2017.
 */

public class MainPresenter implements MvpPresenter<MainView> {

    private final String TAG = getClass().getSimpleName();
    private MainView mainView;

    @Inject
    public MainPresenter(){

    }

    @Override
    public void onAttachView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onDetachView() {
        mainView = null;
    }
}
