package com.example.rizwan.fyp.Evaluator;

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

public class EvaluatorLogin extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private EditText evMbl,evPass;
    private Button evLogin;
    private String mbl,pass;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluator_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Evaluator Login");
        evMbl = findViewById(R.id.ev_mbl);
        evPass = findViewById(R.id.ev_login_password);
        evLogin =findViewById(R.id.ev_login_btn);

        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Evaluator");

        evLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbl = evMbl.getText().toString();
                pass = evPass.getText().toString();

                if (mbl.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(EvaluatorLogin.this, "Kindly enter email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.setTitle("Login");
                    progressDialog.setMessage("Please wait......");
                    progressDialog.show();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.hasChild(mbl))
                            {
                                String password = Objects.requireNonNull(dataSnapshot.child(mbl).child("password").getValue()).toString();

                                if (pass.equals(password))
                                {
                                    Intent intent = new Intent(EvaluatorLogin.this,EvaluatorHome.class);
                                    intent.putExtra("ID",mbl);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(EvaluatorLogin.this, "Invalid password", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(EvaluatorLogin.this, "Record does't exist", Toast.LENGTH_SHORT).show();

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
