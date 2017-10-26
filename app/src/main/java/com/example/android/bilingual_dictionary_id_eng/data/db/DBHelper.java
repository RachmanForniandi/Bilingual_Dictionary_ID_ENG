package com.example.android.bilingual_dictionary_id_eng.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.bilingual_dictionary_id_eng.data_organizer.ApplicationContext;
import com.example.android.bilingual_dictionary_id_eng.data_organizer.DBInfo;
import com.example.android.bilingual_dictionary_id_eng.model.KataKamus;

import javax.inject.Inject;

/**
 * Created by Lenovo on 10/24/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private final String TAG = getClass().getSimpleName();

    //Indonesia to English
    public static final String KAMUS_INDONESIA_TABLE_NAME = "indonesia_english";
    public static final String KAMUS_INDONESIA_COLUMN_ID = "id";
    public static final String KAMUS_INDONESIA_COLUMN_FROM_WORD = "fromWord";
    public static final String KAMUS_INDONESIA_COLUMN_TO_WORD = "toWord";

    //English to Indonesia
    public static final String KAMUS_ENGLISH_TABLE_NAME = "english_indonesia";
    public static final String KAMUS_ENGLISH_COLUMN_ID = "id";
    public static final String KAMUS_ENGLISH_COLUMN_FROM_WORD = "fromWord";
    public static final String KAMUS_ENGLISH_COLUMN_TO_WORD = "toWord";

    @Inject
    public DBHelper(@ApplicationContext Context context, @DBInfo String databaseName,
                    @DBInfo Integer version){
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        tableDictionaryIndonesiaEnglishCreateStatements(sqLiteDatabase);
        tableDictionaryEnglishIndonesiaCreateStatements(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + KAMUS_INDONESIA_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + KAMUS_ENGLISH_TABLE_NAME);

    }


    private void tableDictionaryIndonesiaEnglishCreateStatements(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + KAMUS_INDONESIA_TABLE_NAME + ""
                    + "("
                    + KAMUS_INDONESIA_COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KAMUS_INDONESIA_COLUMN_FROM_WORD + " TEXT, "
                    + KAMUS_INDONESIA_COLUMN_TO_WORD + " TEXT"
                    + ")"
            );
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
    }


    private void tableDictionaryEnglishIndonesiaCreateStatements(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + KAMUS_ENGLISH_TABLE_NAME + ""
                            + "("
                            + KAMUS_ENGLISH_COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + KAMUS_ENGLISH_COLUMN_FROM_WORD + " TEXT, "
                            + KAMUS_ENGLISH_COLUMN_TO_WORD + " TEXT"
                            + ")"
            );
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
    }

     // utk insert data to table kamus Indonesia to English
    public Long insertDataKamusIndonesiaToEnglish(KataKamus katakamus)throws Exception{
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KAMUS_INDONESIA_COLUMN_FROM_WORD, katakamus.getFromWord());
            contentValues.put(KAMUS_INDONESIA_COLUMN_TO_WORD, katakamus.getToWord());
            return sqLiteDatabase.insert(
                    KAMUS_INDONESIA_TABLE_NAME,
                    null,
                    contentValues
            );
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    //utk insert data to table Kamus English To Indonesia
    public Long insertDataKamusEnglishToIndonesia(KataKamus katakamus)throws Exception{
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KAMUS_ENGLISH_COLUMN_FROM_WORD, katakamus.getFromWord());
            contentValues.put(KAMUS_ENGLISH_COLUMN_TO_WORD, katakamus.getToWord());
            return sqLiteDatabase.insert(
                    KAMUS_ENGLISH_TABLE_NAME,
                    null,
                    contentValues
            );
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    /*Delete all data in table ID-ENG*/
    public int deleteDataKamusIndonesiaToEnglish()throws Exception{
        try{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            return sqLiteDatabase.delete(
                    KAMUS_ENGLISH_TABLE_NAME,
                    null,
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /*
    *Delete all data in table ENG-ID
    */
    public int deleteDataKamusEnglishToIndonesia()throws Exception{
        try{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            return sqLiteDatabase.delete(
                    KAMUS_INDONESIA_TABLE_NAME,
                    null,
                    null
            );
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public int itemCountDataKamusEnglishToIndonesia()throws Resources.NotFoundException, NullPointerException{
        int itemCount;
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(
              "SELECT * FROM " + KAMUS_ENGLISH_TABLE_NAME,
                    null,
                    null
            );
            itemCount = cursor.getCount();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return itemCount;
    }

    public int itemCountDataKamusIndonesiaToEnglish()throws Resources.NotFoundException, NullPointerException{
        int itemCount;
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "SELECT * FROM " + KAMUS_INDONESIA_TABLE_NAME,
                    null,
                    null
            );
            itemCount = cursor.getCount();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return itemCount;
    }
}
