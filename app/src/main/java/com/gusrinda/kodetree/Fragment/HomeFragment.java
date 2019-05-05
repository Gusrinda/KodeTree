package com.gusrinda.kodetree.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gusrinda.kodetree.Adapter.BannerAdapter;
import com.gusrinda.kodetree.Model.BannerPojo;
import com.gusrinda.kodetree.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv = view.findViewById(R.id.rv_banner_fragment_home);
        rv.setLayoutManager(new LinearLayoutManager(container.getContext()));
        ArrayList<BannerPojo> dataPojo = new ArrayList<>();
//        dataPojo.add(new BannerPojo(R.id.navigation_home,R.drawable.ic_home_black_24dp,"Home"));
        dataPojo.add(new BannerPojo(R.id.navigation_tambah,R.drawable.ic_add_circle_outline_black_24dp,"Mulai tambahakan data mengenai pohon "));
        dataPojo.add(new BannerPojo(R.id.navigation_trivia,R.drawable.ic_lightbulb_outline_black_24dp,"Mau tau tentang pohon disekitar anda \n yuk cek  "));
        dataPojo.add(new BannerPojo(R.id.navigation_lokasi,R.drawable.ic_pin_drop_black_24dp,"Lihat data Pohon disekitar anda"));
        dataPojo.add(new BannerPojo(R.id.navigation_akun,R.drawable.ic_account_circle_black_24dp,"Lihat Poin Anda"));


        BottomNavigationView nav = getActivity().findViewById(R.id.nav_view);
        if(nav == null){
            Log.d("Home Frament master","nav null");
        }
        rv.setAdapter(new BannerAdapter(nav,dataPojo));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
