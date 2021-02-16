package com.arvirotech.monev.auth;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.arvirotech.monev.R;
import com.arvirotech.monev.marketing.MarMain;
import com.arvirotech.monev.marketing.konstruksi.KonstruksiMarketing;
import com.arvirotech.monev.model.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText eNama, eEmail, ePhone, ePassword;
    RadioButton eMarketing, eManagement;
    Button eDaftar;
    TextView eMasuk;
    String roles = "";
    users mUsers;

    private ProgressDialog loading;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;

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

        /* Todo: Firebase*/
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUsers = new users();
        loading = new ProgressDialog(this);

        eMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, LoginActivity.class));
            }
        });

        eDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)){
                    Toast.makeText(Register.this, getString(R.string.all_field), Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8 ){
                    Toast.makeText(Register.this, getString(R.string.minimum_password), Toast.LENGTH_SHORT).show();
                } else {
                    register(nama, email, password, phone, roles);
                }

            }
        });
    }

    private void register(String nama, String email, String password, String phone, String roles) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userId = firebaseUser.getUid();

                            mDatabase = FirebaseDatabase.getInstance().getReference("Karyawan").child(userId);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("userName", nama);
                            hashMap.put("email", email);
                            hashMap.put("phone", phone);
                            hashMap.put("password", password);
                            hashMap.put("roles", roles);

                            mDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                       if (roles == "Marketing") {
                                           Intent intent = new Intent(Register.this, MarMain.class);
                                           intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(intent);
                                           finish();
                                       } else {
                                           Intent intent = new Intent(Register.this, KonstruksiMarketing.class);
                                           intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(intent);
                                           finish();
                                       }
                                    }else {
                                        Toast.makeText(Register.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }
                });
    }
}
