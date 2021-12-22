package com.example.rizwan.fyp.Admin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rizwan.fyp.GroupModel;
import com.example.rizwan.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddNewGroup extends AppCompatActivity
{
    private EditText etGLName,etGLReg,etGLEmail,etMemName,etMemEmail,etMemReg
            ,etGLMbl,etPassword,etConPassword;
    private Spinner spProgram;
    private String strGLName,strGLReg,strGLEmail,strMemName,strMemEmail,
            strMemReg,strGLMbl,strPassword,strConPassword,strprogram;
    private Button btnAdd;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        initializedFields();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New group");

        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Groups");

        // aik activity ka data dosri activity me show krvane k liye

        Bundle bundle = getIntent().getExtras();

        String value = getIntent().getExtras().getString("Value");;


        if (value.equals("v"))
        {
            etGLMbl.setEnabled(false);
            etPassword.setEnabled(false);
            etConPassword.setEnabled(false);
            final String lstrConfirmPassword,lstrGroupLReg,lsrMReg,lstrGLName,lstrGLEmail,lstrMemName,lstrMemEmail,
                    lstrGLMbl,lstrPassword,lstrConPassword;
            lstrGLName = bundle.getString("GROUPLNAME");
            lstrGroupLReg = bundle.getString("GROUPLREG");
            lstrGLEmail = bundle.getString("GROUPLEMAIL");
            lstrMemName = bundle.getString("GROUPMEMBER");
            lsrMReg = bundle.getString("GROUPMEMBERREG");
            lstrMemEmail = bundle.getString("GROUPMEMAIL");
            lstrGLMbl = bundle.getString("GROUPLMOBILE");
            lstrPassword = bundle.getString("GROUPPASSWORD");
            lstrConfirmPassword = bundle.getString("GROUPCONPASSWORD");





            btnAdd.setText("Update");


            etGLName.setText(lstrGLName);
            etGLReg.setText(lstrGroupLReg);
            etGLEmail.setText(lstrGLEmail);
            etMemName.setText(lstrMemName);
            etMemReg.setText(lsrMReg);
            etMemEmail.setText(lstrMemEmail);
            etGLMbl.setText(lstrGLMbl);
            etPassword.setText(lstrPassword);
            etConPassword.setText(lstrConfirmPassword);


            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getTextFromFields();

                    uploadData();

                    startActivity(new Intent(AddNewGroup.this,GroupList.class));
                }
            });

        }
        else
        {
            //button ka code

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTextFromFields();
                    if (strGLName.isEmpty() || strGLEmail.isEmpty()
                            || strMemName.isEmpty() || strMemEmail.isEmpty() || strprogram.equals("Select Program") || strGLMbl.isEmpty() || strPassword.isEmpty() || strConPassword.isEmpty())
                    {
                        Toast.makeText(AddNewGroup.this, "Kindly enter all fields", Toast.LENGTH_SHORT).show();
                    }
                    else if (!strPassword.equals(strConPassword))
                    {
                        Toast.makeText(AddNewGroup.this, "Mismatch password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        databaseReference.child(strGLMbl).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                progressDialog.setTitle("Checking Data");
                                progressDialog.setMessage("Please wait");
                                progressDialog.show();

                                if (dataSnapshot.exists())
                                {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder builder =new AlertDialog.Builder(AddNewGroup.this, android.R.style.Theme_Material_Dialog_Alert);
                                    builder.setTitle(strGLMbl)
                                            .setMessage("Record already exists?")
                                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    uploadData();
                                                    dialog.dismiss();
                                                }
                                            })
                                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // do nothing
                                                }
                                            })
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .show();
                                }
                                else
                                {
                                    uploadData();
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

    private void uploadData()
    {
        GroupModel groupModel = new  GroupModel();

        groupModel.setGroupLeaderName(strGLName);
        groupModel.setGroupLeaderReg(strGLReg);
        groupModel.setGroupLeaderEmail(strGLEmail);
        groupModel.setMemberName(strMemName);
        groupModel.setMemberReg(strMemReg);
        groupModel.setMemberEmail(strMemEmail);
        groupModel.setProgram(strprogram);
        groupModel.setGroupLeaderMobile(strGLMbl);
        groupModel.setGroupPassword(strPassword);


        progressDialog.setTitle("Creating New Group");
        progressDialog.setMessage("Please wait......");
        progressDialog.show();

        databaseReference.child(groupModel.getGroupLeaderMobile()).setValue(groupModel);
        clearData();

        progressDialog.dismiss();

        Toast.makeText(AddNewGroup.this, "New group created successfully ", Toast.LENGTH_SHORT).show();
    }

    private void clearData() {
        etGLName.setText(null);
        etGLReg.setText(null);
        etGLEmail.setText(null);
        etMemName.setText(null);
        etMemReg.setText(null);
        etMemEmail.setText(null);
        etGLMbl.setText(null);
        etPassword.setText(null);
        etConPassword.setText(null);
    }

    private void getTextFromFields() {
        strGLName = etGLName.getText().toString();
        strGLReg = etGLReg.getText().toString();
        strGLEmail = etGLEmail.getText().toString();
        strMemName = etMemName.getText().toString();
        strMemReg = etMemReg.getText().toString();
        strMemEmail = etMemEmail.getText().toString();
        strGLMbl = etGLMbl.getText().toString();
        strprogram = spProgram.getSelectedItem().toString();
        strPassword = etPassword.getText().toString();
        strConPassword = etConPassword.getText().toString();

    }

    private void initializedFields()
    {
        etGLName = findViewById(R.id.group_leader_name);
        etGLReg = findViewById(R.id.group_leader_roll);
        etGLEmail = findViewById(R.id.group_leader_Email);
        etMemName = findViewById(R.id.member_name);
        etMemReg = findViewById(R.id.member_Reg_No);
        etMemEmail = findViewById(R.id.member_email_id);
        etGLMbl = findViewById(R.id.leader_mobile_number);
        spProgram = findViewById(R.id.select_program);
        etPassword = findViewById(R.id.group_password);
        etConPassword = findViewById(R.id.group_confirm_password);

        btnAdd = findViewById(R.id.addnewgroupbtn);

    }
}
