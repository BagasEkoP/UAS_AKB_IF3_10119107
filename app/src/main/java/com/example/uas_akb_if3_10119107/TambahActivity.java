package com.example.uas_akb_if3_10119107;

//NIM   : 10119107
//Nama  : Bagas Eko Pambudi
//Kelas : IF-3

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TambahActivity extends AppCompatActivity {

    EditText judul_input, kategori_input, isi_input;
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        judul_input = findViewById(R.id.judul_input);
        kategori_input = findViewById(R.id.kategori_input);
        isi_input = findViewById(R.id.isi_input);
        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Databasehelper myDB = new Databasehelper(TambahActivity.this);
                myDB.addBook(judul_input.getText().toString().trim(),
                        kategori_input.getText().toString().trim(),
                        isi_input.getText().toString().trim());
            }
        });
    }
}