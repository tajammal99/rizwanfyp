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

import com.example.rizwan.fyp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MidDefence extends AppCompatActivity {
    private EditText etProjectId,etProgram,etLeaderName,etLReg,etMemberName,etMReg,etTitle,etSupervisor
            ,etOveLmarks,etOveMmarks,etExtLmarks,etExtMmarks,etDemLmarks,etDemMmarks,etFunLmarks,etFunMmarks,etNonfLmarks
            ,etNonfMmarks,etGraLmarks,etGraMmarks,etMidRemarks;

    String strProjectId,strProgram,strLeaderName,strLReg,strMemberName,strMReg,strTitle,strSupervisor
            ,strOveLmarks,strOveMmarks,strExtLmarks,strExtMmarks,strDemLmarks,strDemoMmarks,strFunLmarks,strFunMmarks,strNonfLmarks
            ,strNonfMmarks,strGraLmarks,strGraMmarks,strMidTotalLmarks,strMidTotalMmarks,strMidRemarks;

    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference,showData,validation,validationSRS,finalDb,finalValidation;

    private Button btnMidDefence;

    private TextView midLmarks,midMmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_defence);

        initialized();

        progressDialog = new ProgressDialog(this);

        showData = FirebaseDatabase.getInstance().getReference().child("Groups");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Defence").child("SRS");
        finalDb = FirebaseDatabase.getInstance().getReference().child("Defence").child("Final");
        finalValidation = FirebaseDatabase.getInstance().getReference().child("Validation").child("Final");
        validation = FirebaseDatabase.getInstance().getReference().child("Validation").child("Proposal");
        validationSRS = FirebaseDatabase.getInstance().getReference().child("Validation").child("SRS");

        DialogInformation();

        btnMidDefence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GetTextFromField();

                if (strProjectId.isEmpty() || strProgram.isEmpty() || strLeaderName.isEmpty() || strLReg.isEmpty() ||
                        strMemberName.isEmpty() | strMReg.isEmpty() || strTitle.isEmpty() || strSupervisor.isEmpty() ||
                    strOveLmarks.isEmpty() || strOveMmarks.isEmpty() || strExtLmarks.isEmpty() || strExtMmarks.isEmpty() ||
                        strDemLmarks.isEmpty() || strDemoMmarks.isEmpty() || strFunLmarks.isEmpty() || strFunMmarks.isEmpty()
                || strNonfLmarks.isEmpty() || strNonfMmarks.isEmpty() ||
                        strGraLmarks.isEmpty() || strGraMmarks.isEmpty() || strMidRemarks.isEmpty())
                {
                    Toast.makeText(MidDefence.this, "Kindly fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strOveLmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strOveMmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strExtLmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strExtMmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strDemLmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strDemoMmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strFunLmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strFunMmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strNonfLmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strNonfMmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strGraLmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strGraMmarks) > 5 )
                {
                    Toast.makeText(MidDefence.this, "Maximum marks is 5", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final int totalMarksGL = Integer.parseInt(strOveLmarks)+ Integer.parseInt(strExtLmarks)+
                            Integer.parseInt(strDemLmarks)+ Integer.parseInt(strFunLmarks)+
                            Integer.parseInt(strNonfLmarks)+Integer.parseInt(strGraLmarks);

                    final int memTotalMarks = Integer.parseInt(strOveMmarks)+ Integer.parseInt(strExtMmarks)+
                            Integer.parseInt(strDemoMmarks)+ Integer.parseInt(strFunMmarks)+
                            Integer.parseInt(strNonfMmarks)+Integer.parseInt(strGraMmarks);

                    midLmarks.setText(String.valueOf(totalMarksGL));
                    midMmarks.setText(String.valueOf(memTotalMarks));




                    ProDefenceModel proDefenceModel = new ProDefenceModel();

                    proDefenceModel.setStrGroupID(strProjectId);
                    proDefenceModel.setStrLeaderName(strLeaderName);
                    proDefenceModel.setStrLReg(strLReg);
                    proDefenceModel.setStrMemberName(strMemberName);
                    proDefenceModel.setStrMReg(strMReg);
                    proDefenceModel.setStrProposalLmarks(String.valueOf(totalMarksGL));
                    proDefenceModel.setStrProjMmarks(String.valueOf(memTotalMarks));
                    proDefenceModel.setStrProposalRemarks(strMidRemarks);


                    ProDefenceModel validationModel = new ProDefenceModel();
                    validationModel.setStrGroupID(strProjectId);
                    validationModel.setStrLeaderName(strLeaderName);
                    validationModel.setStrLReg(strLReg);
                    validationModel.setStrMemberName(strMemberName);
                    validationModel.setStrMReg(strMReg);
                    validationModel.setStrProposalLmarks(String.valueOf(totalMarksGL));
                    validationModel.setStrProjMmarks(String.valueOf(memTotalMarks));
                    validationModel.setStrProposalRemarks(strMidRemarks);


                    validationSRS.child(strProjectId).setValue(validationModel);


                    databaseReference.child(strProjectId).setValue(proDefenceModel);

                    Toast.makeText(MidDefence.this, "successfully ", Toast.LENGTH_SHORT).show();

                 //   startActivity(new Intent(MidDefence.this,EvaluatorHome.class));
                }
            }
        });


    }

    private void DialogInformation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MidDefence.this);
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

                if (strProjectId.isEmpty())
                {
                    Toast.makeText(MidDefence.this, "Kindly enter group id", Toast.LENGTH_SHORT).show();
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
                                validation.addListenerForSingleValueEvent(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                    {
                                        if (dataSnapshot.hasChild(strProjectId))
                                        {
                                            String status = "a";
                                            if (dataSnapshot.child(strProjectId).hasChild("selection"))
                                            {
                                                status = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("selection").getValue()).toString();

                                            }

                                            if (status.equals("Accept"))
                                            {

                                                showData.child(strProjectId).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                                    {
                                                        if (dataSnapshot.hasChild("srs"))
                                                        {

                                                            validationSRS.child(strProjectId).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                                                {

                                                                    if (dataSnapshot.exists())
                                                                    {
                                                                        progressDialog.dismiss();
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(MidDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                                                        builder.setTitle(strProjectId)
                                                                                .setMessage("Already evaluated this group")
                                                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                                        dialog.dismiss();
                                                                                    }
                                                                                })
                                                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                                                .show();
                                                                    }
                                                                    else
                                                                    {
                                                                        showData.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                                                            {
                                                                                if (dataSnapshot.hasChild(strProjectId))
                                                                                {
                                                                                    strProgram = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("program").getValue()).toString();
                                                                                    strLeaderName = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("groupLeaderName").getValue()).toString();
                                                                                    strLReg = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("groupLeaderReg").getValue()).toString();
                                                                                    strMemberName = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("memberName").getValue()).toString();
                                                                                    strMReg = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("memberReg").getValue()).toString();
                                                                                    if (dataSnapshot.child(strProjectId).hasChild("projectInfo"))
                                                                                    {
                                                                                        strTitle = Objects.requireNonNull(dataSnapshot.child(strProjectId).child("projectInfo").child("projectName").getValue()).toString();
                                                                                        etTitle.setText(strTitle);

                                                                                    }
                                                                                    if (dataSnapshot.child(strProjectId).hasChild("supervisor"))
                                                                                    {
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
                                                                                }
                                                                                else
                                                                                {
                                                                                    progressDialog.dismiss();
                                                                                    group_id.setText(null);
                                                                                    dialog.show();
                                                                                    Toast.makeText(MidDefence.this, "Group does't exist", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError databaseError)
                                                                            {

                                                                            }
                                                                        });
                                                                    }

                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                }
                                                            });
                                                        }
                                                        else
                                                        {
                                                            progressDialog.dismiss();
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(MidDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                                            builder.setTitle(strProjectId)
                                                                    .setMessage("SRS not found of this group")
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
                                                AlertDialog.Builder builder = new AlertDialog.Builder(MidDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                                builder.setTitle(strProjectId)
                                                        .setMessage("Group proposal in revision or rejected")
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
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MidDefence.this, android.R.style.Theme_Material_Dialog_Alert);
                                            builder.setTitle(strProjectId)
                                                    .setMessage("Group proposal no submitted")
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
                                    public void onCancelled(@NonNull DatabaseError databaseError)
                                    {

                                    }
                                });

                                progressDialog.dismiss();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(MidDefence.this, android.R.style.Theme_Material_Dialog_Alert);
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
        etProjectId=findViewById(R.id.mid_project_id);
        etProgram=findViewById(R.id.mid_program);
        etLeaderName=findViewById(R.id.mid_leader_name);
        etLReg=findViewById(R.id.mid_lreg_no);
        etMemberName=findViewById(R.id.mid_member_name);
        etMReg=findViewById(R.id.mid_mreg_no);
        etTitle=findViewById(R.id.mid_title);
        etSupervisor=findViewById(R.id.mid_supervisor);
        etOveLmarks=findViewById(R.id.ove_lmarks);
        etOveMmarks=findViewById(R.id.ove_mmarks);
        etExtLmarks=findViewById(R.id.ext_lmarks);
        etExtMmarks=findViewById(R.id.ext_mmarks);
        etDemLmarks=findViewById(R.id.dem_lmarks);
        etDemMmarks=findViewById(R.id.dem_mmarks);
        etFunLmarks=findViewById(R.id.fun_lmarks);
        etFunMmarks=findViewById(R.id.fun_mmarks);
        etNonfLmarks=findViewById(R.id.nonf_lmarks);
        etNonfMmarks=findViewById(R.id.nonf_mmarks);
        etGraLmarks=findViewById(R.id.gra_lmarks);
        etGraMmarks=findViewById(R.id.gra_mmarks);
        midLmarks = findViewById(R.id.mid_total_lmarks);
        midMmarks = findViewById(R.id.mid_total_mmarks);
        etMidRemarks=findViewById(R.id.mid_remarks);

        btnMidDefence = findViewById(R.id.mid_submit);


    }

    private void GetTextFromField(){
        strProjectId = etProjectId.getText().toString();
        strProgram = etProgram.getText().toString();
        strLeaderName = etLeaderName.getText().toString();
        strLReg = etLReg.getText().toString();
        strMemberName = etMemberName.getText().toString();
        strMReg = etMReg.getText().toString();
        strTitle = etTitle.getText().toString();
        strSupervisor = etSupervisor.getText().toString();
        strOveLmarks = etOveLmarks.getText().toString();
        strOveMmarks = etOveMmarks.getText().toString();
        strExtLmarks = etExtLmarks.getText().toString();
        strExtMmarks = etExtMmarks.getText().toString();
        strDemLmarks = etDemLmarks.getText().toString();
        strDemoMmarks = etDemMmarks.getText().toString();
        strFunLmarks = etFunLmarks.getText().toString();
        strFunMmarks = etFunMmarks.getText().toString();
        strNonfLmarks = etNonfLmarks.getText().toString();
        strNonfMmarks = etNonfMmarks.getText().toString();
        strGraLmarks = etGraLmarks.getText().toString();
        strGraMmarks = etGraMmarks.getText().toString();
        strMidRemarks = etMidRemarks.getText().toString();

    }
}
