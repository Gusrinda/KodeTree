package com.gusrinda.kodetree.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gusrinda.kodetree.Adapter.CustomInfoWindowAdapter;
import com.gusrinda.kodetree.Model.Tumbuhan;
import com.gusrinda.kodetree.R;

public class LocationFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String nama;

    DatabaseReference mReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment6
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // default marker
        LatLng ub = new LatLng(-7.952541, 112.614088);
        mMap.addMarker(new MarkerOptions().position(ub).title("Universitas Brawijaya"));

        //add map marker dari database
        mReference = FirebaseDatabase.getInstance().getReference().child("Tumbuhan");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Tumbuhan tumbuhan = ds.getValue(Tumbuhan.class);
                    String latitude = tumbuhan.getLatitude();
                    String longitude = tumbuhan.getLongitude();
                    String imgurl = tumbuhan.getImgUrl();
                    nama = tumbuhan.getNama();
                    LatLng latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                    mMap.addMarker(new MarkerOptions().position(latLng).title(nama).snippet(imgurl));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getActivity()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.952541, 112.614088), 15.2f));
    }
}
