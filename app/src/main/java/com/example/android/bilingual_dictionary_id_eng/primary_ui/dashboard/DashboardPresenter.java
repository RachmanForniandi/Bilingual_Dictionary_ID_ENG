package com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard;

import com.example.android.bilingual_dictionary_id_eng.primary_ui.core.MvpPresenter;

import javax.inject.Inject;

/**
 * Created by Lenovo on 10/26/2017.
 */

public class DashboardPresenter implements MvpPresenter<DashboardView> {

    private final String TAG = getClass().getSimpleName();
    private DashboardView dashboardView;

    @Inject
    public DashboardPresenter(){

    }

    @Override
    public void onAttachView(DashboardView mvpView) {
        dashboardView = mvpView;
    }

    @Override
    public void onDetachView() {
        dashboardView = null;
    }
}
