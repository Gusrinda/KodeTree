package com.gusrinda.kodetree.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gusrinda.kodetree.R;

public class DetailTriviaActivity extends AppCompatActivity {

    TextView title, namaPohon, namaLatin, jenisBatang, jenisAkar, jenisDaun, manfaat;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trivia);

        //Deklarasi title pada activity detail trivia
        title = findViewById(R.id.detail_trivia_nama_pohon_title);

        //Deklarasi namaPohon pada activity detail trivia
        namaPohon = findViewById(R.id.detail_trivia_nama_pohon);

        //Deklarasi namaLatin pada activity detail trivia
        namaLatin = findViewById(R.id.detail_trivia_nama_latin);

        //Deklarasi jenisBatang pada activity detail trivia
        jenisBatang = findViewById(R.id.detail_trivia_jenis_batang);

        //Deklarasi jenisAkar pada activity detail trivia
        jenisAkar = findViewById(R.id.detail_trivia_jenis_akar);

        //Deklarasi jenisDaun pada activity detail trivia
        jenisDaun = findViewById(R.id.detail_trivia_jenis_daun);

        //Deklarasi manfaat pada activity detail trivia
        manfaat = findViewById(R.id.detail_trivia_manfaat);

        //Deklarasi image pada activity detail trivia
        image = findViewById(R.id.detail_trivia_foto_pohon);

        //Mendapatkan intent
        Intent intent = getIntent();

        //Mengubah text dari title menjadi namaPohon yang dikirimkan dari intent
        title.setText(intent.getStringExtra("namaPohon"));

        //Mengubah text dari namaPohon menjadi namaPohon yang dikirimkan dari intent
        namaPohon.setText(intent.getStringExtra("namaPohon"));

        //Mengubah text dari namaLatin menjadi namaLatin yang dikirimkan dari intent
        namaLatin.setText(intent.getStringExtra("namaLatin"));

        //Mengubah text dari jenisBatang menjadi jenisBatang yang dikirimkan dari intent
        jenisBatang.setText(intent.getStringExtra("jenisBatang"));

        //Mengubah text dari jenisAkar menjadi jenisAkar yang dikirimkan dari intent
        jenisAkar.setText(intent.getStringExtra("jenisAkar"));

        //Mengubah text dari jenisDaun menjadi jenisDaun yang dikirimkan dari intent
        jenisDaun.setText(intent.getStringExtra("jenisDaun"));

        //Mengubah text dari manfaat menjadi manfaat yang dikirimkan dari intent
        manfaat.setText(intent.getStringExtra("manfaat"));

        //Mengubah image dari title menjadi image yang URL sama dengan urlFoto yang dikirimkan dari intent
        Glide.with(getApplicationContext()).load(intent.getStringExtra("urlFoto")).into(image);
    }
}
