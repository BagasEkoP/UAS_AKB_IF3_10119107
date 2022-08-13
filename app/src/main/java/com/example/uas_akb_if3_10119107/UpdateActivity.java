package com.example.uas_akb_if3_10119107;

//NIM   : 10119107
//Nama  : Bagas Eko Pambudi
//Kelas : IF-3

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText judul_input, kategori_input, isi_input;
    Button btnUpdate, btnDelete;

    String id, judul, kategori, isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        judul_input = findViewById(R.id.judul_input2);
        kategori_input = findViewById(R.id.kategori_input2);
        isi_input = findViewById(R.id.isi_input2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        getAndSetIntentData();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Databasehelper myDB = new Databasehelper(UpdateActivity.this);
                judul = judul_input.getText().toString().trim();
                kategori = kategori_input.getText().toString().trim();
                isi = isi_input.getText().toString().trim();
                myDB.updateData(id, judul, kategori, isi);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("judul") &&
                getIntent().hasExtra("kategori") && getIntent().hasExtra("isi")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            judul = getIntent().getStringExtra("judul");
            kategori = getIntent().getStringExtra("kategori");
            isi = getIntent().getStringExtra("isi");

            //Setting Intent Data
            judul_input.setText(judul);
            kategori_input.setText(kategori);
            isi_input.setText(isi);
            Log.d("", judul+" "+kategori+" "+isi);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + judul + " ?");
        builder.setMessage("Ingin Hapus " + judul + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Databasehelper myDB = new Databasehelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}