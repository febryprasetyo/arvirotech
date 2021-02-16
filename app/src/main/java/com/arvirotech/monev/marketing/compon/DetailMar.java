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
import android.widget.Toast;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.perencana.PerencanaMarketing;
import com.arvirotech.monev.model.listData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import static java.nio.file.Paths.get;

public class DetailMar extends AppCompatActivity {

    private List<listData> dataList;

    private String Pekerjaan, Satker, Alamat, Nilai, User, Progress, key;
    private EditText namaPekerjaan, alamatPekerjaan, satuanKerja, nilaiPagu, user;
    String progress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail Pekerjaan");
        actionBar.setDisplayHomeAsUpEnabled(true);

        namaPekerjaan = findViewById(R.id.edNamaProyek);
        alamatPekerjaan = findViewById(R.id.edAlamatProyek);
        satuanKerja = findViewById(R.id.edSatuanKerja);
        nilaiPagu = findViewById(R.id.edNilaiPagu);
        user = findViewById(R.id.ednamaMarketing);

        Pekerjaan = getIntent().getExtras().get("Pekerjaan").toString();
        Satker = getIntent().getExtras().get("satuanKerja").toString();
        Alamat = getIntent().getExtras().get("alamatProyek").toString();
        Nilai = getIntent().getExtras().get("nilaiPagu").toString();
        User = getIntent().getExtras().get("user").toString();
        Progress = getIntent().getExtras().get("progress").toString();
        key = getIntent().getExtras().get("key").toString();


//        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myref = mDatabase.getReference("Marketing").child("Perencana").push();
//        String id = myref.getRef().getKey();

        namaPekerjaan.setText(Pekerjaan);
        alamatPekerjaan.setText(Alamat);
        satuanKerja.setText(Satker);
        user.setText(User);

        Double _pagu = Double.valueOf(Nilai);

        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        formatRupiah.setDecimalFormatSymbols(formatRp);

        nilaiPagu.setText(formatRupiah.format(_pagu));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                EditButton();
                return true;
            case R.id.hapus:
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mDatabase = database.getReference().child("Marketing").child("Perencana").child(key);
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                Intent intent = new Intent(DetailMar.this, PerencanaMarketing.class);
                startActivity(intent);
                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void EditButton() {

        Intent intent = new Intent(DetailMar.this, EditMar.class);
        intent.putExtra("satuanKerja", Satker);
        intent.putExtra("Pekerjaan", Pekerjaan);
        intent.putExtra("alamatProyek", Alamat);
        intent.putExtra("nilaiPagu", Nilai);
        intent.putExtra("progress", progress);
        intent.putExtra("key", key);
        startActivity(intent);
        finish();
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