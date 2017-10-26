package com.example.android.bilingual_dictionary_id_eng.primary_ui.main;

import android.content.Context;
import android.util.Log;

import com.example.android.bilingual_dictionary_id_eng.data.manager.DataManager;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;
import com.example.android.bilingual_dictionary_id_eng.primary_ui.core.MvpPresenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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

    void doLoadData(final DataManager dataManager, Context context){
        String firstInstallConfig = dataManager.getFirstInstallConfig();
        Log.d(TAG, "firstInstallConfig: " + firstInstallConfig);
        if (firstInstallConfig != null) {
            if (firstInstallConfig.equalsIgnoreCase("done")) {
                mainView.loadData();
            } else if (firstInstallConfig.equalsIgnoreCase("failed")) {
                List<KataKamus> listDataKataKamusEnglishIndonesia = new ArrayList<>();
                List<KataKamus> listDataKataKamusIndonesiaEnglish = new ArrayList<>();
                try {
                    dataManager.deleteDataKamusEnglishToIndonesia();
                    dataManager.deleteDataKamusIndonesiaToEnglish();

                    // membaca file english_indonesia.txt dan memasukan ke table english_indonesia
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    context.getAssets()
                                            .open("english_indonesia.txt"),
                                    "utf-8"
                            )
                    );
                    String strLine;
                    while ((strLine = bufferedReader.readLine()) != null) {
                        strLine = bufferedReader.readLine();
                        if (strLine == null) {
                            continue;
                        }
                        String[] strArrayLine = strLine.split("\\t");
                        String fromWord = strArrayLine[0].trim();
                        String toWord = strArrayLine[1].trim();
                        listDataKataKamusEnglishIndonesia.add(
                                new KataKamus(
                                        fromWord,
                                        toWord
                                )
                        );
                    }

                    //membaca file indonesia_english.txt dan memasukan ke table indonesia_english
                    while ((strLine = bufferedReader.readLine()) != null) {
                        strLine = bufferedReader.readLine();
                        String[] strArrayLine = strLine.split("\\t");
                        String fromWord = strArrayLine[0].trim();
                        String toWord = strArrayLine[1].trim();
                        listDataKataKamusIndonesiaEnglish.add(
                                new KataKamus(
                                        fromWord,
                                        toWord
                                )
                        );
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    mainView.loadDataFailed();
                    return;
                }

                Observable<List<KataKamus>> observableDataKataKamusEnglishIndonesia = Observable
                        .just(listDataKataKamusEnglishIndonesia);
                Observable<List<KataKamus>> observableDataKataKamusIndonesiaEnglish = Observable
                        .just(listDataKataKamusIndonesiaEnglish);
                Observable
                        .zip(
                                observableDataKataKamusEnglishIndonesia,
                                observableDataKataKamusIndonesiaEnglish,
                                new BiFunction<List<KataKamus>, List<KataKamus>, Boolean>() {
                                    @Override
                                    public Boolean apply(
                                            @NonNull List<KataKamus> listKataKamusEnglishIndonesia,
                                            @NonNull List<KataKamus> listKataKamusIndonesiaEnglish
                                    ) throws Exception {
                                        boolean isExecutedSuccess = true;
                                        long insertExecuted;

                                        for (KataKamus kataKamusEnglishIndonesia : listKataKamusEnglishIndonesia) {
                                            insertExecuted = dataManager
                                                    .insertDataKamusEnglishToIndonesia(
                                                            kataKamusEnglishIndonesia
                                                    );
                                            isExecutedSuccess = insertExecuted != -1;
                                            if (!isExecutedSuccess) {
                                                return isExecutedSuccess;
                                            }
                                        }

                                        for (KataKamus kataKamusIndonesiaEnglish : listKataKamusIndonesiaEnglish) {
                                            insertExecuted = dataManager
                                                    .insertDataKamusIndonesiaToEnglish(
                                                            kataKamusIndonesiaEnglish
                                                    );
                                            isExecutedSuccess = insertExecuted != -1;
                                            if (!isExecutedSuccess) {
                                                return isExecutedSuccess;
                                            }
                                        }

                                        return isExecutedSuccess;
                                    }
                                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            dataManager.saveFirstInstallConfig("done");
                                            mainView.loadData();
                                        } else {
                                            dataManager.saveFirstInstallConfig("failed");
                                            mainView.loadDataFailed();
                                        }
                                    }
                                },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        throwable.printStackTrace();
                                        mainView.loadDataFailed();
                                    }
                                }
                        );
                }
            } else {
                BufferedReader bufferedReader;
                List<KataKamus> listDataKataKamusEnglishIndonesia = new ArrayList<>();
                List<KataKamus> listDataKataKamusIndonesiaEnglish = new ArrayList<>();
                try {
                    // membaca file english_indonesia.txt dan memasukan ke table english_indonesia
                    bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    context.getAssets()
                                            .open("english_indonesia.txt"),
                                    "utf-8"
                            )
                    );
                    String strLine;
                    while ((strLine = bufferedReader.readLine()) != null) {
                        strLine = bufferedReader.readLine();
                        if (strLine == null) {
                            continue;
                        }
                        String[] strArrayLine = strLine.split("\\t");
                        String fromWord = strArrayLine[0].trim();
                        String toWord = strArrayLine[1].trim();
                        listDataKataKamusEnglishIndonesia.add(
                                new KataKamus(
                                        fromWord,
                                        toWord
                                )
                        );
                    }

                    bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    context.getAssets()
                                            .open("indonesia_english.txt"),
                                    "utf-8"
                            )
                    );
                    while ((strLine = bufferedReader.readLine()) != null) {
                        strLine = bufferedReader.readLine();
                        String[] strArrayLine = strLine.split("\\t");
                        String fromWord = strArrayLine[0].trim();
                        String toWord = strArrayLine[1].trim();
                        listDataKataKamusIndonesiaEnglish.add(
                                new KataKamus(
                                        fromWord,
                                        toWord
                                )
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mainView.loadDataFailed();
                    return;
                }
                Log.d(TAG, "listDataKataKamusEnglishIndonesia.size: " + listDataKataKamusEnglishIndonesia.size());
                Log.d(TAG, "listDataKataKamusIndonesiaEnglish.size: " + listDataKataKamusIndonesiaEnglish.size());

                Observable<List<KataKamus>> observableDataKataKamusEnglishIndonesia = Observable
                        .just(listDataKataKamusEnglishIndonesia);
                Observable<List<KataKamus>> observableDataKataKamusIndonesiaEnglish = Observable
                        .just(listDataKataKamusIndonesiaEnglish);
                Observable
                        .zip(
                                observableDataKataKamusEnglishIndonesia,
                                observableDataKataKamusIndonesiaEnglish,
                                new BiFunction<List<KataKamus>, List<KataKamus>, Boolean>() {
                                    @Override
                                    public Boolean apply(
                                            @NonNull List<KataKamus> listKataKamusEnglishIndonesia,
                                            @NonNull List<KataKamus> listKataKamusIndonesiaEnglish
                                    ) throws Exception {
                                        boolean isExecutedSuccess = true;
                                        long insertExecuted;
                                        for (KataKamus kataKamusEnglishIndonesia : listKataKamusEnglishIndonesia) {
                                            insertExecuted = dataManager
                                                    .insertDataKamusEnglishToIndonesia(
                                                            kataKamusEnglishIndonesia
                                                    );
                                            isExecutedSuccess = insertExecuted != -1;
                                            if (!isExecutedSuccess) {
                                                return isExecutedSuccess;
                                            }
                                            Log.d(TAG, "KataKamuEnglishIndonesia: " + kataKamusEnglishIndonesia);
                                        }

                                        for (KataKamus kataKamusIndonesiaEnglish : listKataKamusIndonesiaEnglish) {
                                            insertExecuted = dataManager
                                                    .insertDataKamusIndonesiaToEnglish(
                                                            kataKamusIndonesiaEnglish
                                                    );
                                            isExecutedSuccess = insertExecuted != -1;
                                            if (!isExecutedSuccess) {
                                                return isExecutedSuccess;
                                            }
                                            Log.d(TAG, "KataKamuIndonesiaEnglish: " + kataKamusIndonesiaEnglish);
                                        }

                                        return isExecutedSuccess;
                                    }
                                }
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            dataManager.saveFirstInstallConfig("done");
                                            mainView.loadData();
                                        } else {
                                            dataManager.saveFirstInstallConfig("failed");
                                            mainView.loadDataFailed();
                                        }
                                    }
                                },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        throwable.printStackTrace();
                                        mainView.loadDataFailed();
                                    }
                                }
                        );
            }
        }

        void onGetItemDataKamus(DataManager dataManager)throws Exception{
        int itemCountKamusEnglishToIndonesia = dataManager.getSizeItemDataKamusEnglishToIndonesia();
        int itemCountKamusIndonesiaToEnglish = dataManager.getSizeItemDataKamusIndonesiaToEnglish();
        Log.d(TAG, "itemCountKamusEnglishToIndonesia: " + itemCountKamusEnglishToIndonesia);
        Log.d(TAG, "itemCountKamusIndonesiaToEnglish: " + itemCountKamusIndonesiaToEnglish);
    }

}
