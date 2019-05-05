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

        //Deklarasi instance firabase database
        db = FirebaseDatabase.getInstance();

        //Deklarasi progress bar
        progressBar = rootView.findViewById(R.id.fragment_trivia_progress_bar);

        //Mengatur visibility dari progress bar menjadi visible
        progressBar.setVisibility(View.VISIBLE);

        //Deklarasi ArrayList berisi trivia
        trivias = new ArrayList<>();

        //Deklarasi RecyclerView yang nantinya akan menampung daftar trivia
        triviaRecycler = rootView.findViewById(R.id.fragment_trivia_recycler_view);

        //Mengatur agar RecyclerView mempunyai ukuran yang tetap
        triviaRecycler.setHasFixedSize(true);

        //Mengatur LayoutManager dari Recycler trivia
        triviaRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));

        //Mendapatkan data Trivia dari Firebase
        db.getReference("Trivia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Untuk setiap child dari Trivia
                for (DataSnapshot sn : dataSnapshot.getChildren()) {

                    //Menginstansiasi objek Trivia kemudian dimasukkan ke dalam ArrayList trivia
                    trivias.add(new Trivia(

                            //Mendapatkan value dari child namaPohon
                            sn.child("namaPohon").getValue(String.class)

                            //Mendapatkan value dari child namaLatin
                            , sn.child("namaLatin").getValue(String.class)

                            //Mendapatkan value dari child jenisBatang
                            , sn.child("jenisBatang").getValue(String.class)

                            //Mendapatkan value dari child jenisAkar
                            , sn.child("jenisAkar").getValue(String.class)

                            //Mendapatkan value dari child jenisDaun
                            , sn.child("jenisDaun").getValue(String.class)

                            //Mendapatkan value dari child manfaat
                            , sn.child("manfaat").getValue(String.class)

                            //Mendapatkan value dari child urlFoto
                            , sn.child("urlFoto").getValue(String.class)
                    ));
                }

                //Instansiasi adapter dari trivia
                triviaAdapter = new TriviaAdapter(rootView.getContext(), trivias);

                //Menyambungkan adapter ke dalam RecyclerView
                triviaRecycler.setAdapter(triviaAdapter);

                //Mengatur visibility progress bar menjadi gone
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Inflate layout dari fragment
        return rootView;
    }
}
