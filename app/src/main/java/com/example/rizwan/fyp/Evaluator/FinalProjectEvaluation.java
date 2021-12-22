package com.example.rizwan.fyp.Evaluator;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizwan.fyp.Admin.totalMarksModel;
import com.example.rizwan.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FinalProjectEvaluation extends AppCompatActivity {
    private EditText etProjectId,etProgram,etLeaderName,etLReg,etMemberName,etMReg,etTitle,etSupervisor
            ,etPanelMember1,etpanelMember2,etPanelMember3,etPresLmarks,etPresMmarks,etVivaLmarks,etVivaMmarks
            ,etImpLmarks,etImpMmarks,etPart1Remarks,etIsLmarks,etISMmarks,etISRemarks
            ,etDocLeaderMarks,etDocMemberMarks,etDocRemarks;

    String  strProjectId,strProgram,strLeaderName,strLReg,strMemberName,strMReg,strTitle,strSupervisor
            ,strPanelMember1,strpanelMember2,strPanelMember3,strPresLmarks,strPresMmarks,strVivaLmarks,strVivaMmarks
            ,strImpLmarks,strImpMmarks,strPart1Lmarks,strPart1Mmarks,strPart1Remarks,strIsLmarks,strISMmarks,strISRemarks
            ,strDocLeaderMarks,strDocMemberMarks,strDocRemarks,evID;

    String member1,member2,member3;

    int glp,memp,glsrs,memsrs;



    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference,showData,validation,validationFinal,evaluationMember
            ,propasalMarksDb,midMarksDB,totalMarksDB;

    private Button btnFinalDefence;

    private TextView finalLmarks,finalMmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_project_evaluation);

        final totalMarksModel marks = new totalMarksModel();

        initialized();


        evID = Objects.requireNonNull(getIntent().getExtras()).getString("ID");

        progressDialog = new ProgressDialog(this);

        showData = FirebaseDatabase.getInstance().getReference().child("Groups");
        evaluationMember = FirebaseDatabase.getInstance().getReference().child("Evaluator").child(evID);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Defence").child("Final");
        validation = FirebaseDatabase.getInstance().getReference().child("Validation").child("SRS");
        validationFinal = FirebaseDatabase.getInstance().getReference().child("Validation").child("Final");
        propasalMarksDb = FirebaseDatabase.getInstance().getReference().child("Validation").child("Proposal");
        midMarksDB = FirebaseDatabase.getInstance().getReference().child("Validation").child("SRS");
        totalMarksDB = FirebaseDatabase.getInstance().getReference().child("Marks");


        evaluationMember.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    member1 = Objects.requireNonNull(dataSnapshot.child("panelMemberName").getValue()).toString();
                    member2 = Objects.requireNonNull(dataSnapshot.child("panelMemberName2").getValue()).toString();
                    member3 = Objects.requireNonNull(dataSnapshot.child("panelMemberName3").getValue()).toString();

                    etPanelMember1.setText(member1);
                    etpanelMember2.setText(member2);
                    etPanelMember3.setText(member3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DialogInformation();






        btnFinalDefence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTextFromField();

                if (strProjectId.isEmpty() || strProgram.isEmpty() || strLeaderName.isEmpty() || strLReg.isEmpty() ||
                        strMemberName.isEmpty() | strMReg.isEmpty() || strTitle.isEmpty() || strSupervisor.isEmpty()
                        || strPresLmarks.isEmpty() || strPresMmarks.isEmpty() || strVivaLmarks.isEmpty() || strVivaMmarks.isEmpty()
                                || strImpLmarks.isEmpty() || strImpMmarks.isEmpty() || strPart1Remarks.isEmpty() || strIsLmarks.isEmpty() || strISMmarks.isEmpty() ||
                strISRemarks.isEmpty() || strDocLeaderMarks.isEmpty() || strDocMemberMarks.isEmpty() || strDocRemarks.isEmpty())
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Kindly fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strPresLmarks) > 15 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 15", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strPresMmarks) > 15 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 15", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strVivaLmarks) > 15 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strVivaMmarks) > 15 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strImpLmarks) > 20 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 20", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strImpMmarks) > 20 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 20", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strIsLmarks) > 50 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 50", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strISMmarks) > 50 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 50", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strDocLeaderMarks) > 40 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 40", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strDocMemberMarks) > 40 )
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Maximum marks is 40", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    final int totalMarksGL = Integer.parseInt(strPresLmarks)+ Integer.parseInt(strVivaLmarks)+
                            Integer.parseInt(strImpLmarks)+ Integer.parseInt(strIsLmarks)+
                            Integer.parseInt(strDocLeaderMarks);

                    final int memTotalMarks = Integer.parseInt(strPresMmarks)+ Integer.parseInt(strVivaMmarks)+
                            Integer.parseInt(strImpMmarks)+ Integer.parseInt(strISMmarks)+
                            Integer.parseInt(strDocMemberMarks);

                    finalLmarks.setText(String.valueOf(totalMarksGL));
                    finalMmarks.setText(String.valueOf(memTotalMarks));




                    ProDefenceModel proDefenceModel = new ProDefenceModel();

                    proDefenceModel.setStrGroupID(strProjectId);
                    proDefenceModel.setStrLeaderName(strLeaderName);
                    proDefenceModel.setStrLReg(strLReg);
                    proDefenceModel.setStrMemberName(strMemberName);
                    proDefenceModel.setStrMReg(strMReg);
                    proDefenceModel.setStrProposalLmarks(String.valueOf(totalMarksGL));
                    proDefenceModel.setStrProjMmarks(String.valueOf(memTotalMarks));
                    proDefenceModel.setStrProposalRemarks(strDocRemarks);


                    ProDefenceModel validationModel = new ProDefenceModel();
                    validationModel.setStrGroupID(strProjectId);
                    validationModel.setStrLeaderName(strLeaderName);
                    validationModel.setStrLReg(strLReg);
                    validationModel.setStrMemberName(strMemberName);
                    validationModel.setStrMReg(strMReg);
                    validationModel.setStrProposalLmarks(String.valueOf(totalMarksGL));
                    validationModel.setStrProjMmarks(String.valueOf(memTotalMarks));
                    validationModel.setStrProposalRemarks(strDocRemarks);


                    validationFinal.child(strProjectId).setValue(validationModel);


                    databaseReference.child(strProjectId).setValue(proDefenceModel);

                    Toast.makeText(FinalProjectEvaluation.this, "successfully ", Toast.LENGTH_SHORT).show();

                    //   startActivity(new Intent(MidDefence.this,EvaluatorHome.class));




                    //totalMarks

                    marks.setGlName(strLeaderName);
                    marks.setMemName(strMemberName);
                    marks.setGlTotalMarks(totalMarksGL+glp+glsrs);
                    marks.setMemTotalMarks(memTotalMarks+memp+memsrs);
                    marks.setRemarks(strDocRemarks);

                    totalMarksDB.child(strProjectId).setValue(marks);

                }
            }
        });



    }

    private void DialogInformation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(FinalProjectEvaluation.this);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_for_group_id,null);

        Button btnSubmit = view.findViewById(R.id.dialog_submit_btn);
        final EditText group_id = view.findViewById(R.id.dialog_group_id);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                strProjectId = group_id.getText().toString();

                propasalMarksDb.child(strProjectId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                        {
                            glp = Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("strProposalLmarks").getValue()).toString());
                            memp = Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("strProjMmarks").getValue()).toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                midMarksDB.child(strProjectId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())

                        {
                            glsrs = Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("strProposalLmarks").getValue()).toString());
                            memsrs = Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("strProjMmarks").getValue()).toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                if (strProjectId.isEmpty())
                {
                    Toast.makeText(FinalProjectEvaluation.this, "Kindly enter group id", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.setTitle("Loading Data");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();


                    showData.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.hasChild(strProjectId))
                            {
                                if (dataSnapshot.child(strProjectId).hasChild("srs"))
                                {
                                    validation.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.hasChild(strProjectId)) {
                                                showData.child(strProjectId).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                                    {

                                                            validationFinal.child(strProjectId).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                    if (dataSnapshot.exists()) {
                                                                        progressDialog.dismiss();
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(FinalProjectEvaluation.this, android.R.style.Theme_Material_Dialog_Alert);
                                                                        builder.setTitle(strProjectId)
                                                                                .setMessage("Already evaluated this group")
                                                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                                        dialog.dismiss();
                                                                                    }
                                                                                })
                                                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                                                .show();
                                                                    } else {
                                                                        showData.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild(strProjectId)) {
                                                                                    strProgram = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("program").getValue()).toString();
                                                                                    strLeaderName = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("groupLeaderName").getValue()).toString();
                                                                                    strLReg = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("groupLeaderReg").getValue()).toString();
                                                                                    strMemberName = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("memberName").getValue()).toString();
                                                                                    strMReg = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("memberReg").getValue()).toString();
                                                                                    if (dataSnapshot.child(strProjectId).hasChild("projectInfo")) {
                                                                                        strTitle = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("projectInfo").child("projectName").getValue()).toString();
                                                                                        etTitle.setText(strTitle);

                                                                                    }
                                                                                    if (dataSnapshot.child(strProjectId).hasChild("supervisor")) {
                                                                                        strSupervisor = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("supervisor").getValue()).toString();
                                                                                        etSupervisor.setText(strSupervisor);

                                                                                    }

                                                                                    etProjectId.setText(strProjectId);
                                                                                    etProgram.setText(strProgram);
                                                                                    etLeaderName.setText(strLeaderName);
                                                                                    etLReg.setText(strLReg);
                                                                                    etMemberName.setText(strMemberName);
                                                                                    etMReg.setText(strMReg);
                                                                                    dialog.dismiss();
                                                                                    progressDialog.dismiss();
                                                                                } else {
                                                                                    progressDialog.dismiss();
                                                                                    group_id.setText(null);
                                                                                    dialog.show();
                                                                                    Toast.makeText(FinalProjectEvaluation.this, "Group does't exist", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                            }
                                                                        });
                                                                    }

                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                            } else {
                                                //show data

                                                progressDialog.dismiss();
                                                AlertDialog.Builder builder = new AlertDialog.Builder(FinalProjectEvaluation.this, android.R.style.Theme_Material_Dialog_Alert);
                                                builder.setTitle(strProjectId)
                                                        .setMessage("SRS evaluation pending")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        })
                                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                                        .show();

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                    progressDialog.dismiss();

                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(FinalProjectEvaluation.this, android.R.style.Theme_Material_Dialog_Alert);
                                    builder.setTitle(strProjectId)
                                            .setMessage("SRS not found")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .show();
                                }
                            }
                            else
                            {
                                progressDialog.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(FinalProjectEvaluation.this, android.R.style.Theme_Material_Dialog_Alert);
                                builder.setTitle(strProjectId)
                                        .setMessage("Group does't exist")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
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

    private void initialized() {
        etProjectId = findViewById(R.id.final_project_project_id);
        etProgram = findViewById(R.id.final_project_program);
        etLeaderName = findViewById(R.id.final_project_leader_name);
        etLReg = findViewById(R.id.final_project_lreg_no);
        etMemberName = findViewById(R.id.final_project_member_name);
        etMReg = findViewById(R.id.final_project_mreg_no);
        etTitle = findViewById(R.id.final_project_title);
        etSupervisor = findViewById(R.id.final_project_supervisor);
        etPanelMember1 = findViewById(R.id.final_project_panel1);
        etpanelMember2 = findViewById(R.id.final_project_panel2);
        etPanelMember3 = findViewById(R.id.final_project_panel3);
        etPresLmarks = findViewById(R.id.final_project_pre_lmarks);
        etPresMmarks = findViewById(R.id.final_project_pre_mmarks);
        etVivaLmarks = findViewById(R.id.final_project_viva_lmarks);
        etVivaMmarks = findViewById(R.id.final_project_viva_mmarks);
        etImpLmarks = findViewById(R.id.final_project_impl_lmarks);
        etImpMmarks = findViewById(R.id.final_project_impl_mmarks);
        finalLmarks = findViewById(R.id.final_project_part1_lmarks);
        finalMmarks = findViewById(R.id.final_project_part1_mmarks);
        etPart1Remarks = findViewById(R.id.final_project_part1_remarks);
        etIsLmarks = findViewById(R.id.final_internal_supervisor_lmarks);
        etISMmarks = findViewById(R.id.final_internal_supervisor_mmarks);
        etISRemarks = findViewById(R.id.final_internal_supervisor_remarks);
        etDocLeaderMarks = findViewById(R.id.final_doc_lmarks);
        etDocMemberMarks = findViewById(R.id.final_doc_mmarks);
        etDocRemarks = findViewById(R.id.final_doc_part2_remarks);

        btnFinalDefence = findViewById(R.id.final_project_submit_btn);


    }
    private void getTextFromField(){
        strProjectId = etProjectId.getText().toString();
        strProgram = etProgram.getText().toString();
        strLeaderName = etLeaderName.getText().toString();
        strLReg = etLReg.getText().toString();
        strMemberName = etMemberName.getText().toString();
        strMReg = etMReg.getText().toString();
        strTitle = etTitle.getText().toString();
        strSupervisor = etSupervisor.getText().toString();
        strPanelMember1 = etPanelMember1.getText().toString();
        strpanelMember2 = etpanelMember2.getText().toString();
        strPanelMember3 = etPanelMember3.getText().toString();
        strPresLmarks = etPresLmarks.getText().toString();
        strPresMmarks = etPresMmarks.getText().toString();
        strVivaLmarks = etVivaLmarks.getText().toString();
        strVivaMmarks = etVivaMmarks.getText().toString();
        strImpLmarks = etImpLmarks.getText().toString();
        strImpMmarks = etImpMmarks.getText().toString();
        strPart1Remarks = etPart1Remarks.getText().toString();
        strIsLmarks = etIsLmarks.getText().toString();
        strISMmarks = etISMmarks.getText().toString();
        strISRemarks = etISRemarks.getText().toString();
        strDocLeaderMarks = etDocLeaderMarks.getText().toString();
        strDocMemberMarks = etDocMemberMarks.getText().toString();
        strDocRemarks = etDocRemarks.getText().toString();

    }
}