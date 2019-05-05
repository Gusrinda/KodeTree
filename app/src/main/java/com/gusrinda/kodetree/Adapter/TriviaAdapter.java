package com.gusrinda.kodetree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gusrinda.kodetree.Activity.DetailTriviaActivity;
import com.gusrinda.kodetree.Model.Trivia;
import com.gusrinda.kodetree.R;

import java.util.ArrayList;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Trivia> trivias;

    public TriviaAdapter(Context context, ArrayList<Trivia> data) {
        this.context = context;
        this.trivias = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflate layout card_trivia
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_trivia, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //Instansiasi objek trivia yang isinya sama dengan trivia pada index position
        final Trivia trivia = trivias.get(position);

        //Mengisi namaPohon dari holder menjadi namaPohon dari objek trivia
        holder.namaPohon.setText(trivia.getNamaPohon());

        //Mengisi namaLatin dari holder menjadi namaLatin dari objek trivia
        holder.namaLatin.setText(trivia.getNamaLatin());

        //Saat cardView diklik
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Membuat intent ke activity detail trivia
                Intent intent = new Intent(v.getContext(), DetailTriviaActivity.class);

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("namaPohon", trivia.getNamaPohon());

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("namaLatin", trivia.getNamaLatin());

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("jenisBatang", trivia.getJenisBatang());

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("jenisAkar", trivia.getJenisAkar());

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("jenisDaun", trivia.getJenisDaun());

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("manfaat", trivia.getManfaat());

                //Menambahkan namaPohon ke dalam intent
                intent.putExtra("urlFoto", trivia.getUrlFoto());

                //Menjalankan activity
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        //Mengembalikan ukuran dari ArrayList trivia
        return trivias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Deklarasi TextView namaPohon dan namaLatin
        private TextView namaPohon, namaLatin;

        //Deklarasi CardView
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            namaPohon = itemView.findViewById(R.id.card_trivia_nama_tumbuhan);
            namaLatin = itemView.findViewById(R.id.card_trivia_nama_latin);
            cardView = itemView.findViewById(R.id.card_trivia_card_view);
        }
    }
}