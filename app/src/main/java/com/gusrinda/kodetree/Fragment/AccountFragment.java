package com.gusrinda.kodetree.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gusrinda.kodetree.Activity.LoginActivity;
import com.gusrinda.kodetree.Adapter.ScoreAdapter;
import com.gusrinda.kodetree.Model.User;
import com.gusrinda.kodetree.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AccountFragment extends Fragment {

    View view;
    TextView nama;
    TextView point;
    TextView rank;
    Button btnLogout;
    ProgressDialog progressBar;
    RecyclerView listScore;

    FirebaseUser mUser;
    DatabaseReference mReference;
    DatabaseReference scoreReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, container, false);
        nama = view.findViewById(R.id.textUsername);
        btnLogout = view.findViewById(R.id.btnLogout);
        point = view.findViewById(R.id.tv_user_point_account);
        listScore = view.findViewById(R.id.rv_account_list_score);
        rank = view.findViewById(R.id.tv_user_rank_account);
        progressBar = new ProgressDialog(this.getContext());
        progressBar.setMessage("Sedang Mengambil data");
        listScore.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar.show();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        getUserData();
        getScore();

        return view;
    }

    void getScore() {
        final ArrayList<User> daftarScore = new ArrayList<>();
        scoreReference= FirebaseDatabase.getInstance().getReference("Users");
        scoreReference.orderByChild("point").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        int i = 0;
                        int userRank =0;
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            User user = postSnapshot.getValue(User.class);
                            daftarScore.add(user);
                            if(user.getId().equals(mUser.getUid())){
                                userRank = (i);
                            }
                            i++;
                        }

                        Collections.reverse(daftarScore);
                        rank.setText(Integer.toString(daftarScore.size() - userRank));
                        listScore.setAdapter(new ScoreAdapter(daftarScore));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );

    }

    void getUserData() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null) {
            Intent login = new Intent(getActivity().getBaseContext(), LoginActivity.class);
            startActivity(login);

        }

        mReference = FirebaseDatabase.getInstance().getReference("Users").child(mUser.getUid());

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                onUserGet(user);
                // point.setText(user.getPoint());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // dipanggil saat data user sudah didapatkan
    private void onUserGet(User user) {
        nama.setText(user.getUsername());
        String pointString = Integer.toString(user.getPoint());
        point.setText(pointString);
        progressBar.hide();
    }

}
