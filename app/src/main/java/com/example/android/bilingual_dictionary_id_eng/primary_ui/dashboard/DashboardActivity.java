package com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.bilingual_dictionary_id_eng.R;
import com.example.android.bilingual_dictionary_id_eng.TheApp;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.ActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.DaggerActivityComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.ActivityModule;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.eng_id.EnglishIndonesiaFragment;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.id_eng.IndonesiaEnglishFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardView, NavigationView.OnNavigationItemSelectedListener{

    private final String TAG = getClass().getSimpleName();
    @Inject
    DashboardPresenter dashboardPresenter;
    private ActivityComponent activityComponent;

    @BindView(R.id.toolbar_dashboard_app_bar)
    Toolbar toolbarAppBarDashboard;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;


    public ActivityComponent getActivityComponent(){
        if (activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(TheApp.get(this).getAppComponent())
                    .build();

        }
        return  activityComponent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dashboard);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        onAttachMvpView();
        initToolbar();
        initNavigationDrawer();
        initContentLayout();
        initListeners();
    }

    private void initContentLayout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container_content_dashboard, new EnglishIndonesiaFragment())
                .commit();
    }

    @Override
    public void onAttachMvpView() {
        dashboardPresenter.onAttachView(this);
    }

    @Override
    public void onDetachMvpView() {
        dashboardPresenter.onDetachView();
    }

    private void initToolbar(){
        setSupportActionBar(toolbarAppBarDashboard);
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbarAppBarDashboard,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initListeners() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_eng_id_menu_navi_dashboard:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container_content_dashboard, new EnglishIndonesiaFragment())
                        .commit();
                break;
            case R.id.menu_item_id_eng_menu_navi_dashboard:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container_content_dashboard, new IndonesiaEnglishFragment())
                        .commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
