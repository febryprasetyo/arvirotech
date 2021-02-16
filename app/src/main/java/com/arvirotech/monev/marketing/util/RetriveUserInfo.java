package com.arvirotech.monev.marketing.util;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public final class RetriveUserInfo {

    private static DatabaseReference rootRef;
    private static FirebaseAuth mAuth;
    private static String currentUserId;
    private static FirebaseDatabase mDatabase;
    private static FirebaseUser mUser;


    private RetriveUserInfo(){}

    public void getName(){
        rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        rootRef.child("Karyawan").child(currentUserId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("userName")))
                        {
                            String retUserName =  dataSnapshot.child("userName").getValue().toString();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }



}
