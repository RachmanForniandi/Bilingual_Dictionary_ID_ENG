package com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.eng_id;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.bilingual_dictionary_id_eng.R;
import com.example.android.bilingual_dictionary_id_eng.TheApp;
import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.DaggerFragmentComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.components.FragmentComponent;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.modules.FragmentModule;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.dashboard.fragments.adapter.AdapterSearchingKeyword;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.detail.DetailKamus;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishIndonesiaFragment extends Fragment implements EnglishIndonesiaView{

    private final String TAG = getClass().getSimpleName();

    @Inject
    EnglishIndonesiaPresenter englishIndonesiaPresenter;

    @Inject
    DataManager dataManager;

    private FragmentComponent fragmentComponent;
    private List<KataKamus> listDataKataKamusEnglishIndonesia;
    private AdapterSearchingKeyword adapterSearchingKeyword;

    @BindView(R.id.et_keyword_english_fragment_english_indonesia)
    EditText edtTextKeywordEnglishFragmentEnglishIndonesia;

    @BindView(R.id.rc_view_result_data_keyword_fragment_english_indonesia)
    RecyclerView rcViewResultDataKeywordFragmentEnglishIndonesia;


    public FragmentComponent getFragmentComponent(){
        if (fragmentComponent == null){
            fragmentComponent = DaggerFragmentComponent
                    .builder()
                    .fragmentModule(new FragmentModule(this))
                    .appComponent(TheApp.get(getContext()).getAppComponent())
                    .build();
        }
        return fragmentComponent;
    }


    public EnglishIndonesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english_indonesia, container, false);
        ButterKnife.bind(this, view);
        getFragmentComponent().inject(this);
        onAttachMvpView();
        initData();
        initListeners();
        return view;
    }

    @Override
    public void onResume(){
        onAttachMvpView();
        super.onResume();
    }

    @Override
    public void onPause(){
        onDetachMvpView();
        super.onPause();
    }

    @Override
    public void onDestroy(){
        onDetachMvpView();
        super.onDestroy();
    }

    @Override
    public void onAttachMvpView() {
        englishIndonesiaPresenter.onAttachView(this);
    }

    @Override
    public void onDetachMvpView() {
        englishIndonesiaPresenter.onDetachView();
    }

    private void initData() {
        listDataKataKamusEnglishIndonesia = new ArrayList<>();
        adapterSearchingKeyword =new AdapterSearchingKeyword(
                listDataKataKamusEnglishIndonesia,
                new AdapterSearchingKeyword.ListenerAdapterSearchingKeyword() {
            @Override
            public void onClickItemKeyword(KataKamus kataKamusSelected) {
                Intent intentDetailKamus = new Intent(
                        getContext(),
                        DetailKamus.class
                    );
                    intentDetailKamus.putExtra("KataKamus", kataKamusSelected);
                    startActivity(intentDetailKamus);
                }
            }
        );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcViewResultDataKeywordFragmentEnglishIndonesia.setLayoutManager(linearLayoutManager);
        rcViewResultDataKeywordFragmentEnglishIndonesia.setAdapter(adapterSearchingKeyword);
    }

    private void initListeners() {
        RxTextView.afterTextChangeEvents(edtTextKeywordEnglishFragmentEnglishIndonesia)
                .map(new Function<TextViewAfterTextChangeEvent, String>() {
                    @Override
                    public String apply(@NonNull TextViewAfterTextChangeEvent txtViewAfterChangeEvent)throws Exception{
                        return txtViewAfterChangeEvent.editable().toString();
                    }
                })
                .map(new Function<String, List<KataKamus>>() {
                    @Override
                    public List<KataKamus>apply(@NonNull String keyword)throws Exception{
                        return englishIndonesiaPresenter.onSearchingKeyword(
                                dataManager,
                                keyword
                        );
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<KataKamus>>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d){

                    }

                    @Override
                    public void onNext(@NonNull List<KataKamus> listDataKataKamusSearching){
                        Log.d(TAG, "listDataKataKamusSearching: " + Arrays.toString(listDataKataKamusSearching.toArray()));
                        listDataKataKamusEnglishIndonesia.clear();
                        listDataKataKamusEnglishIndonesia.addAll(listDataKataKamusSearching);
                        adapterSearchingKeyword.refreshData(listDataKataKamusSearching);
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete(){

                    }
                });
        }
    }
