package com.example.rizwan.fyp.Evaluator;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizwan.fyp.Admin.AddNewGroup;
import com.example.rizwan.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProDefence extends AppCompatActivity
{
    private EditText etProjectId,etProgram,etLeaderName,etLReg,etMemberName,etMReg,etTitle,etSupervisor,etSubLmarks
            ,etsubmmarks,etProbLmarks,etProbMmarks,etProjLmarks,etProjMmarks,etContLmarks,etContMmarks
            ,etLangLmarks,etLangMmarks,etProposalRemarks;
    private TextView ProposalLmarks,ProposalMmarks;

    String strProjectId,strProgram,strLeaderName,strLReg,strMemberName,strMReg,strTitle,strSupervisor,strSubLmarks
            ,strsubmmarks,strProbLmarks,strProbMmarks,strProjLmarks,strProjMmarks,strContLmarks,strContMmarks
            ,strLangLmarks,strLangMmarks,strSelection,strProposalRemarks,strGroupID;

    private Button proDefBtn;

    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference,showData,validation;

    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_defence);

        initialized();

        progressDialog = new ProgressDialog(this);

        showData = FirebaseDatabase.getInstance().getReference().child("Groups");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Defence").child("Proposal");
        validation = FirebaseDatabase.getInstance().getReference().child("Validation").child("Proposal");


        DialogInformation();



        proDefBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTextFromField();

                if (strSubLmarks.isEmpty() || strsubmmarks.isEmpty() || strProbLmarks.isEmpty() || strProbMmarks.isEmpty()
                || strProjLmarks.isEmpty() || strProjMmarks.isEmpty() || strContLmarks.isEmpty() || strContMmarks.isEmpty()
                        || strLangLmarks.isEmpty() || strLangMmarks.isEmpty() || strProposalRemarks.isEmpty() )
                {
                    Toast.makeText(ProDefence.this, "Kindly fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strSubLmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strsubmmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strProbLmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strProbMmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strProjLmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strProjMmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strContLmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strContMmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strLangLmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strLangMmarks) > 4 )
                {
                    Toast.makeText(ProDefence.this, "Maximum marks is 4", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final int totalMarksGL = Integer.parseInt(strSubLmarks)+ Integer.parseInt(strProbLmarks)+
                            Integer.parseInt(strProjLmarks)+ Integer.parseInt(strContLmarks)+ Integer.parseInt(strLangLmarks);

                    final int memTotalMarks = Integer.parseInt(strsubmmarks)+ Integer.parseInt(strProbMmarks)+
                            Integer.parseInt(strProjMmarks)+ Integer.parseInt(strContMmarks)+ Integer.parseInt(strLangMmarks);

                    ProposalLmarks.setText(String.valueOf(totalMarksGL));
                    ProposalMmarks.setText(String.valueOf(memTotalMarks));




                    ProDefenceModel proDefenceModel = new ProDefenceModel();

                    proDefenceModel.setStrGroupID(strGroupID);
                    proDefenceModel.setStrLeaderName(strLeaderName);
                    proDefenceModel.setStrLReg(strLReg);
                    proDefenceModel.setStrMemberName(strMemberName);
                    proDefenceModel.setStrMReg(strMReg);
                    proDefenceModel.setStrProposalLmarks(String.valueOf(totalMarksGL));
                    proDefenceModel.setStrProjMmarks(String.valueOf(memTotalMarks));
                    proDefenceModel.setStrProposalRemarks(strProposalRemarks);
                    proDefenceModel.setSelection(strSelection);


                    ProDefenceModel validationModel = new ProDefenceModel();
                    validationModel.setStrGroupID(strGroupID);
                    validationModel.setStrLeaderName(strLeaderName);
                    validationModel.setStrLReg(strLReg);
                    validationModel.setStrMemberName(strMemberName);
                    validationModel.setStrMReg(strMReg);
                    validationModel.setStrProposalLmarks(String.valueOf(totalMarksGL));
                    validationModel.setStrProjMmarks(String.valueOf(memTotalMarks));
                    validationModel.setStrProposalRemarks(strProposalRemarks);
                    validationModel.setSelection(strSelection);


                    validation.child(strProjectId).setValue(validationModel);
                    databaseReference.child(strSelection).child(strProjectId).setValue(proDefenceModel);

                    Toast.makeText(ProDefence.this, "successfully ", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ProDefence.this,EvaluatorHome.class));
                }
            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                strSelection = radioButton.getText().toString();
            }
        });


    }

    private void initialized() {
        etProjectId=findViewById(R.id.project_id);
        etProgram=findViewById(R.id.mid_program);
        etLeaderName=findViewById(R.id.pro_leader_name);
        etLReg=findViewById(R.id.pro_lreg_no);
        etMemberName=findViewById(R.id.pro_member_name);
        etMReg=findViewById(R.id.pro_mreg_no);
        etTitle=findViewById(R.id.pro_title);
        etSupervisor=findViewById(R.id.pro_supervisor);
        etSubLmarks=findViewById(R.id.sub_lmarks);
        etsubmmarks=findViewById(R.id.sub_mmarks);
        etProbLmarks=findViewById(R.id.prob_lmarks);
        etProbMmarks=findViewById(R.id.prob_mmarks);
        etProjLmarks=findViewById(R.id.proj_lmarks);
        etProjMmarks=findViewById(R.id.proj_mmarks);
        etContLmarks=findViewById(R.id.con_lmarks);
        etContMmarks=findViewById(R.id.con_mmarks);
        etLangLmarks=findViewById(R.id.lan_lmarks);
        etLangMmarks=findViewById(R.id.lan_mmarks);
        ProposalLmarks=findViewById(R.id.pro_lmarks);
        ProposalMmarks=findViewById(R.id.pro_mmarks);
        etProposalRemarks=findViewById(R.id.pro_remarks);
        radioGroup = findViewById(R.id.radioGroup);


        proDefBtn = findViewById(R.id.pro_defence_submit);

    }

    private void getTextFromField(){
        strProjectId=etProjectId.getText().toString();
        strProgram=etProgram.getText().toString();
        strLeaderName=etLeaderName.getText().toString();
        strLReg=etLReg.getText().toString();
        strMemberName=etMemberName.getText().toString();
        strMReg=etMReg.getText().toString();
        strTitle=etTitle.getText().toString();
        strSupervisor=etSupervisor.getText().toString();
        strSubLmarks=etSubLmarks.getText().toString();
        strsubmmarks=etsubmmarks.getText().toString();
        strProbLmarks=etProbLmarks.getText().toString();
        strProbMmarks=etProbMmarks.getText().toString();
        strProjLmarks=etProjLmarks.getText().toString();
        strProjMmarks=etProjMmarks.getText().toString();
        strContLmarks=etContLmarks.getText().toString();
        strContMmarks=etContMmarks.getText().toString();
        strLangLmarks=etLangLmarks.getText().toString();
        strLangMmarks=etLangMmarks.getText().toString();
        strProposalRemarks=etProposalRemarks.getText().toString();
    }


    private void DialogInformation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProDefence.this);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_for_group_id,null);

        Button btnSubmit = view.findViewById(R.id.dialog_submit_btn);
        final EditText group_id = view.findViewById(R.id.dialog_group_id);


        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strGroupID = group_id.getText().toString();

                if (strGroupID.isEmpty())
                {
                    Toast.makeText(ProDefence.this, "Kindly enter group id", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    progressDialog.setTitle("Loading Data");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();


                    validation.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.hasChild(strGroupID))
                            {
                                validation.addListenerForSingleValueEvent(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                    {
                                        if (dataSnapshot.hasChild(strGroupID))
                                        {
                                            String status = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("selection").getValue()).toString();

                                            if (status.equals("Accept"))
                                            {
                                                progressDialog.dismiss();
                                                AlertDialog.Builder builder =new AlertDialog.Builder(ProDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                                builder.setTitle(strGroupID)
                                                        .setMessage("Group Already evaluated")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                startActivity(new Intent(ProDefence.this,EvaluatorHome.class));
                                                            }
                                                        })
                                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                                        .show();
                                            }
                                            else if (status.equals("Revise") || status.equals("Reject"))
                                            {
                                                showData.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                                    {
                                                        if (dataSnapshot.hasChild(strGroupID))
                                                        {

                                                            if (dataSnapshot.child(strGroupID).hasChild("proposal"))
                                                            {
                                                                strProgram = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("program").getValue()).toString();
                                                                strLeaderName = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("groupLeaderName").getValue()).toString();
                                                                strLReg = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("groupLeaderReg").getValue()).toString();
                                                                strMemberName = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("memberName").getValue()).toString();
                                                                strMReg = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("memberReg").getValue()).toString();
                                                                if (dataSnapshot.child(strGroupID).hasChild("projectInfo"))
                                                                {
                                                                    strTitle = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("projectInfo").child("projectName").getValue()).toString();
                                                                    etTitle.setText(strTitle);

                                                                }
                                                                if (dataSnapshot.child(strGroupID).hasChild("supervisor"))
                                                                {
                                                                    strSupervisor = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("supervisor").getValue()).toString();
                                                                    etSupervisor.setText(strSupervisor);

                                                                }

                                                                etProjectId.setText(strGroupID);
                                                                etProgram.setText(strProgram);
                                                                etLeaderName.setText(strLeaderName);
                                                                etLReg.setText(strLReg);
                                                                etMemberName.setText(strMemberName);
                                                                etMReg.setText(strMReg);
                                                                dialog.dismiss();
                                                            }
                                                            else
                                                            {
                                                                progressDialog.dismiss();
                                                                AlertDialog.Builder builder =new AlertDialog.Builder(ProDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                                                builder.setTitle(strGroupID)
                                                                        .setMessage("Proposal not found")
                                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                dialog.dismiss();
                                                                                startActivity(new Intent(ProDefence.this,EvaluatorHome.class));
                                                                            }
                                                                        })
                                                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                                                        .show();
                                                            }
                                                        }
                                                        else
                                                        {
                                                            group_id.setText(null);
                                                            dialog.show();
                                                            Toast.makeText(ProDefence.this, "Group does't exist", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError)
                                                    {

                                                    }
                                                });



                                                progressDialog.dismiss();


                                                dialog.dismiss();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError)
                                    {

                                    }
                                });
                            }
                            else
                            {
                                showData.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                    {
                                        if (dataSnapshot.hasChild(strGroupID))
                                        {

                                            if (dataSnapshot.child(strGroupID).hasChild("proposal"))
                                            {
                                                strProgram = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("program").getValue()).toString();
                                                strLeaderName = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("groupLeaderName").getValue()).toString();
                                                strLReg = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("groupLeaderReg").getValue()).toString();
                                                strMemberName = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("memberName").getValue()).toString();
                                                strMReg = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("memberReg").getValue()).toString();
                                                if (dataSnapshot.child(strGroupID).hasChild("projectInfo"))
                                                {
                                                    strTitle = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("projectInfo").child("projectName").getValue()).toString();
                                                    etTitle.setText(strTitle);

                                                }
                                                if (dataSnapshot.child(strGroupID).hasChild("supervisor"))
                                                {
                                                    strSupervisor = Objects.requireNonNull(dataSnapshot.child(strGroupID).child("supervisor").getValue()).toString();
                                                    etSupervisor.setText(strSupervisor);

                                                }

                                                etProjectId.setText(strGroupID);
                                                etProgram.setText(strProgram);
                                                etLeaderName.setText(strLeaderName);
                                                etLReg.setText(strLReg);
                                                etMemberName.setText(strMemberName);
                                                etMReg.setText(strMReg);
                                                dialog.dismiss();
                                            }
                                            else
                                            {
                                                progressDialog.dismiss();
                                                AlertDialog.Builder builder =new AlertDialog.Builder(ProDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                                builder.setTitle(strGroupID)
                                                        .setMessage("Proposal not found")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                startActivity(new Intent(ProDefence.this,EvaluatorHome.class));
                                                            }
                                                        })
                                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                                        .show();
                                            }
                                        }
                                        else
                                        {
                                            group_id.setText(null);
                                            dialog.show();
                                            Toast.makeText(ProDefence.this, "Group does't exist", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError)
                                    {

                                    }
                                });



                                progressDialog.dismiss();


                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });


                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProDefence.this,EvaluatorHome.class));
    }
}
