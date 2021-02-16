package com.arvirotech.monev.marketing.compon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.MarMain;
import com.arvirotech.monev.model.AddMarketing;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  EditMar extends AppCompatActivity {

    private Spinner spinner;
    private String Pekerjaan, Satker, Alamat, Nilai, User, Progress, key;
    private EditText namaPekerjaan, alamatPekerjaan, satuanKerja, nilaiPagu, user;
    private AddMarketing mAddMarketing;
    private RadioButton rbNulPersen, rbLimapuluhPersen, rbSeratusPersen;
    String progress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit");
        actionBar.setDisplayHomeAsUpEnabled(true);

        namaPekerjaan = findViewById(R.id.editProyek);
        alamatPekerjaan = findViewById(R.id.editAlamat);
        satuanKerja = findViewById(R.id.editSatker);
        nilaiPagu = findViewById(R.id.editNilai);
        rbNulPersen = findViewById(R.id.edit0);
        rbLimapuluhPersen = findViewById(R.id.edit50);
        rbSeratusPersen = findViewById(R.id.edit100);
        mAddMarketing = new AddMarketing();

        Intent intent = getIntent();
        Pekerjaan = intent.getStringExtra("Pekerjaan");
        Satker = intent.getStringExtra("satuanKerja");
        Alamat = intent.getStringExtra("alamatProyek");
        Nilai = intent.getStringExtra("nilaiPagu");
        User = intent.getStringExtra("user");
        Progress = intent.getStringExtra("progress");
        key = intent.getStringExtra("key");

        namaPekerjaan.setText(Pekerjaan);
        alamatPekerjaan.setText(Alamat);
        satuanKerja.setText(Satker);
        nilaiPagu.setText(Nilai);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save :
                saveEdit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveEdit() {
        String enamaPekerjaan, ealamatPekerjaan, esatuanPekerja, enilaiPagu, eprogress;

        enamaPekerjaan = namaPekerjaan.getText().toString();
        ealamatPekerjaan = alamatPekerjaan.getText().toString();
        esatuanPekerja = satuanKerja.getText().toString();
        enilaiPagu = nilaiPagu.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference().child("Marketing").child("Perencana").child(key);

        if (rbNulPersen.isChecked()) {
            progress = "0%";
        } if (rbLimapuluhPersen.isChecked()){
            progress = "50%";
        } if (rbSeratusPersen.isChecked()){
            progress = "100%";
        }

        mDatabase.child("namaProyek")
                .setValue(enamaPekerjaan);
        mDatabase.child("nilaiPagu")
                .setValue(enilaiPagu);
        mDatabase.child("satuanKerja")
                .setValue(esatuanPekerja);
        mDatabase.child("satuanKerja")
                .setValue(esatuanPekerja);
        mDatabase.child("progress")
                .setValue(progress);
        mDatabase.child("alamatProyek")
                .setValue(ealamatPekerjaan)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(EditMar.this, MarMain.class));
                            finish();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}