package com.example.android.bilingual_dictionary_id_eng.primary_ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.bilingual_dictionary_id_eng.R;
import com.example.android.bilingual_dictionary_id_eng.TheApp;
import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.ActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.DaggerActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.ActivityModule;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailKamus extends AppCompatActivity implements DetailView{

    private final String TAG = getClass().getSimpleName();

    @Inject
    DataManager dataManager;

    @Inject
    DetailPresenter detailPresenter;

    private ActivityComponent activityComponent;

    @BindView(R.id.tv_from_activity_detail_kamus)
    TextView txtViewFromActivityDetailKamus;
    @BindView(R.id.tv_to_activity_detail_kamus)
    TextView txtViewToActivityDetailKamus;

    private ActivityComponent getActivityComponent(){
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
        setContentView(R.layout.activity_detail_kamus);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        onAttachMvpView();
        doLoadData();
    }

    @Override
    public void onAttachMvpView() {
        detailPresenter.onAttachView(this);
    }

    @Override
    public void onDetachMvpView() {
        detailPresenter.onDetachView();
    }

    @Override
    protected void onResume() {
        onAttachMvpView();
        super.onResume();
    }

    @Override
    protected void onPause(){
        onDetachMvpView();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        onDetachMvpView();
        super.onDestroy();
    }


    private void doLoadData() {
        Bundle bundle = getIntent().getExtras();
        KataKamus kataKamusdetail = bundle.getParcelable("kataKamus");
        txtViewFromActivityDetailKamus.setText(kataKamusdetail.getFromWord());
        txtViewToActivityDetailKamus.setText(kataKamusdetail.getToWord());
    }
}
