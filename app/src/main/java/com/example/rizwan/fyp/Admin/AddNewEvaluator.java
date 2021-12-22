package com.example.rizwan.fyp.Admin;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rizwan.fyp.Evaluator.EvaluatorMod;
import com.example.rizwan.fyp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewEvaluator extends AppCompatActivity {

    private EditText etPanelId,etpanelName1,etEmail1,etPanelName2,etEmail2,etpanelName3,etEmail3
            ,etPassword,etCnrmPassword;
    private Button btnAddNew;
    private String strPanelId,strpanelName1,strEmail1,strpanelName2,strEmail2,strpanelName3,strEmail3
            ,strPassword,strCnrmPassword;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_evaluator);

        initializedFields();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Evaluation Panel");


        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Evaluator");



        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromFields();

                if (strPanelId.isEmpty() || strpanelName1.isEmpty() || strEmail1.isEmpty() || strpanelName2.isEmpty()
                || strEmail2.isEmpty() || strpanelName3.isEmpty() || strEmail3.isEmpty() || strPassword.isEmpty() ||
                        strCnrmPassword.isEmpty())
                {
                    Toast.makeText(AddNewEvaluator.this, "Kindly fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (!strPassword.equals(strCnrmPassword))
                {
                    Toast.makeText(AddNewEvaluator.this, "Password does't match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    EvaluatorMod evaluatorMod = new EvaluatorMod();
                    evaluatorMod.setPanelID(strPanelId);
                    evaluatorMod.setPanelMemberName(strpanelName1);
                    evaluatorMod.setPanelMemberNameEmailID(strEmail1);
                    evaluatorMod.setPanelMemberName2(strpanelName2);
                    evaluatorMod.setPanelMemberName2EmailID(strEmail2);
                    evaluatorMod.setPanelMemberName3(strpanelName3);
                    evaluatorMod.setPanelMemberName3EmailID(strEmail3);
                    evaluatorMod.setPassword(strCnrmPassword);

                    progressDialog.setTitle("Adding Data");
                    progressDialog.setMessage("Please wait......");
                    progressDialog.show();

                    databaseReference.child(evaluatorMod.getPanelID()).setValue(evaluatorMod);
                    clearData();

                    progressDialog.dismiss();

                    Toast.makeText(AddNewEvaluator.this, "successfully ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void clearData()
    {
        etPanelId.setText(null);
        etpanelName1.setText(null);
        etEmail1.setText(null);
        etPanelName2.setText(null);
        etEmail2.setText(null);
        etpanelName3.setText(null);
        etEmail3.setText(null);
        etPassword.setText(null);
        etCnrmPassword.setText(null);
    }

    private void getTextFromFields()
    {
        strPanelId = etPanelId.getText().toString();
        strpanelName1 = etpanelName1.getText().toString();
        strEmail1 = etEmail1.getText().toString();
        strpanelName2 = etPanelName2.getText().toString();
        strEmail2 = etEmail2.getText().toString();
        strpanelName3 = etpanelName3.getText().toString();
        strEmail3 = etEmail3.getText().toString();
        strPassword = etPassword.getText().toString();
        strCnrmPassword = etCnrmPassword.getText().toString();
    }

    private void initializedFields()
    {
        etPanelId = findViewById(R.id.admin_panel_id);
        etpanelName1 = findViewById(R.id.panel_member_one_name);
        etEmail1 = findViewById(R.id.panel_member_one_email);
        etPanelName2 = findViewById(R.id.panel_member_two_name);
        etEmail2 = findViewById(R.id.panel_member_two_email);
        etpanelName3 = findViewById(R.id.panel_member_three_name);
        etEmail3 = findViewById(R.id.panel_member_three_email);
        etPassword = findViewById(R.id.panel_password);
        etCnrmPassword = findViewById(R.id.panel_confirm_password);
        btnAddNew = findViewById(R.id.addNewPanelButton);

    }
}
