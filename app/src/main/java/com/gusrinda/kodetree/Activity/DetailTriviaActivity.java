package com.gusrinda.kodetree.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gusrinda.kodetree.R;

public class DetailTriviaActivity extends AppCompatActivity {

    TextView title, namaPohon, namaLatin, jenisBatang, jenisAkar, jenisDaun, manfaat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trivia);

        title = findViewById(R.id.detail_trivia_nama_pohon_title);
        namaPohon = findViewById(R.id.detail_trivia_nama_pohon);
        namaLatin = findViewById(R.id.detail_trivia_nama_latin);
        jenisBatang = findViewById(R.id.detail_trivia_jenis_batang);
        jenisAkar = findViewById(R.id.detail_trivia_jenis_akar);
        jenisDaun = findViewById(R.id.detail_trivia_jenis_daun);
        manfaat = findViewById(R.id.detail_trivia_manfaat);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("namaPohon"));
        namaPohon.setText(intent.getStringExtra("namaPohon"));
        namaLatin.setText(intent.getStringExtra("namaLatin"));
        jenisBatang.setText(intent.getStringExtra("jenisBatang"));
        jenisAkar.setText(intent.getStringExtra("jenisAkar"));
        jenisDaun.setText(intent.getStringExtra("jenisDaun"));
        manfaat.setText(intent.getStringExtra("manfaat"));
    }
}
