package com.arvirotech.monev.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.perencana.PerencanaMarketing;
import com.arvirotech.monev.model.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register1 extends AppCompatActivity {

    EditText eNama, eEmail, ePhone, ePassword;
    RadioButton eMarketing, eManagement;
    Button eDaftar;
    TextView eMasuk;
    String roles = "";
    users mUsers;

    private ProgressDialog loading;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eNama = findViewById(R.id.txtName);
        eEmail = findViewById(R.id.txtEmail);
        ePhone = findViewById(R.id.txtPhone);
        ePassword = findViewById(R.id.txtPassword);
        eManagement = findViewById(R.id.rbManagement);
        eMarketing = findViewById(R.id.rbMarketing);
        eDaftar = findViewById(R.id.btnDaftar);
        eMasuk = findViewById(R.id.masuk);
        mAuth = FirebaseAuth.getInstance();
        mUsers = new users();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        loading = new ProgressDialog(this);

        eDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaftarUser();
                DataUsers();
            }
        });

        eMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register1.this, LoginActivity.class));
            }
        });
    }

    private void DataUsers() {
        String nama = eNama.getText().toString();
        String email = eEmail.getText().toString();
        String phone = ePhone.getText().toString();
        String password = ePassword.getText().toString();

        if (eMarketing.isChecked()){
            roles = "Marketing";
        }if (eManagement.isChecked())
        {
            roles = "Management";
        }

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        assert firebaseUser != null;
        String userId = firebaseUser.getUid();

        mUsers.setId(userId);
        mUsers.setNama(nama);
        mUsers.setEmail(email);
        mUsers.setPhone(phone);
        mUsers.setPassword(password);
        mUsers.setRoles(roles);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("Users").child(userId);
        dataRef.push().setValue(mUsers);


    }


    private void DaftarUser() {
        String nama = eNama.getText().toString();
        String email = eEmail.getText().toString();
        String phone = ePhone.getText().toString();
        String password = ePassword.getText().toString();

        if (eMarketing.isChecked()){
            roles = "Marketing";
        }if (eManagement.isChecked())
        {
            roles = "Management";
        }

//


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Masukkan Email Anda", Toast.LENGTH_SHORT).show();;
        }if (TextUtils.isEmpty(nama)){
            Toast.makeText(this, "Nama tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Telephon tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        } else
            {
//                register(nama, email, phone,password, roles);

                loading.setTitle("Daftar akun baru");
                loading.setMessage("Mohon tunggu, Akun anda akan diaktifkan");
                loading.setCanceledOnTouchOutside(true);
                loading.show();



                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {
                                    sendUsertoDashboard();
                                    Toast.makeText(Register1.this, "Pendaftaran akun berhasil", Toast.LENGTH_SHORT).show();
                                    loading.dismiss();
                                }else
                                {
                                    String message = task.getException().toString();
                                    Toast.makeText(Register1.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                    loading.dismiss();
                                }
                            }

                        });


        };
    }

    private void sendUsertoDashboard() {
        Intent logindashboard = new Intent(Register1.this, PerencanaMarketing.class);
        logindashboard.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logindashboard);
        finish();
    }

//    private void register (final String nama, final String password, final String email, final String phone, final String roles){
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                            assert firebaseUser != null;
//                            String userId = firebaseUser.getUid();
//
//                            mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(userId);
//
//
//
//                            HashMap<String, String>hashMap = new HashMap<>();
//                            hashMap.put("id", userId);
//                            hashMap.put("Username", nama);
//                            hashMap.put("email", email);
//                            hashMap.put("phone", phone);
//                            hashMap.put("password", password);
//                            hashMap.put("roles", roles);
//
//                            mDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        sendUsertoDashboard();
//                                    Toast.makeText(Register1.this, "Pendaftaran akun berhasil", Toast.LENGTH_SHORT).show();
//                                    loading.dismiss();
//                                    }else {
//                                        String message = task.getException().toString();
//                                    Toast.makeText(Register1.this, "Error: " + message, Toast.LENGTH_SHORT).show();
//                                    loading.dismiss();                                    }
//                                }
//                            });
//
//                        }
//                    }
//                });
//    }

}