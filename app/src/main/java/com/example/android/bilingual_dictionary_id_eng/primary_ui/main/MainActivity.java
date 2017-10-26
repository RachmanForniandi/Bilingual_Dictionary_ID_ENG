package com.example.android.bilingual_dictionary_id_eng.primary_ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.bilingual_dictionary_id_eng.R;
import com.example.android.bilingual_dictionary_id_eng.TheApp;
import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.ActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.DaggerActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.ActivityModule;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.DashboardActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    private final String TAG = getClass().getSimpleName();
    @Inject
    MainPresenter mainPresenter;
    @Inject
    DataManager dataManager;

    private ActivityComponent activityComponent;

    @BindView(R.id.progress_bar_loading_activity_main)
    ProgressBar progressBarLoadingActivityMain;

    @BindView(R.id.tv_preparing_data_activity_main)
    TextView txtViewPreparingDataActivityMain;

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
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        onAttach();
        mainPresenter.doLoadData(dataManager, this);

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

    @Override
    public void loadDataFailed(){
        txtViewPreparingDataActivityMain.setVisibility(View.GONE);
        progressBarLoadingActivityMain.setVisibility(View.GONE);
        AlertDialog.Builder builderAlertDialogError = new AlertDialog.Builder(this);
        AlertDialog alertDialogError = builderAlertDialogError.create();
        alertDialogError.setCancelable(false);
        alertDialogError.setMessage("Error when data on process. Please try again");
        alertDialogError.setButton(
                DialogInterface.BUTTON_POSITIVE, "Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        System.exit(1);
                    }
                }
        );
        alertDialogError.show();
    }

    @Override
    public void loadData(){
        txtViewPreparingDataActivityMain.setVisibility(View.GONE);
        progressBarLoadingActivityMain.setVisibility(View.GONE);
        Intent intentDashboardActivity = new Intent(this, DashboardActivity.class);
        startActivity(intentDashboardActivity);
    }
}
