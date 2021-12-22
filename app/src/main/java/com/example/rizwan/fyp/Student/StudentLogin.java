package com.example.rizwan.fyp.Student;

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

import com.example.rizwan.fyp.Admin.AdminHom;
import com.example.rizwan.fyp.Admin.AdminLogin;
import com.example.rizwan.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class StudentLogin extends AppCompatActivity
{
    private DatabaseReference databaseReference;
    private EditText groupEmail,groupPass;
    private Button groupLogin;
    private String email,pass;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Group Login");
        groupEmail = findViewById(R.id.std_email);
        groupPass = findViewById(R.id.std_password);
        groupLogin = findViewById(R.id.std_login);

        progressDialog = new ProgressDialog(this);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Groups");


        groupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = groupEmail.getText().toString();
                pass = groupPass.getText().toString();

                if (email.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(StudentLogin.this, "Kindly enter email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.setTitle("Login");
                    progressDialog.setMessage("Please wait......");
                    progressDialog.show();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.hasChild(email))
                            {
                                String password = Objects.requireNonNull(dataSnapshot.child(email).child("groupPassword").getValue()).toString();

                                if (pass.equals(password))
                                {
                                    Intent intent = new Intent(StudentLogin.this,StdHome.class);
                                    intent.putExtra("GROUPID",email);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(StudentLogin.this, "Invalid password", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(StudentLogin.this, "Record does't exist", Toast.LENGTH_SHORT).show();
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
