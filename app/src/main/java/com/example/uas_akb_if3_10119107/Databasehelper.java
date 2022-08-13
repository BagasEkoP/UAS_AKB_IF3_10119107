package com.example.uas_akb_if3_10119107;

//NIM   : 10119107
//Nama  : Bagas Eko Pambudi
//Kelas : IF-3

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "catatan.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_catatan";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_JUDUL = "judul";
    private static final String COLUMN_KATEGORI = "kategori";
    private static final String COLUMN_ISI = "isi";

    Databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JUDUL + " TEXT, " +
                COLUMN_KATEGORI + " TEXT, " +
                COLUMN_ISI + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String judul, String isi, String kategori){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_KATEGORI, kategori);
        cv.put(COLUMN_ISI, isi);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Tambah Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil Ditambah!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String id, String judul, String kategori, String isi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_KATEGORI, kategori);
        cv.put(COLUMN_ISI, isi);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID+"=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Update Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Telah Berhasil!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID+"=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Gagal Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Berhasil Delete.", Toast.LENGTH_SHORT).show();
        }
    }

}
