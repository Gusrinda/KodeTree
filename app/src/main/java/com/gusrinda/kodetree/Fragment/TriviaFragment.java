package com.gusrinda.kodetree.Fragment;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gusrinda.kodetree.Activity.DetailTriviaActivity;
import com.gusrinda.kodetree.Adapter.TriviaAdapter;
import com.gusrinda.kodetree.Model.Trivia;
import com.gusrinda.kodetree.R;

import java.util.ArrayList;
import java.util.List;

public class TriviaFragment extends Fragment {
    ArrayList<Trivia> trivias;
    TriviaAdapter triviaAdapter;
    RecyclerView triviaRecycler;
    View rootView;
    FirebaseDatabase db;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_trivia, container, false);

        db = FirebaseDatabase.getInstance();

        progressBar = rootView.findViewById(R.id.fragment_trivia_progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        trivias = new ArrayList<>();
        triviaRecycler = rootView.findViewById(R.id.fragment_trivia_recycler_view);
        triviaRecycler.setHasFixedSize(true);
        triviaRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));

        db.getReference("Trivia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    trivias.add(new Trivia(
                            sn.child("namaPohon").getValue(String.class)
                            , sn.child("namaLatin").getValue(String.class)
                            , sn.child("jenisBatang").getValue(String.class)
                            , sn.child("jenisAkar").getValue(String.class)
                            , sn.child("jenisDaun").getValue(String.class)
                            , sn.child("manfaat").getValue(String.class)
                    ));
                }
                triviaAdapter = new TriviaAdapter(rootView.getContext(), trivias);
                triviaRecycler.setAdapter(triviaAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }
}
