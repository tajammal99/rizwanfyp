package com.example.rizwan.fyp.Admin;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rizwan.fyp.R;
import com.example.rizwan.fyp.Student.StdHome;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddSuperVisors extends AppCompatActivity {

    Button btnAddNew;
    EditText name,mobile;
    String strName,strMobile;
    private DatabaseReference supervisorRef;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_super_visors);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Supervisor");


        btnAddNew = findViewById(R.id.add_new_suprvisor);
        name = findViewById(R.id.super_name);
        mobile = findViewById(R.id.super_mobile);

        progressDialog = new ProgressDialog(this);

        supervisorRef = FirebaseDatabase.getInstance().getReference().child("Supervisor");


        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strName = name.getText().toString();
                strMobile = mobile.getText().toString();

                if (strName.isEmpty() || strMobile.isEmpty())
                {
                    Toast.makeText(AddSuperVisors.this, "Enter name or mobile", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.setTitle("Saving Data");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("supervisor", strName+" "+strMobile);

                    supervisorRef.child(strMobile).updateChildren(userInfo);

                    Toast.makeText(AddSuperVisors.this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                }
            }
        });



    }
}
