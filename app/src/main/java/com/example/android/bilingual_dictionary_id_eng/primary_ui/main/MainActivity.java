package com.example.android.bilingual_dictionary_id_eng.primary_ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bilingual_dictionary_id_eng.R;
import com.example.android.bilingual_dictionary_id_eng.TheApp;
import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.ActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.ActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    private final String TAG = getClass().getSimpleName();
    @Inject
    MainPresenter mainPresenter;
    @Inject
    DataManager dataManager;

    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(TheApp.get(this).getAppComponent())
                    .build();
        }
        return activityComponent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        onAttach();

    }

    @Override
    protected void onResume(){
        onAttach();
        super.onResume();
    }
    @Override
    protected void onPause(){
        onDetach();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        onDetach();
        super.onDestroy();
    }

    @Override
    public void onAttach() {
        mainPresenter.onAttachView(this);
    }

    @Override
    public void onDetach() {
        mainPresenter.onDetachView();
    }
}
