package com.gusrinda.kodetree.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gusrinda.kodetree.R;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password;
    Button btnDaftar;

    FirebaseAuth mAuth;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.EditTextUsername);
        email = findViewById(R.id.EditTextEmail);
        password = findViewById(R.id.EditTextPassword);

        btnDaftar = findViewById(R.id.ButtonDaftar);

        mAuth = FirebaseAuth.getInstance();

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Username = username.getText().toString();
                String txt_Email = email.getText().toString();
                String txt_Password = password.getText().toString();

                if (TextUtils.isEmpty(txt_Username) || TextUtils.isEmpty(txt_Email) || TextUtils.isEmpty(txt_Password)) {
                    Toast.makeText(RegisterActivity.this, "Lengkapi semua field Fergusso!", Toast.LENGTH_SHORT).show();
                } else if (txt_Password.length() < 6 ){
                    Toast.makeText(RegisterActivity.this, "Password minimal 6 karakter Fergusso!", Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_Username, txt_Email, txt_Password);
                }
            }
        });
    }

    private void register(final String username, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            mReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);

                            mReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Tidak bisa register dengan Email dan Password ini Fergusso!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
