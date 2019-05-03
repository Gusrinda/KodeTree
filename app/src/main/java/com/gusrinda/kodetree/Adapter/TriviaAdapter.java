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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_trivia, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Trivia trivia = trivias.get(position);
        holder.namaPohon.setText(trivia.getNamaPohon());
        holder.namaLatin.setText(trivia.getNamaLatin());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailTriviaActivity.class);
                intent.putExtra("namaPohon", trivia.getNamaPohon());
                intent.putExtra("namaLatin", trivia.getNamaLatin());
                intent.putExtra("jenisBatang", trivia.getJenisBatang());
                intent.putExtra("jenisAkar", trivia.getJenisAkar());
                intent.putExtra("jenisDaun", trivia.getJenisDaun());
                intent.putExtra("manfaat", trivia.getManfaat());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trivias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaPohon, namaLatin;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            namaPohon = itemView.findViewById(R.id.card_trivia_nama_tumbuhan);
            namaLatin = itemView.findViewById(R.id.card_trivia_nama_latin);
            cardView = itemView.findViewById(R.id.card_trivia_card_view);
        }
    }
}