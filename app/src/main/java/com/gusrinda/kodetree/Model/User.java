package com.gusrinda.kodetree.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {

    private String id;
    private String username;
    private int point = 0;

    static final String  TAG = "USER";

    public final static int  incrementPoint = 10;
    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }


    public User setPoint(int point){
        this.point = point;
        return this;
    }

    public User(){

    }

    public static void UpdatePoint(){

        FirebaseUser mUser  = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user =dataSnapshot.getValue(User.class);
                if(user!=null){
                    user.point+= incrementPoint;
                    mReference.setValue(user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,databaseError.getMessage());
            }
        });

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }
}
