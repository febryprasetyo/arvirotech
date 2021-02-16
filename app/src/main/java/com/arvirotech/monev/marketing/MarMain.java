package com.arvirotech.monev.marketing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arvirotech.monev.R;
import com.arvirotech.monev.auth.LoginActivity;
import com.arvirotech.monev.marketing.konstruksi.KonstruksiMarketing;
import com.arvirotech.monev.marketing.pengadaan.PengadaanMarketing;
import com.arvirotech.monev.marketing.perencana.PerencanaMarketing;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

public class MarMain extends AppCompatActivity {

    private static final String TAG = "USERNAME" ;
    ImageView imageView;
    LinearLayout lPerencana, lKonstruksi, lPengadaan;
    private TextView totalPerencana, total0, total50, total100, totalKontrak,
            PrcKerja0, PrcKerja50, PrcKerja100, PrcKerjaKontrak,
            Nama;
    private TextView totalKonstruksi, total0K, total50K, total100K, totalKontrakK,
            konKerja0, konKerja50, konKerja1000, konKerjaKontrak;
    String user;

    DatabaseReference rootRef;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mar_main);

        // TODO: Initialized
        imageView = findViewById(R.id.avatar);
        lPerencana = findViewById(R.id.layoutPerencana);
        lKonstruksi = findViewById(R.id.layoutKonstruksi);
        lPengadaan = findViewById(R.id.layoutPengadaan);
        Nama = findViewById(R.id.txtName);

        totalPerencana = findViewById(R.id.PrcTotalPagu);
        total0 = findViewById(R.id.PrcPaguMarNol);
        total50 = findViewById(R.id.PrcPaguMarLima);
        total100 = findViewById(R.id.PrcPaguMarSeratus);
        totalKontrak = findViewById(R.id.PrcPaguMarKontrak);
        PrcKerja0 = findViewById(R.id.PrcKerjaMarNol);
        PrcKerja50 = findViewById(R.id.PrcKerjaMarLima);
        PrcKerja100 = findViewById(R.id.PrcKerjaMarSeratus);
        PrcKerjaKontrak = findViewById(R.id.PrcKerjaMarKontrak);

        totalKonstruksi = findViewById(R.id.KonTotalPagu);
        total0K = findViewById(R.id.KonPaguMarNol);
        total50K = findViewById(R.id.KonPaguMarLima);
        total100K = findViewById(R.id.KonPaguMarSeratus);
        totalKontrakK = findViewById(R.id.KonPaguMarKontrak);
        konKerja0 = findViewById(R.id.KonKerjaMarNol);
        konKerja50 = findViewById(R.id.KonKerjaMarLima);
        konKerja1000 = findViewById(R.id.KonKerjaMarSeratus);
        konKerjaKontrak = findViewById(R.id.KonKerjaMarKontrak);

        /* Todo: Firebase*/
        rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        RetriveUserInfo();
        OpenActivity();
        Perencana();
        Konstruksi();
        Pengadaan();
//        TotalOmzet();



        // TODO: PERENCANA
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Marketing").child("Perencana");
        Query qTotal0 = nm.orderByChild("progress").equalTo("0%");
        Query qTotal50 = nm.orderByChild("progress").equalTo("50%");
        Query qTotal100 = nm.orderByChild("progress").equalTo("100%");
        Query qTotalKon = nm.orderByChild("progress").equalTo("Kontrak");

        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    Map<String, Object> map = (Map<String,Object>) ds.getValue();
                    Object totalPagu = map.get("nilaiPagu");
                    int pValue = Integer.parseInt(String.valueOf(totalPagu));
                    sum += pValue;

                    Double _totalPerencana = Double.valueOf(sum);

                    DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    formatRupiah.setDecimalFormatSymbols(formatRp);

                    totalPerencana.setText(formatRupiah.format(_totalPerencana));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotal0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                int countKerja = 0;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                      bind data
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

