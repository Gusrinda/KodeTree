package com.gusrinda.kodetree.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gusrinda.kodetree.Model.User;
import com.gusrinda.kodetree.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddFragment extends Fragment implements LocationListener {

    private EditText textNama;
    private TextView latitude, longitude;
    private Button btnKamera, btnLokasi, btnTambah;
    private ImageView imgKamera;

    LocationManager locationManager;

    private StorageReference mStorage;
    private DatabaseReference mReference;
    private ProgressDialog mProgress;

    String pathFile, Latitude, Longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        textNama = view.findViewById(R.id.editNama);
        latitude = view.findViewById(R.id.editLatitude);
        longitude = view.findViewById(R.id.editLongitude);

        btnKamera = view.findViewById(R.id.btnCamera);
        btnLokasi = view.findViewById(R.id.btnLokasi);
        btnTambah = view.findViewById(R.id.btnTambah);

        imgKamera = view.findViewById(R.id.imageCamera);

        mStorage = FirebaseStorage.getInstance().getReference();

        mProgress = new ProgressDialog(this.getContext());

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerIntent();
            }
        });

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        return view;
    }

    //Fungsi untuk memanggil kamera dan menyimpan gambar
    private void dispatchPictureTakerIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getContext().getPackageManager()) != null){
            File photoFile = null;
            photoFile = createPhotofile();
            if (photoFile != null){
                pathFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(this.getContext(), "com.gusrinda.kodetree.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 1);
            }
        }

    }

    //Fungsi untuk membuat gambar berada di storage
    private File createPhotofile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File StorageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try{
            image = File.createTempFile(name, ".jpg", StorageDir);

        } catch (IOException e){
            Log.d("mylog", "Excep" + e.toString());
        }
        return  image;
    }


    //Fungsi ketika selesai menyimpan gambar dan menaruh hasilnya pada imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathFile);
                imgKamera.setImageBitmap(bitmap);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getLocation();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "Nyalakan GPS dan Internet", Toast.LENGTH_SHORT).show();

                }
                return;
            }

        }
    }

    //method untuk ambil data long lat
    void getLocation() {
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    //menampilkan long lat ke text view
    @Override
    public void onLocationChanged(Location location) {
        latitude.setText(String.valueOf(location.getLatitude()));
        longitude.setText(String.valueOf(location.getLongitude()));
        Latitude = String.valueOf(location.getLatitude());
        Longitude = String.valueOf(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(getContext(), "Nyalakan GPS dan Internet", Toast.LENGTH_SHORT).show();
    }

    //Fungsi untuk uploading file pada Firebase
    private void uploadFile(){
        if (pathFile != null){
            mProgress.setMessage("Data sedang ditambahkan");
            mProgress.show();

            Uri file = Uri.fromFile(new File(pathFile));
            final StorageReference filePath = mStorage.child("Photos").child(file.getLastPathSegment());
            filePath.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            String imgUrl = downloadUrl.toString();

                            mReference = FirebaseDatabase.getInstance().getReference("Tumbuhan").push();

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("Nama", textNama.getText().toString());
                            hashMap.put("Latitude", Latitude);
                            hashMap.put("Longitude", Longitude);
                            hashMap.put("imgUrl", imgUrl);

                            mReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mProgress.dismiss();
                                    }
                                }
                            });
                        }
                    });
                    User.UpdatePoint();
                    Toast.makeText(getContext(), "Data berhasil ditambahkan!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            //error
        }
    }
}
