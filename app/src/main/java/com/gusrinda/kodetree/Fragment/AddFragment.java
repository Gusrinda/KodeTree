package com.gusrinda.kodetree.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gusrinda.kodetree.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddFragment extends Fragment {

    private EditText textNama;
    private TextView lokasi;
    private Button btnKamera, btnLokasi, btnTambah;
    private ImageView imgKamera;


    private StorageReference mStorage;

    private ProgressDialog mProgress;

    String pathFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        textNama = view.findViewById(R.id.editNama);
        lokasi = view.findViewById(R.id.editLokasi);

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

    //Fungsi untuk uploading file pada Firebase
    private void uploadFile(){
        if (pathFile != null){
            mProgress.setMessage("Data sedang ditambahkan");
            mProgress.show();

            Uri file = Uri.fromFile(new File(pathFile));
            StorageReference filePath = mStorage.child("Photos").child(file.getLastPathSegment());
            filePath.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();
                    Toast.makeText(getContext(), "Data berhasil ditambahkan!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            //error
        }
    }
}
