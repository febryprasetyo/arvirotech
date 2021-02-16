package com.arvirotech.monev.marketing.compon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.pengadaaan.PengadaanMarketing;
import com.arvirotech.monev.model.AddMarketing;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class AddDataPengMar extends AppCompatActivity {

    EditText etNamaProyek, etSatuanKerja, etAlamatProyek, etNilaiPagu, enamaMarketing, eKey;
    RadioButton rbNulPersen, rbLimapuluhPersen, rbSeratusPersen;
    String progress = "";
    String key;

    AddMarketing mAddMarketing;

    DatabaseReference rootRef;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_peng_mar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tambah Pekerjaan Kon");
        actionBar.setDisplayHomeAsUpEnabled(true);

        etNamaProyek = findViewById(R.id.namaProyekMar);
        etSatuanKerja = findViewById(R.id.satuanKerjaMar);
        etAlamatProyek = findViewById(R.id.alamatProyekMar);
        etNilaiPagu = findViewById(R.id.nilaiPaguMar);
        enamaMarketing = findViewById(R.id.tvNamaMarketing);
        rbNulPersen = findViewById(R.id.nulPersen);
        rbLimapuluhPersen = findViewById(R.id.limapuluhPersen);
        rbSeratusPersen = findViewById(R.id.seratusPersen);
        mAddMarketing = new AddMarketing();

        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();

        rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        rootRef.child("Karyawan").child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("userName")))
                        {
                            String jeneng = dataSnapshot.child("userName").getValue().toString();
                            enamaMarketing.setText(jeneng);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void saveData() {
        String namaProyek = etNamaProyek.getText().toString();
        String satuanKerja = etSatuanKerja.getText().toString();
        String alamatProyek = etAlamatProyek.getText().toString();
        String nilaiPagu = etNilaiPagu.getText().toString();
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", calendar).toString();
        String namaMarketing = enamaMarketing.getText().toString();


        if (rbNulPersen.isChecked()) {
            progress = "0%";
        } if (rbLimapuluhPersen.isChecked()){
            progress = "50%";
        } if (rbSeratusPersen.isChecked()){
            progress = "100%";
        }

        mAddMarketing.setNamaProyek(namaProyek);
        mAddMarketing.setSatuanKerja(satuanKerja);
        mAddMarketing.setAlamatProyek(alamatProyek);
        mAddMarketing.setNilaiPagu(nilaiPagu);
        mAddMarketing.setProgress(progress);
        mAddMarketing.setDate(date);
        mAddMarketing.setUser(namaMarketing);

        HashMap<String, Object> hashkon = new HashMap<>();
        hashkon.put("namaProyek", namaProyek);
        hashkon.put("satuanKerja", satuanKerja);
        hashkon.put("alamatProyek", alamatProyek);
        hashkon.put("nilaiPagu", nilaiPagu);
        hashkon.put("date", date);
        hashkon.put("progress", progress);
        hashkon.put("user", namaMarketing);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        String keyLcl = myRef.child("Marketing").child("Pengadaan").push().getKey();
        hashkon.put("key", keyLcl);
        Task task = myRef.child("Marketing").child("Pengadaan").child(keyLcl).setValue(hashkon);
        task.addOnSuccessListener(aVoid -> {
            //the data is added and now we are sure to do something related
            Toast.makeText(AddDataPengMar.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                saveData();
                back();
        }
        return super.onOptionsItemSelected(item);
    }

    private void back() {
        Intent intent = new Intent(AddDataPengMar.this, PengadaanMarketing.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}