//                       convert currency
                        Double _totalPerencana = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        total0.setText(formatRupiah.format(_totalPerencana));

                    }

                    countKerja = (int) dataSnapshot.getChildrenCount();
                    PrcKerja0.setText(Integer.toString(countKerja));

                }else {
                    total0.setText("0");
                    PrcKerja0.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotal50.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                int countKerja = 0;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

                        Double _total50 = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        total50.setText(formatRupiah.format(_total50));

                    }

                    countKerja = (int) dataSnapshot.getChildrenCount();
                    PrcKerja50.setText(Integer.toString(countKerja));

                }else {
                    total50.setText("0");
                    PrcKerja50.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotal100.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                int countKerja = 0;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;
                        Double _total100 = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        total100.setText(formatRupiah.format(_total100));
                    }

                    countKerja = (int) dataSnapshot.getChildrenCount();
                    PrcKerja100.setText(Integer.toString(countKerja));

                }else {
                    total100.setText("0");
                    PrcKerja100.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotalKon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;

                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()){

                        Map<String, Object> map = (Map<String,Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

                        Double _totalKontrak = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        totalKontrak.setText(formatRupiah.format(_totalKontrak));
                    }
                    int countKerja=0;
                    countKerja = (int) dataSnapshot.getChildrenCount();
                    PrcKerjaKontrak.setText(Integer.toString(countKerja));

                }else {
                    totalKontrak.setText("0");
                    PrcKerjaKontrak.setText("0");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // TODO: KONSTRUKSI

        final DatabaseReference nmk = FirebaseDatabase.getInstance().getReference("Marketing").child("Konstruksi");
        Query qTotal0k = nmk.orderByChild("progress").equalTo("0%");
        Query qTotal50k = nmk.orderByChild("progress").equalTo("50%");
        Query qTotal100k = nmk.orderByChild("progress").equalTo("100%");
        Query qTotalKonk = nmk.orderByChild("progress").equalTo("Kontrak");

        nmk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    Map<String, Object> map = (Map<String,Object>) ds.getValue();
                    Object totalPagu = map.get("nilaiPagu");
                    int pValue = Integer.parseInt(String.valueOf(totalPagu));
                    sum += pValue;

                    Double _totalKonstruksi = Double.valueOf(sum);

                    DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    formatRupiah.setDecimalFormatSymbols(formatRp);
                    totalKonstruksi.setText(formatRupiah.format(_totalKonstruksi));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotal0k.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                int countKerja = 0;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

                        Double _total0K = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        total0K.setText(formatRupiah.format(_total0K));
                    }

                    countKerja = (int) dataSnapshot.getChildrenCount();
                    konKerja0.setText(Integer.toString(countKerja));

                }else {
                    total0K.setText("0");
                    konKerja0.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotal50k.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                int countKerja = 0;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

                        Double _total50K = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        total50K.setText(formatRupiah.format(_total50K));
                    }

                    countKerja = (int) dataSnapshot.getChildrenCount();
                    konKerja50.setText(Integer.toString(countKerja));

                }else {
                    total50K.setText("0");
                    konKerja50.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotal100k.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                int countKerja = 0;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

                        Double _total100K = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        total100K.setText(formatRupiah.format(_total100K));
                    }

                    countKerja = (int) dataSnapshot.getChildrenCount();
                    konKerja1000.setText(Integer.toString(countKerja));

                }else {
                    total100K.setText("0");
                    konKerja1000.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qTotalKonk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;

                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()){

                        Map<String, Object> map = (Map<String,Object>) ds.getValue();
                        Object totalPagu = map.get("nilaiPagu");
                        int pValue = Integer.parseInt(String.valueOf(totalPagu));
                        sum += pValue;

                        Double _totalKontrakK = Double.valueOf(sum);

                        DecimalFormat formatRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setMonetaryDecimalSeparator(',');
                        formatRp.setGroupingSeparator('.');
                        formatRupiah.setDecimalFormatSymbols(formatRp);

                        totalKontrakK.setText("Rp. " + String.valueOf(_totalKontrakK));
                    }
                    int countKerja=0;
                    countKerja = (int) dataSnapshot.getChildrenCount();
                    konKerjaKontrak.setText(Integer.toString(countKerja));

                }else {
                    totalKontrakK.setText("0");
                    konKerjaKontrak.setText("0");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void OpenActivity() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                Intent intent = new Intent(MarMain.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        // TODO: Open Activity
        lPerencana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarMain.this, PerencanaMarketing.class));
            }
        });
        lKonstruksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarMain.this, KonstruksiMarketing.class));
            }
        });
        lPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarMain.this, PengadaanMarketing.class));
            }
        });
    }

    private void Perencana() {

    }

    private void Konstruksi() {

    }

    private void Pengadaan() {

    }

    private void RetriveUserInfo(){
        currentUserId = mAuth.getCurrentUser().getUid();
        rootRef.child("Karyawan").child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("userName")))
                        {
                            String retUserName =  dataSnapshot.child("userName").getValue().toString();

                            Nama.setText("Hi, " + retUserName);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}