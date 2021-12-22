package com.example.rizwan.fyp.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rizwan.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminLogin extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EditText adminEmail,adminPass;
    private Button adminLogin;
    private String email,pass;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Admin Login");

        adminEmail = findViewById(R.id.admin_email);
        adminPass = findViewById(R.id.admin_password);
        adminLogin = findViewById(R.id.admin_login);

        progressDialog = new ProgressDialog(this);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin");


        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = adminEmail.getText().toString();
                pass = adminPass.getText().toString();

                if (email.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(AdminLogin.this, "Kindly enter email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.setTitle("Login");
                    progressDialog.setMessage("Please wait......");
                    progressDialog.show();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists())
                            {
                                String username = Objects.requireNonNull(dataSnapshot.child("admin").getValue()).toString();
                                String password = Objects.requireNonNull(dataSnapshot.child("password").getValue()).toString();

                                if (email.equals(username) && pass.equals(password))
                                {
                                    Intent intent = new Intent(AdminLogin.this,AdminHom.class);

                                    startActivity(intent);
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    Toast.makeText(AdminLogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                            else
                            {
                                Toast.makeText(AdminLogin.this, "Admin does't exist", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
